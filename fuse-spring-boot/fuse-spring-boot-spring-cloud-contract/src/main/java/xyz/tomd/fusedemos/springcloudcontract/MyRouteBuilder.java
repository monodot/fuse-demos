package xyz.tomd.fusedemos.springcloudcontract;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

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
                .log("SEARCH was hit!")
                .to("mock:fetch-search-results");
    }
}
