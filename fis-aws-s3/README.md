# fis-aws-s3

A Fuse Integration Services (FIS) project for OpenShift which reads files from Amazon S3.

## Preparation steps

Create an AWS S3 bucket (you'll need to have the AWS CLI installed for this):

    $ aws s3 mb s3://<bucket-name> --profile <aws-profile-name>

**NB:** Your S3 bucket name must be unique.

## To run locally (AWS S3)

First put a file in your bucket:

    $ echo "Hello" >> hello.txt
    $ aws s3 cp hello.txt s3://<bucket-name> --profile <aws-profile-name>

Check the file is there:

    $ aws s3 ls s3://<bucket-name> --profile <aws-profile-name>
    2018-01-26 12:34:39         12 hello.txt

Now run the app locally:

    $ mvn spring-boot:run -Daws.accessKey=<aws-access-key> -Daws.secretKey=<aws-secret-key>

Watch the logs from Camel as it should receive the file:

    11:00:53.147 [main] INFO  o.a.coyote.http11.Http11NioProtocol - Starting ProtocolHandler ["http-nio-0.0.0.0-8080"]
    11:00:53.148 [main] INFO  o.a.tomcat.util.net.NioSelectorPool - Using a shared selector for servlet write/read
    11:00:53.149 [main] INFO  o.s.b.c.e.t.TomcatEmbeddedServletContainer - Tomcat started on port(s): 8080 (http)
    11:00:53.154 [main] INFO  com.cleverbuilder.Application - Started Application in 10.349 seconds (JVM running for 25.255)
    11:07:08.593 [Camel (camel) thread #0 - aws-s3://bucketybucket] INFO  main-route - >>> Hello

You can also check that the file has been deleted from S3 (this is the default behaviour):

    $ aws s3 ls s3://<bucket-name> --profile <aws-profile-name>
    $

