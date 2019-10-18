package xyz.tomd.fusedemos.springcloudcontract;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.loader.PactSource;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRestPactRunner.class)
@Provider("catalogue-search")
@PactFolder("pacts")
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@MockEndpointsAndSkip("jms:*")
public class ApplicationTest {

    @Autowired
    ProducerTemplate template;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @Autowired
    CamelContext camelContext;

    @Before
    public void setUp() throws Exception {
        camelContext.getRouteDefinition("search-route").adviceWith(
                camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        mockEndpointsAndSkip("jms:*");
                    }
                }
        );
    }

    @State("search executes a lookup using queryParams")
    public void testSomething() throws Exception {
//        template.sendBody("direct:search", "help!");
    }

    @State("a second state")
    public void testTwo() throws Exception {

    }
}
