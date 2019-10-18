# karaf-spring-osgi-camel-demo

Demonstrates a Camel route running inside Spring DM (Spring for OSGi containers) on JBoss Fuse.

## Install into Fuse 6.3

Compile and install into your local Maven repository:

    mvn clean install

Then to install into **JBoss Fuse 6.3** (from the Karaf console):

    osgi:install -s mvn:xyz.tomd.fusedemos/karaf-spring-osgi-camel-demo/1.0-SNAPSHOT

```
2019-05-16 11:29:48,342 | INFO  | ExtenderThread-5 | OsgiBundleXmlApplicationContext  | 205 - org.apache.servicemix.bundles.spring-context - 3.2.16.RELEASE_1 | Publishing application context as OSGi service with properties {org.springframework.context.service.name=xyz.tomd.fusedemos.spring-osgi-camel-demo, Bundle-SymbolicName=xyz.tomd.fusedemos.spring-osgi-camel-demo, Bundle-Version=1.0.0.SNAPSHOT}
2019-05-16 11:29:48,346 | INFO  | ExtenderThread-5 | ContextLoaderListener            | 210 - org.springframework.osgi.extender - 1.2.1 | Application context successfully refreshed (OsgiBundleXmlApplicationContext(bundle=xyz.tomd.fusedemos.spring-osgi-camel-demo, config=osgibundle:/META-INF/spring/*.xml))
2019-05-16 11:29:49,341 | INFO  |  timer://mytimer | timer-route                      | 232 - org.apache.camel.camel-core - 2.17.0.redhat-630187 | Hello from Camel
2019-05-16 11:29:54,340 | INFO  |  timer://mytimer | timer-route                      | 232 - org.apache.camel.camel-core - 2.17.0.redhat-630187 | Hello from Camel
```

## Install into Fuse 7

To install into **Fuse 7 on Karaf** (from the Karaf console), first install the `aries-blueprint-spring` feature:

    features:install aries-blueprint-spring

Then install the bundle into Karaf:

    osgi:install -s mvn:xyz.tomd.fusedemos/spring-osgi-camel-demo/1.0-SNAPSHOT

You should then see the following in the Karaf logs:

```
13:46:01.639 INFO [Blueprint Extender: 3] Refreshing org.apache.aries.blueprint.spring.SpringApplicationContext@5191ce7a: startup date [Thu May 16 13:46:01 BST 2019]; root of context hierarchy
13:46:01.658 INFO [Blueprint Extender: 3] Loading XML bean definitions from URL [bundle://228.0:0/META-INF/spring/camel-context.xml]
13:46:02.097 INFO [Blueprint Extender: 3] Refreshing org.apache.aries.blueprint.spring.SpringApplicationContext@5191ce7a: startup date [Thu May 16 13:46:02 BST 2019]; root of context hierarchy
13:46:02.350 INFO [Blueprint Extender: 3] Apache Camel 2.21.0.fuse-730078-redhat-00001 (CamelContext: camel-1) is starting
...
13:46:02.593 INFO [Blueprint Extender: 3] Blueprint bundle xyz.tomd.fusedemos.spring-osgi-camel-demo/1.0.0.SNAPSHOT has been started
13:46:03.780 INFO [Camel (camel-1) thread #1 - timer://mytimer] Hello from Camel
13:46:08.577 INFO [Camel (camel-1) thread #1 - timer://mytimer] Hello from Camel
```

The `aries-blueprint-spring` feature is provided as part of the `spring-legacy` _feature repository_:

    mvn:org.apache.karaf.features/spring-legacy/4.2.0.fuse-730036-redhat-00001/xml/features

