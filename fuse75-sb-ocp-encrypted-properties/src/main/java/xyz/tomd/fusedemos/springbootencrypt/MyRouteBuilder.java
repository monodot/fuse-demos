package xyz.tomd.fusedemos.springbootencrypt;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

    public void configure() throws Exception {
        from("timer:foo?period=5000")
                .log("Property value is: {{secret.password}}")
                .log("Super secret password is: {{supersecret.password}}");
    }
}
