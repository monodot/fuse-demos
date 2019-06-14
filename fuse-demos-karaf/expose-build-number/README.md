# expose-build-number

Example of writing a build number into a property and then exposing this through a healthcheck endpoint.

This example is designed for Fuse on Apache Karaf.

The healthcheck endpoint is just a simple Jetty on port 8093.

Firstly build, giving your custom build number using the `project.buildnumber` property:

    $ mvn -Dproject.buildnumber=12345 clean install

Then deploy into Fuse on Apache Karaf:

    karaf@root> osgi:install mvn:xyz.tomd.fusedemos/expose-build-number/1.0-SNAPSHOT

Finally observe the build number returned from an endpoint:

    $ curl http://localhost:8093/buildnumber
    12345


