package xyz.tomd.demos.fuse.springboot.perf;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PerfRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Basic, persistent

//        from("servlet:/test-persistent?httpMethodRestrict=POST")
//                .to("direct:send-persistent");

        from("direct:send-persistent")
                .id("send-persistent-route")
                .log("Sending a message, persistent")
                .to("jms:queue:CAMELS.SHIMMER?disableReplyTo=true&deliveryPersistent=true")
                .setBody(constant("Thankyou for your submission!"));


//        restConfiguration().component("servlet");
//
//        rest("/reset/request/{data}")
//                .get()
//                .post()
//                .route()
//                .log("Received request...")
//                .setHeader(Exchange.HTTP_PATH, simple("/testytesty/index.php"))
//                .setHeader(Exchange.HTTP_QUERY, simple("_q=requestreset&data=${header.data}"))
//                .to("http://localhost:8080?bridgeEndpoint=true");



        from("servlet:?matchOnUriPrefix=true")
                .to("log:mylogger?showAll=true")

                .process(new Processor() {
                    public void process(Exchange exchange) throws Exception {
                        // Perform a lookup in your HashMap here, e.g.:
                        String path = exchange.getMessage()
                                .getHeader(Exchange.HTTP_PATH, String.class);
                        //if (path.startsWith("...")) ...

                        if (exchange.getMessage()
                                .getHeader(Exchange.HTTP_PATH, String.class)
                                .startsWith("reset/request")) {
                            exchange.getMessage().setHeader(Exchange.HTTP_QUERY,
                                    "_q=requestreset&data=${header.CamelHttpPath}"); // set the target URL
                            exchange.getMessage().setHeader(Exchange.HTTP_PATH,
                                    ""); // prevent Camel adding the same path to the target URL
                        }
                    }
                })
                .to("http://localhost:8080?bridgeEndpoint=true");


//        // Basic, non-persistent
//
//        from("servlet:/test-nonpersistent?httpMethodRestrict=POST")
//                .to("direct:send-nonpersistent");
//
//        from("direct:send-nonpersistent")
//                .id("send-nonpersistent-route")
//                .log("Sending a message, non-persistent")
//                .to("jms:queue:CAMELS.SHIMMER?disableReplyTo=true&deliveryPersistent=false");



        from("jms:queue:CAMELS.SHIMMER")
                .id("receive-route")
                .log("Received a message");
    }
}
