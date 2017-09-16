# simple-jms-transaction

Demonstrates a simple JMS transaction in Fuse/Camel.

## Run

First, build the project and install it into your local Maven repository:

    $ mvn clean install -DskipTests

Then, in JBoss Fuse:

    JBossFuse:karaf@root> features:install camel-amq
    JBossFuse:karaf@root> osgi:install -s mvn:com.cleverbuilder.fuse-demos/simple-jms-transaction/1.0-SNAPSHOT

By default, this will successfully push 5 messages into the queue `FOO`, every 10 seconds. You can observe the message count increasing in the Hawtio console.

To simulate an exception at the end of the route - **after** the messages have been inserted - and observe the transaction being rolled back (i.e. no messages being inserted into the queue), then change the config setting `com.cleverbuilder.fusedemos.simplejmstransaction/exception.throw`, like this:

    JBossFuse:karaf@root> config:edit com.cleverbuilder.fusedemos.simplejmstransaction
    JBossFuse:karaf@root> config:propset exception.throw true
    JBossFuse:karaf@root> config:update
    
Then, refresh the bundle to pick up the config change:

    JBossFuse:karaf@root> refresh com.cleverbuilder.fuse-demos.simple-jms-transaction 
    
This will cause an exception to be thrown at the end of the route, which causes the JMS transaction to be rolled back. You can observe in Hawtio that the message count to the queue `FOO` no longer increases.


    