# fuse-spring-boot-perf-demo

A demo sending and receiving messages to Artemis.

## To run

Download and extract Apache ActiveMQ Artemis. Configure the broker as per the installation instructions and then:

To run, setting a maximum heap size of 1Gb:

    mvn clean install
    java -jar target/fuse-spring-boot-amq-perf-1.0-SNAPSHOT.jar -Xmx1024m

To send a test message:

    curl -X POST \
        -d @src/test/resources/payload.txt \
        -H 'Content-Type: text/plain' \
        http://localhost:8080/test-services/test/

To create a test payload (e.g. of 10,000 characters):

    base64 /dev/urandom | head -c 10000 > src/test/resources/payload-10k.txt

## Monitoring using JConsole

Start a basic JConsole session:

    jconsole &

Then connect to the running application (if you're running through Maven you'll probably connect to the process named _org.codehaus.plexus.classworlds.launcher...` or similar).


## Test results

### Camel Servlet: send plain-text, send to ActiveMQ Artemis

This is a baseline test to see how fast we can send messages to the broker.

Environment setup:

- Red Hat AMQ 7.5 (Artemis 2.10.0), running on localhost
- Artemis JMS client with default settings. No explicit connection pooling configured.
- Fuse (Spring Boot) JVM configured with max heap size of 1Gb (-Xmx1024m)
- Test payload size is 10Kb

Test: sending 10,000 messages (synchronously) to the Camel Servlet over HTTP, which inserts each message into an Artemis queue:

    $ java -Xmx1024m -jar target/fuse-spring-boot-amq-perf-1.0-SNAPSHOT.jar
    $ date; for i in {1..10000};do curl -X POST -d @src/test/resources/payload-10k.txt -H 'Content-Type: text/plain' http://localhost:8080/test-services/test/; done; date
    Tue  3 Mar 11:36:13 GMT 2020
    Tue  3 Mar 11:38:08 GMT 2020

Results:

- Time taken to send 10,000 messages @ 11Kb: 1 minute 55 seconds.
- Fuse (Spring Boot) heap memory usage peaks at 475Mb.
- Fuse (Spring Boot) CPU usage peaks at 9.5%

## Notes

- Default setup is to create a standard Artemis connection factory (non-pooling). To create a pooled connection factory, Spring must find org.messaginghub.pooled.jms.JmsPoolConnectionFactory on the classpath.

