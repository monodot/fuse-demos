package xyz.tomd.fusedemos.springcloudcontract;

import io.restassured.RestAssured;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.UseAdviceWith;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(CamelSpringRunner.class)
@SpringBootTest(
        classes = Application.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CatalogueServiceBase {

    @LocalServerPort
    int port;

    @EndpointInject(uri = "mock:fetch-search-results")
    MockEndpoint mockSearch;

    @Before
    public void setUp() throws Exception {
        // This is needed so that RestAssured sends requests to the correct URL
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = this.port;

        // Configure our mock endpoint to generate some search results
        // In a real application, we might use AdviceWith to replace
        // an existing endpoint with a mock, allowing us to have
        // complete control over what the component returns, and inject
        // in some test data.
        mockSearch.whenAnyExchangeReceived(new MockSearchResultsProcessor());
    }


    /**
     * A processor which will generate our fake search results
     * for us.
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
