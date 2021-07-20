package xyz.tomd.demos.fuse.springboot.amqxa;

import oracle.jdbc.xa.client.OracleXADataSource;
import oracle.jms.AQjmsFactory;
import org.apache.camel.component.jms.JmsComponent;
import org.messaginghub.pooled.jms.JmsPoolXAConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jta.XAConnectionFactoryWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.*;
import javax.transaction.TransactionManager;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
public class Application {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "jms")
    JmsComponent jmsComponent(ConnectionFactory activemqCF, PlatformTransactionManager jmstx) {
        JmsComponent jms = new JmsComponent();

        // This should be our ActiveMQ XA connection factory
        jms.setConnectionFactory(activemqCF);

        jms.setCacheLevelName("CACHE_CONSUMER");

        // Camel uses Spring JMS under the covers - so it expects a (Spring) PlatformTransactionManager
        jms.setTransactionManager(jmstx);

        jms.setTransacted(true);

        return jms;
    }

    @Bean(name = "oracleaq")
    JmsComponent oracleAQJmsComponent(PlatformTransactionManager transactionManager,
                                      TransactionManager jtaTransactionManager)
            throws JMSException, SQLException {
        OracleXADataSource oracleXADataSource = new OracleXADataSource();
        oracleXADataSource.setURL("jdbc:oracle:thin:@localhost:1521:ORCLCDB");
        oracleXADataSource.setUser("scott");
        oracleXADataSource.setPassword("tiger");

        // Now we've created the XA datasource, we need something that will generate an XAConnectionFactory
        XAConnectionFactory oracleXACF = AQjmsFactory.getXAConnectionFactory(oracleXADataSource);

        // Now we need to wrap this connection factory in an enlisting connection factory
        // Related reading: https://access.redhat.com/documentation/en-us/red_hat_fuse/7.2/html-single/apache_karaf_transaction_guide/index#about_auto_enlistment
        JmsPoolXAConnectionFactory pooledJmsXACF = new JmsPoolXAConnectionFactory();
        pooledJmsXACF.setConnectionFactory(oracleXACF);
        // Wire the connection factory to Narayana via its JTA interface implementation
        pooledJmsXACF.setTransactionManager(jtaTransactionManager);

        JmsComponent jms = new JmsComponent();
        jms.setConnectionFactory(pooledJmsXACF);
        // Wire the Camel JMS component to Narayana via its Spring interface implementation
        jms.setTransactionManager(transactionManager);

        jms.setTransacted(false);

        return jms;
    }


}
