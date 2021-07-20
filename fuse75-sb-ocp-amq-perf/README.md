# fuse-spring-boot-perf-demo

A test scenario, testing the performance when sending and consuming messages from a message broker (ActiveMQ Artemis).

## To run

Download and extract Apache ActiveMQ Artemis. Configure the broker as per the installation instructions and then:

To run, setting a maximum heap size of 1Gb:

    mvn clean install
    java -jar target/fuse-spring-boot-amq-perf-1.0-SNAPSHOT.jar -Xmx1024m

To send a test message:

    curl -X POST \
        -d @src/test/resources/payload.txt \
        -H 'Content-Type: text/plain' \
        http://localhost:8080/test-services/test-persistent/

To create a test payload (e.g. of 10,000 characters):

    base64 /dev/urandom | head -c 10000 > src/test/resources/payload-10k.txt

## Monitoring using JConsole

Start a basic JConsole session:

    jconsole &

Then connect to the running application (if you're running through Maven you'll probably connect to the process named _org.codehaus.plexus.classworlds.launcher..._ or similar).


## Performance tests

### HTTP->JMS Producer, with Apache Bench

This is a test to see how fast we can send messages to ActiveMQ Artemis, by creating an HTTP (Servlet) endpoint in Camel which receives messages and sends them onto the broker. The scenario:

- Camel Servlet component to expose an HTTP endpoint
- Camel JMS component to send messages to Artemis
- Testing with a plain-text payload (no marshalling/unmarshalling required)
- Using a simple Camel route to send the message to ActiveMQ Artemis.
- Testing persistent vs non-persistent messaging.
- No transactionality (send-only)

Lenovo ThinkPad X1 Carbon - CPU details:

    $ lscpu
    Architecture:        x86_64
    CPU op-mode(s):      32-bit, 64-bit
    Byte Order:          Little Endian
    Address sizes:       39 bits physical, 48 bits virtual
    CPU(s):              8
    On-line CPU(s) list: 0-7
    Thread(s) per core:  2
    Core(s) per socket:  4
    Socket(s):           1
    NUMA node(s):        1
    Vendor ID:           GenuineIntel
    CPU family:          6
    Model:               142
    Model name:          Intel(R) Core(TM) i7-8650U CPU @ 1.90GHz
    Stepping:            10
    CPU MHz:             800.049
    CPU max MHz:         4200.0000
    CPU min MHz:         400.0000
    BogoMIPS:            4224.00
    Virtualization:      VT-x


Environment setup:

- Red Hat AMQ 7.5 (Artemis 2.10.0), running locally
- Red Hat Fuse 7.5 (Camel 2.23.2, Spring Boot 2.1.6), running locally
- JVM configured with max heap size of 1Gb (-Xmx1024m)
- Artemis JMS client with default settings. No explicit connection pooling configured.

Tests can be run by first spinning up an ActiveMQ Artemis broker. For file-based persistence:

    cp ./broker-<persistence>.xml /path/to/amq/etc/broker.xml
    
    /path/to/amq/bin/artemis run

For JDBC-based persistence with postgresql (note this is using an un-tuned deployment of Postgres - performance could probably be tuned/improved here):

    podman run --rm --name postgres \
        -e POSTGRESQL_USER=admin -e POSTGRESQL_PASSWORD=admin -e POSTGRESQL_DATABASE=sampledb \
        -e POSTGRESQL_MAX_CONNECTIONS=50 -e POSTGRESQL_MAX_PREPARED_TRANSACTIONS=50 \
        -d -p 5432:5432 registry.redhat.io/rhscl/postgresql-10-rhel7:latest
        
    cp ./broker-postgres.xml /path/to/amq/etc/broker.xml
    
    /path/to/amq/bin/artemis run

Then start the Fuse application, and execute the test using Apache Bench:

    java -jar target/fuse-spring-boot-amq-perf-1.0-SNAPSHOT.jar -Xmx1024m
    
    ab -n <count> -c <concurrent requests> \
        -p src/test/resources/payload-10k.txt \ 
        -T 'text/plain' \ 
        http://localhost:8080/test-services/test-persistent/

Test variations:

- Transactional JMS consumer vs non-transactional JMS consumer
- Message size (10k, 100k, 1Mb)
- Artemis persistence - file-based journal vs database (Postgres, JDBC)


#### Results

Here are the test results **for message sending**, executed in Mar 2020:

| Messages sent  | Size  | Concurrent requests | AMQ Persist. | Run  | Total time | Messages/sec | Mean (iii)   | Max request time |
| -------------- | ----- | ------------------- | ------------ | ---- | ---------- | -------------| ------------ | ---------------- |
| 1,000          | 10kb  | 1                   | File         |      | 3 secs     | 420          |              | 37 ms            |
| 1,000          | 10kb  | 1                   | JDBC         |      | 10 secs    | 101          | 9 ms         | 176 ms           |
| 1,000          | 100kb | 1                   | File         |      | 4 secs     | 271          | 4 ms         | 44 ms            |
| 1,000          | 100kb | 1                   | JDBC         |      | 16 secs    | 61           | 16 ms        | 123 ms           |
| 10,000         | 100kb | 1                   | File         |      | 38 secs    | 256          | 4 ms         | 1059 ms          |
| 10,000         | 100kb | 1                   | JDBC         |      | 259 secs   | 38           | 25 ms        | 2992 ms          |
| 10,000         | 10kb  | 100                 | File         |      | 32 secs    | 312          |              | 1217 ms          |
| 10,000         | 10kb  | 100                 | JDBC         |      | 48 secs    | 205          | 486 ms       | 1159 ms          |
| 10,000         | 100kb | 100                 | File         |      | 34 secs    | 288          | 346 ms       | 1163 ms          |
| 10,000         | 100kb | 100                 | JDBC         |      | 57 secs    | 172          | 579 ms       | 4154 ms          |
| 10,000         | 977K  | 100                 | File         |      | 156 secs   | 64           | 1560 ms (iv) | 15433 ms         |
| 10,000         | 977K  | 100                 | JDBC         |      | 2272 secs  | 4            | 22726 ms     | 66299 ms         |
| 50,000         | 10kb  | 200                 | File         | 1st  | 160 secs   | 312 (i)      |              | 1313 ms          |
|                |       |                     | File         | 2nd  | 177 secs   | 281          | 709 ms       | 1716 ms          |
| 50,000         | 10kb  | 300                 | File         | 1st  | 195 secs   | 256 (ii)     | 1172 ms      | 4340 ms          |
|                |       |                     | File         | 2nd  | 171 secs   | 292          | 1026 ms      | 2189 ms          |
| 10,000         | 10kb  | 1,000               | File         |      | 31 secs    | 316          |              | 4520 ms          |

Notes:

(i) Notice how these tests achieve the same number of messages/sec. This indicates that increasing concurrent requests in the Apache Bench test client had no effect. Possibly my laptop doesn't have enough resources to actually run 200 concurrent requests?

(ii) Decline in performance - possibly because I was restarting IntelliJ at the same time (not a good idea).

(iii) This is the 'Time per request' value from Apache Bench and is the mean time of a request, taking into account that there is concurrency.

(iv) Once the size of the message increases, the mean time starts increasing significantly

Other observations:

- Throughout these tests, the Spring Boot JVM hovered around **90 live threads**.
- The Spring Boot JVM averaged around **30% CPU**.
- The Artemis broker went to **100% CPU**.

Here is an extract from `top` during a test of 50,000 messages with 200 concurrent requests sending 10K, which shows the details for Artemis and Fuse:

      PID USER      PR  NI    VIRT    RES    SHR S  %CPU  %MEM     TIME+ COMMAND 
    13775 tdonohue  20   0 8437244 688292  21864 S 105.3   4.2  18:23.80 java (Artemis)
    29153 tdonohue  20   0 7122824 394800  18092 S  30.2   2.4   5:46.77 java (Spring Boot/Fuse)


## Notes

- The default setup is to create a standard Artemis connection factory (non-pooling). To create a pooled connection factory, Spring Boot must be able to find _org.messaginghub.pooled.jms.JmsPoolConnectionFactory_ on the classpath.

