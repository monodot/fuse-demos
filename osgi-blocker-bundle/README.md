# blocker-bundle

This OSGi bundle never completes the `start()` method in its `Activator`, and therefore never starts up.

Useful for showing how bundles that fail to start are represented in Fuse.

Based on Chapter 10 of _OSGi in Depth_ by Alexandre de Castro Alves.

Created using the Maven archetype: `org.apache.karaf.archetypes:karaf-bundle-archetype`.

## To run

**Caution:** installing this bundle will cause the Karaf shell to become completely unresponsive!

First compile the project:

    mvn clean install

Then, in Fuse:

    osgi:install -s mvn:com.example/blocker-bundle/1.0-SNAPSHOT

