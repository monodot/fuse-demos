# fuse-patching

An example walkthrough for patching a running JBoss Fuse container.

You will need the following from the Red Hat Customer Portal:

- The latest JBoss Fuse GA distribution (e.g. `jboss-fuse-karaf-6.3.0.redhat-xxx.zip`).
- Patch Mechanism upgrade distribution (_Red Hat JBoss Fuse 6.x Rollup N Patch Management Package_ - e.g. `patch-management-package-6.3.0.redhat-yyy.zip`) (if applicable to this Fuse version)
- Latest Rollup Patch distribution (_Red Hat JBoss Fuse 6.x Rollup N on Karaf_ - e.g. `jboss-fuse-karaf-6.3.0.redhat-yyy.zip`))

# Steps

1. Use the Ansible playbook provided to provision a standalone Fuse container, using the GA release.
2. Copy the Patch Mechanism upgrade distribution to the remote server and follow the steps in [Configuring and Running JBoss Fuse - Chapter 19. Applying Patches][1].
3. Copy the Rollup Patch distribution to the remote server and follow the instructions for applying a Rollup Patch, which can be found in [the same guide][1].

[1]: https://access.redhat.com/documentation/en-us/red_hat_jboss_fuse/6.3/html/configuring_and_running_jboss_fuse/esbruntimepatching

