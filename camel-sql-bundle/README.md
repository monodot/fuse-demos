# camel-sql-bundle

Demonstrates a Camel route which uses a SQL DataSource.

## To run

First, build the project using:

    $ mvn clean install -DskipTests

Then, in Fuse:

    karaf> features:install camel-sql
    karaf> osgi:install -s mvn:uk.co.monodot/camel-sql-bundle/1.0-SNAPSHOT

