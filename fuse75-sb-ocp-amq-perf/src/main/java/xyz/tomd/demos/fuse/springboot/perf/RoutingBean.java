package xyz.tomd.demos.fuse.springboot.perf;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.springframework.stereotype.Component;

@Component
public class RoutingBean {

    public static String getTargetUrl(String httpPath) {
        // Do something here to look up your target URL
        // return .....
        return "_q=requestreset&data=${header.CamelHttpPath}";
    }
}
