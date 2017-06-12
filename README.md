# fuse-demos

Scripts, resources and walkthroughs for demonstrating specific functionality/use-cases in JBoss Fuse. Particularly focused towards quickly-reproducable cloud (AWS) demos.

**NOTE: These scripts come without warranty, and most definitely should NOT be used in a production environment.**

- **ansible-playbook-fuse** - (Git submodule) Ansible roles for provisioning JBoss Fuse servers
- **fuse-patch** - a very brief guide to the JBoss Fuse patching process
- **fuse-standalone** - Provisions a single Fuse instance on AWS and (optionally) deploys some demo features

## Notes on using AWS with these demos

- AWS will release the public IP address of an EC2 instance when it's stopped or terminated. This means that a stopped instance will receive a new public IP address when it's restarted. These provisioning scripts are intended only for creating ephemeral Fuse instances, not for creating permanent infrastructure.
- The provisioning scripts do not terminate EC2 instances. Make sure you terminate instances when you're finished with them. Check your AWS console regularly unless you like huge bills (Clinton excepted)
- The EBS volumes backing the Fuse instances are not set to automatically be removed on termination. After terminating EC2 instances, make sure to check your AWS console, and delete any orphaned EBS volumes to avoid extra charges.