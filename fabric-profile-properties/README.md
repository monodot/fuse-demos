# fabric-profile-properties

Demonstrates how to use the Fabric Maven plugin to create a Fabric profile which contains a single OSGi Config Admin **PID** (collection of properties).

- The properties are defined in: `src/main/fabric8/my-properties.properties`
- This will create a PID inside the Fabric profile called `my-properties`

To deploy, first start a local Fuse Fabric server and then run:

    mvn fabric8:deploy
    
This will create a Fabric profile called `cleverbuilder-profile`.

To verify that the profile and PID have been deployed - use `profile-display` from the Fabric root container command line:

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


    