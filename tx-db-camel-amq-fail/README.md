# tx-db-camel-amq-fail

Demonstrate how a transaction involving two parties (e.g. message broker, database) is not atomic when XA transactions are not used.

This demo was created from the archetype: `io.fabric8.archetypes:karaf-camel-amq-archetype:1.2.0.redhat-630224`

## To run

- Spin up a local MySQL instance on port 3306; database `customers`, username `customers`, password `customers`
- Run an ActiveMQ broker on port 61616 with username `admin`, password `admin`

Set up your database first:

    mysql> create database customers;
    mysql> create user 'customers'@'localhost' identified by 'customers';
    mysql> grant all privileges on customers.* to 'customers'@'localhost';

If you're using JBoss Fuse, then ActiveMQ will be started for you already. Otherwise, spin up your broker nowwww.

Next, build the project and install it into your local Maven repository:

    $ mvn clean install -DskipTests

Then, in JBoss Fuse:

    JBossFuse:karaf@root> features:addurl mvn:org.ops4j.pax.jdbc/pax-jdbc-features/1.1.0/xml/features
    JBossFuse:karaf@root> features:install camel-sql pax-jdbc-mysql
    JBossFuse:karaf@root> osgi:install -s mvn:com.cleverbuilder.fusedemos/tx-db-camel-amq-fail/1.0.0-SNAPSHOT

Notice how:

- Every 10 seconds, Camel will attempt to push 5 messages into an ActiveMQ queue called `FOO`, in a route which is labelled as `<transacted/>`
- The SQL insert statement will fail, because the given column does not exist
- Yet... the 5 messages are still inserted into ActiveMQ
