package xyz.tomd.demos.fuse.springboot.amqp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class AmqpRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {

/*
        from("timer:mytimer?period=30000")
                .setBody(constant("HELLO"))
                .log("Sending a message...")
                .to("amqp:queue:afds.requests?deliveryMode=jmsMessageType=Text");
*/

        from("amqp:afds.events")
                .log("Received a message! - ${body}");

    }
}
