package xyz.tomd.fusedemos.springcloudcontract;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@RunWith(SpringRestPactRunner.class)
@Provider("catalogue-search")
@PactFolder("pacts")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CatalogueServiceContractTest {

    @EndpointInject(uri = "mock:fetch-search-results")
    MockEndpoint mockEndpoint;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @Before
    public void setUp() throws Exception {
        System.out.println("Started.");
    }

    @State("search executes a lookup using queryParams")
    public void testSomething() {
        // Use Camel Mock to generate some fake data which will be returned by the API
        mockEndpoint.whenAnyExchangeReceived(new MockSearchResultsProcessor());
    }

    @State("a second state")
    public void testASecondState() {
        // Use Camel Mock to generate some fake data which will be returned by the API
        mockEndpoint.whenAnyExchangeReceived(new MockSearchResultsProcessor());
    }

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


}
