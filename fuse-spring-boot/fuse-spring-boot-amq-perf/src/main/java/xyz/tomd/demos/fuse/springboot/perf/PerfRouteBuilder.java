package xyz.tomd.demos.fuse.springboot.perf;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PerfRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Push a message into a queue
        from("servlet:/test?httpMethodRestrict=POST")
                .to("direct:send");

        // Receive a message at localhost/test
        from("direct:send")
                .id("send-route")
                .log("Sending a message")
                .to("jms:queue:CAMELS.SHIMMER?disableReplyTo=true");

        // Receive from the same queue and just log it out
        from("jms:queue:CAMELS.SHIMMER")
                .id("receive-route")
                .log("Received a message");
    }
}
