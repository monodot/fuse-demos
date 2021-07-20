# fabric-profile-properties

Demonstrates how to use the [Fabric8 Maven plugin][fmp] to create a Fabric profile in a JBoss Fuse server, containing a single [OSGi Config Admin **PID**][felixpid] (collection of properties).

- The properties are defined in: `src/main/fabric8/my-properties.properties`
- A PID called `my-properties` will be created inside the Fabric profile

To deploy, first start up your JBoss Fuse Fabric server and then, on the root node, run:

    mvn fabric8:deploy
    
This will create a Fabric profile called `cleverbuilder-profile`.

To verify that the profile and PID have been deployed - log on to the Karaf console and use the `fabric:profile-display` command. This will show the properties:

    JBossFuse:karaf@root> profile-display cleverbuilder-profile
    Profile id: cleverbuilder-profile
    Version   : 1.0

    ...
    
    Configuration details
    ----------------------------
    PID: io.fabric8.web.contextPath
      null/null fabric-profile-properties


    PID: my-properties
      chips.accompaniment gravy
      worst.film Baby's Day Out
      my.foo bar
      my.bar foo

You can also check the properties from the web console (Hawtio).


[fmp]: https://fabric8.io/gitbook/mavenPlugin.html
[felixpid]: http://felix.apache.org/documentation/subprojects/apache-felix-config-admin.html
