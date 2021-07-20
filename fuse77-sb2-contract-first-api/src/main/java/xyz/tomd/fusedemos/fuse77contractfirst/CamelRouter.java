package xyz.tomd.fusedemos.fuse77contractfirst;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;
import xyz.tomd.fusedemos.fuse77contractfirst.model.Cocktail;

/**
 * A simple Camel REST DSL route that implements the greetings service.
 * 
 */
@Component
public class CamelRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        /**
         * Swagger REST DSL plugin will automatically generate the REST DSL for us.
         * We just have to implement each method.
         * e.g. GET /cocktails => direct:getcocktails
         */
        from("direct:getcocktails")
                .hystrix()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Cocktail cocktail = new Cocktail();
                        cocktail.setName("Martini");
                        cocktail.setGarnish("Olive or lemon");
                        cocktail.setRating(8.5);
                        cocktail.setStrength(3);

                        exchange.getMessage().setBody(cocktail);
                    }
                })
                .marshal().json(JsonLibrary.Jackson)
                .log("Returning ${body}");

        /**
         * To implement the remaining methods:
         * - Ensure you have run `mvn clean camel-restdsl-openapi:generate-with-dto`
         * - Check out the REST DSL generated in `target/generated-sources/restdsl-openapi/xyz/tomd/fusedemos/fuse77contractfirst/DrinksAPI.java`
         */
    }

}