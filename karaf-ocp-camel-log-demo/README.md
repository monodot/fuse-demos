# karaf-ocp-camel-log-demo

This quickstart shows a simple Apache Camel application that logs a message to the server log every 5th second.

This example is implemented using solely the XML DSL (there is no Java code). The source code is provided in the following XML file `src/main/resources/OSGI-INF/blueprint/camel-log.xml`.
It also shows how Karaf assembly files can be overridden using resources from `src/main/resources/assembly/`. The included sample log file `etc/org.ops4j.pax.logging.cfg` sets the log level to DEBUG.


### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- Your system is configured for Fabric8 Maven Workflow, if not you can find a [Get Started Guide](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/single/red-hat-jboss-fuse-integration-services-20-for-openshift/)

The example can be built and deployed using:

    mvn fabric8:deploy

When the example runs in fabric8, you can use the OpenShift client tool to inspect the status

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the openshift [web console](https://docs.openshift.com/container-platform/3.3/getting_started/developers_console.html#developers-console-video) to manage the running pods, and view logs and much more.


