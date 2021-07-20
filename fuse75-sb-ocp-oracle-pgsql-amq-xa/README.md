# fuse-spring-boot-oracle-pgsql-amq-xa

Demo of an XA (2PC) transaction in Apache Camel with Oracle AQ, Postgresl and ActiveMQ Artemis, using [Narayana][narayana] as the transaction manager.

This example is based on this repo: https://github.com/jboss-fuse/spring-boot-camel-xa/

## Getting started

### Run the supporting things

For this demo to run, you'll need:

- An AMQ 7 (Artemis) broker
- An instance of Postgresql
- An instance of Oracle Database

**To start an AMQ 7 broker:** Download and extract the AMQ distribution from the Red Hat Customer Portal, then:

    $ cd $AMQ_HOME
    $ ./bin/artemis create mybroker
    $ ./mybroker/bin/artemis run

This will start Artemis; watch out for this line in the logs which indicates that the broker is accepting requests on port 61616 for the _CORE_ protocol:

    2019-10-18 15:18:22,535 INFO  [org.apache.activemq.artemis.core.server] AMQ221020: Started EPOLL Acceptor at 0.0.0.0:61616 for protocols [CORE,MQTT,AMQP,STOMP,HORNETQ,OPENWIRE]

TODO - instructions on how to start Postgres and Oracle...

For the Oracle drivers.....well. You'll need to start by registering to use Oracle's Maven repository, here: https://www.oracle.com/webapps/maven/register/register.html



### Start the application

Now start the app:

    $ mvn clean spring-boot:run

Or, to run with Oracle client's own tracing output:

    $ mvn clean spring-boot:run -Doracle.jms.traceLevel=11

[narayana]: https://narayana.io/
