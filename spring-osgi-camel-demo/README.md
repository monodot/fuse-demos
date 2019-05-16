# spring-osgi-camel-demo

Demonstrates a Camel route running inside Spring DM (Spring for OSGi containers) on JBoss Fuse.

To compile and install into your local Maven repository:

    mvn clean install

Then to install into JBoss Fuse 6.3 (from the Karaf console):

    osgi:install mvn:xyz.tomd.fusedemos/spring-osgi-camel-demo/1.0-SNAPSHOT

You should then see the following in the Karaf logs:

```
2019-05-16 11:29:48,342 | INFO  | ExtenderThread-5 | OsgiBundleXmlApplicationContext  | 205 - org.apache.servicemix.bundles.spring-context - 3.2.16.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=xyz.tomd.fusedemos.spring-osgi-camel-demo, Bundle-SymbolicName=xyz.tomd.fusedemos.spring-osgi-camel-demo, Bundle-Version=1.0.0.SNAPSHOT}
2019-05-16 11:29:48,346 | INFO  | ExtenderThread-5 | ContextLoaderListener            | 210 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=xyz.tomd.fusedemos.spring-osgi-camel-demo, config=osgibundle:/META-INF/spring/*.xml))
2019-05-16 11:29:49,341 | INFO  |  timer://mytimer | timer-route                      | 232 - org.apache.camel.camel-core - 2.17.0.redhat-630187 | Hello from Camel
2019-05-16 11:29:54,340 | INFO  |  timer://mytimer | timer-route                      | 232 - org.apache.camel.camel-core - 2.17.0.redhat-630187 | Hello from Camel
```
