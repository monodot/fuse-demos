package xyz.tomd.fusedemos.springcloudcontract;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringBootRunner.class)
//@Provider("catalogue-search")
//@PactFolder("pacts")
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@UseAdviceWith
public class CatalogueServiceContractTest {

//    @EndpointInject(uri = "mock:fetch-search-results")
//    MockEndpoint mockEndpoint;

    @LocalServerPort
    int port;

    @Autowired
    CamelContext camelContext;

    @Before
    public void setUp() throws Exception {
        camelContext.getRouteDefinition("search-route")
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        mockEndpointsAndSkip("jms:*");
                    }
                });
        camelContext.start();
    }

//    @TestTarget // Annotation denotes Target that will be used for Pact tests
//    public final Target target = new HttpTarget(8080);

//    @Override
//    protected RoutesBuilder createRouteBuilder() throws Exception {
//        return new MyRouteBuilder();
//    }

//    @Override
//    public String isMockEndpointsAndSkip() {
//        return "jms:*";
//    }

//    /**
//     * Tell Camel that we don't want the context to start automatically.
//     * Because we're going to add a servlet into the Spring context.
//     * (So we can serve a REST service)
//     */
//    @Override
//    public boolean isUseAdviceWith() {
//        return true;
//    }

//    /**
//     * Create a Spring application context by scanning classes in the
//     * base package xyz.tomd.fusedemos.springcloudcontract.
//     * This will find and instantiate our Camel routes
//     */
//    protected AbstractApplicationContext createApplicationContext() {
//        ConfigurableApplicationContext context =
//                new SpringApplicationBuilder(Configuration.class)
//                        .web(true)
//                        .run();
//
//
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//
//        // This should pick up the ServletRegistrationBean in Application.java - why isn't it?
////        context.scan("xyz.tomd.fusedemos.springcloudcontract");
//
//        // How can we also instantiate a servlet?
////        context.register(TestConfig.class);
//        return context;
//    }

//    @Override
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//    }

/*
    @Override
    protected JndiRegistry createRegistry() throws Exception {
        JndiRegistry registry = super.createRegistry();

        // Instantiate a servlet bean and add it to the Camel registry
//        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),
//                "/api/v1/*");
//        registration.setName("CamelServlet");
//        registry.bind("servlet", registration);
        JndiRegistry jndi = super.createRegistry();
        jndi.bind("myBinding", new ServletRestHttpBinding());
        return registry;
    }
*/

    @Test
    public void runPacts() {

    }

//    @State("search executes a lookup using queryParams")
//    public void testSomething() throws Exception {
//        // Use Camel Mock to generate some fake data which will be returned by the API
////        mockEndpoint.whenAnyExchangeReceived(new MockSearchResultsProcessor());
//    }
//
//    @State("a second state")
//    public void testASecondState() {
//        // Use Camel Mock to generate some fake data which will be returned by the API
////        mockEndpoint.whenAnyExchangeReceived(new MockSearchResultsProcessor());
//    }

    /**
     * Custom Camel processor which generates fake data. To be used
     * in conjunction with a Mock component.
     */
    class MockSearchResultsProcessor implements Processor {
        public void process(Exchange exchange) throws Exception {
            SearchResult[] results = {
                    new SearchResult("1", "Yahoo", "http://yahoo.com"),
                    new SearchResult("2", "Google", "http://google.com"),
                    new SearchResult("3", "BBC", "http://bbc.co.uk")
            };
            exchange.getMessage().setBody(results);
        }
    }

//    @Configuration
//    @ComponentScan("xyz.tomd.fusedemos.springcloudcontract")
//    public static class SpringConfig {
//
//    }

//    @Configuration
//    public static class TestConfig {
//        // any additional beans go here
//
//        @Bean
//        public ServletRegistrationBean camelServletRegistrationBean() {
//            System.out.println("**** HELLO registering servlet bean");
//            ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),
//                    "/api/v1/*");
//            registration.setName("CamelServlet");
//            return registration;
//        }
//    }

}
