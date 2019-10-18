package xyz.tomd.fusedemos.karaf.embeddedartemis;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.concurrent.TimeUnit;

@ContextConfiguration(locations = "/META-INF/spring/camel-context.xml")
public class ApplicationTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    ProducerTemplate template;

    @EndpointInject(uri = "mock:result")
    MockEndpoint mockResult;

    @Test
    public void testBroker() throws Exception {
        mockResult.expectedMessageCount(1);

        template.sendBody("direct:start", "Hello world!");

        mockResult.assertIsSatisfied(5, TimeUnit.SECONDS);
    }

}
