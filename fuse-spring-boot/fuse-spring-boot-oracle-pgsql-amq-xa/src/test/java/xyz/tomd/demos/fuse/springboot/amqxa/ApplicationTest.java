package xyz.tomd.demos.fuse.springboot.amqxa;

import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.transaction.TransactionManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(CamelSpringRunner.class)
@SpringBootApplication
public class ApplicationTest {

    @TestConfiguration
    static class Config {

        // Override the Oracle config
        @Bean(name = "oracleaq")
        JmsComponent fakeOracleJmsComponent() {
            return new JmsComponent();
        }
    }

    // Do stuff here to override Oracle AQ

    // Spin up embedded Artemis

    // Spin up embedded Postgresql or H2

    @Autowired
    CamelContext camelContext;

    @Test
    public void testSomething() throws Exception {
        assertThat(camelContext.getStatus().isStarted());
    }

}