package xyz.tomd.demos.fuse.springboot.amqp;

import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.messaginghub.pooled.jms.JmsPoolConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AmqpApplication {

    /**
     * A main method to start this application.
     */
    public static void main(String[] args) {
        SpringApplication.run(AmqpApplication.class, args);
    }

    @Bean(name = "amqp-component")
    AMQPComponent amqpComponent() throws Exception {
//        String remoteURI = "amqps://afds.apps.int-gateway.daa.ie:443";
        String remoteURI = "amqps://router-mesh-5671-daa-broker-interconnect.apps.int-services.daa.ie:443?amqp.traceFrames=true";
        // could also use amqp.saslMechanisms=PLAIN

        //        String remoteURI = "amqp://localhost:5672";

//        JmsConnectionFactory qpid = new JmsConnectionFactory("dave", "dave",
//                remoteURI);
        JmsConnectionFactory qpid2 = new JmsConnectionFactory();
        qpid2.setUsername("guest@router-mesh");
//        qpid2.setPassword("rNHieZUw");
        qpid2.setPassword("TbYhzjTO");
        qpid2.setRemoteURI(remoteURI);
//        qpid2.setTopicPrefix("topic://");

        JmsPoolConnectionFactory factory = new JmsPoolConnectionFactory();
        factory.setConnectionFactory(qpid2);

        AMQPComponent amqpComponent = new AMQPComponent();
        amqpComponent.setConnectionFactory(factory);

        return amqpComponent;
    }

/*
    @Bean
    ConnectionFactory connectionFactory() {
        ConnectionFactory cf = new
    }

    @Bean
    AMQPCom jmsComponent
*/


}
