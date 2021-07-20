package xyz.tomd.fusedemos.springcloudcontract;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(CamelSpringBootRunner.class)
@Provider("my-search-app")
@PactFolder("pacts")
@SpringBootTest(classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@UseAdviceWith
public class ApplicationContractTest {

//    @EndpointInject(uri = "mock:fetch-search-results")
//    MockEndpoint mockEndpoint;

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContractTest.class);

    @LocalServerPort
    int port;

    @MockBean(name = "myMongoClient")
    MongoClient mongoClient;

    @Autowired
    CamelContext camelContext;

    @Before
    public void setUp() throws Exception {
        camelContext.getRouteDefinition("search-route")
                .adviceWith(camelContext, new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        mockEndpointsAndSkip("mongodb3:*");
                        mockEndpointsAndSkip("jms:*");
                        mockEndpointsAndSkip("http4:*");
                    }
                });
        camelContext.start();

        // TODO do something here to get the mongoClient bean to return a MongoDatabase object
        // TODO - add something here which makes mongo component *think* that it's connected to a database
        MongoDatabase mongoDatabase = new MongoDatabaseImpl();

        given(mongoClient.getDatabase("mydatabase"))
                .willReturn()


        given(mongoClient.getDatabase("mydatabase"))
                .willReturn(new MongoD)
    }

//    @TestTarget // Annotation denotes Target that will be used for Pact tests
//    public final Target target = new HttpTarget(8080);

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
