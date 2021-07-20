# blueprint-import-xml

How to add a secondary Blueprint XML into the same Blueprint container / bundle.

To build:

    mvn clean install

Then deploy into Fuse on Apache Karaf:

    karaf@root> osgi:install -s mvn:org.ops4j.pax.url/pax-url-classpath/2.6.1
    karaf@root> osgi:install -s mvn:org.ops4j.base/ops4j-base-lang/1.5.0
    karaf@root> osgi:install -s mvn:org.ops4j.pax.url/pax-url-commons/2.6.1


    karaf@root> osgi:install -s mvn:xyz.tomd.fusedemos/blueprint-import-xml/1.0-SNAPSHOT
    karaf@root> features:install camel-jetty

_"Fragment bundles will be attached to their host and become RESOLVED."_. -- https://stackoverflow.com/questions/43675990/fragment-osgi-bundle
