package xyz.tomd.fusedemos.springcloudcontract;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SearchAppRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {
        from("timer:foo?period=5000")
                .log("Message.....");

        restConfiguration()
                .component("servlet")
                .contextPath("/api/v1")
                .bindingMode(RestBindingMode.json);

        rest().get("/search")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .description("Search for resources")
                .outType(SearchResult[].class)
                .param().type(RestParamType.query).name("q").description("Term to search for").endParam()
                .param().type(RestParamType.query).name("bbox").description("Extent to search for").endParam()
                .responseMessage().code(200).message("Search executes successfully").endResponseMessage()
                .to("direct:search");

        from("direct:search").id("search-route")
                .log("Received a search request")
//                .to("jms:queue:MY.QUEUE?exchangePattern=InOnly");

                // Using a component which will need to be mocked in order for any test to pass
                .to("jms:queue:MY.QUEUE?exchangePattern=InOnly")
                .to("mongodb3:myMongoClient?collection=lovelycats&database=petsdb")
                .to("http4://example.com/hello")
                .log("End of route");
//                .to("mock:fetch-search-results");
    }
}
