package xyz.tomd.demos.fuse.springboot.perf;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoutingProcessor implements Processor {

    private static Map<String, String> routeMap;

    public void process(Exchange exchange) throws Exception {
        String path = exchange.getMessage().getHeader(Exchange.HTTP_PATH, String.class);

        exchange.getMessage().setHeader(Exchange.HTTP_PATH, routeMap.get() );


        // Perform a lookup in your HashMap here, e.g.:
        if (exchange.getMessage()
                .getHeader(Exchange.HTTP_PATH, String.class)
                .startsWith("reset/request")) {
            exchange.getMessage().setHeader(Exchange.HTTP_QUERY,
                    "_q=requestreset&data=${header.CamelHttpPath}"); // set the target URL
            exchange.getMessage().setHeader(Exchange.HTTP_PATH,
                    ""); // prevent Camel adding the same path to the target URL
        }
    }
}
