# fabric-profile-child

Demonstrates creating a Fabric profile, using properties inherited from a _Maven parent project_. This shows how Maven inheritance can be used to share lists of features in Fabric profiles.

To deploy:

    mvn fabric8:deploy

This creates a Fabric profile called `my-profile`, which inherits a list of features from the parent Maven project (_fabric-profile-parent_), by including the Maven property `my.featurelist`, which is defined in the Maven parent. 

The resulting list of features should be: `camel-twitter camel-swagger camel-rabbitmq camel-dropbox`

To verify in Fuse:

    profile:display my-profile
