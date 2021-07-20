package xyz.tomd.demos.fuse.springboot.amqxa;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class XaApplicationRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {

        from("oracleaq:FOOQUEUE")
                .log("Received a message from Oracle AQ! - ${body}")

                .setHeader("name", body())
                .to("sql:insert into chickens (name) values (:#name)")
                .log("Inserted a chicken into the database")

                .log("Sending to ActiveMQ...")
                .to("jms:queue:CHICKENS.PROCESSED");

    }

}
