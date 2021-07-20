# datasource-blueprint-bundle

Demonstrates defining a datasource using Blueprint in an OSGi bundle, and then exposing the datasource as a service.

## To run

First compile the project:

    $ mvn clean install

Add the pax jdbc feature into JBoss Fuse:

    karaf> features:addurl mvn:org.ops4j.pax.jdbc/pax-jdbc-features/1.1.0/xml/features
    karaf> features:install pax-jdbc-derbyclient

Then, in Fuse:

    karaf> osgi:install -s mvn:uk.co.monodot/datasource-blueprint-bundle/1.0-SNAPSHOT

## Info

This project uses OPS4J's pax-jdbc-derbyclient feature:

    Provide-Capability =
        osgi.service;
            objectClass=org.osgi.service.jdbc.DataSourceFactory;
            osgi.jdbc.driver.class=org.apache.derby.jdbc.ClientDriver;
            osgi.jdbc.driver.name=derbyclient
    Require-Capability =
        osgi.ee;filter:=(&(osgi.ee=JavaSE)(version=1.7))

    Export-Package =
        org.ops4j.pax.jdbc.derbyclient.constants;version=1.1.0
    Import-Package =
        javax.sql,
        org.apache.derby.jdbc,
        org.osgi.framework;version="[1.6,2)",
    org.osgi.service.jdbc;version="[1.0,2)"

