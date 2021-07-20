# karaf-assembly

Demonstration of using the Karaf assembly approach.

This app was initially created using an archetype:

    mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate \
      -DarchetypeCatalog=https://maven.repository.redhat.com/ga/io/fabric8/archetypes/archetypes-catalog/2.2.0.fuse-730042-redhat-00002/archetypes-catalog-2.2.0.fuse-730042-redhat-00002-archetype-catalog.xml \
      -DarchetypeGroupId=org.jboss.fuse.fis.archetypes \
      -DarchetypeArtifactId=karaf-camel-log-archetype \
      -DarchetypeVersion=2.2.0.fuse-730042-redhat-00002

### Building

The example can be built with

    mvn clean install

To compile and build a container image (for OpenShift)

    mvn clean install -Popenshift

### Features

- Add any custom `.cfg` files into `src/main/resources/assembly/etc` to get them copied into the `etc/` folder in the assembly.
-

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


