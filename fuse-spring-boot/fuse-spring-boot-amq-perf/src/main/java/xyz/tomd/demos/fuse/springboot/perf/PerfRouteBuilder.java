package xyz.tomd.demos.fuse.springboot.perf;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PerfRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        // Basic, persistent

        from("servlet:/test-persistent?httpMethodRestrict=POST")
                .to("direct:send-persistent");

        from("direct:send-persistent")
                .id("send-persistent-route")
                .log("Sending a message, persistent")
                .to("jms:queue:CAMELS.SHIMMER?disableReplyTo=true&deliveryPersistent=true")
                .setBody(constant("Thankyou for your submission!"));


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
