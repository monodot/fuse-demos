![Build Status][buildstatus]
![Project licence][licence]
![Egg status][eggs]

# fuse-demos

Scripts, resources and walkthroughs for demonstrating specific functionality/use-cases in JBoss Fuse. Particularly focused towards quickly-reproducable cloud (AWS) demos.

**NOTE: These scripts come without warranty, and most definitely should NOT be used in a production environment.**

- **ansible-playbook-fuse** - (Git submodule) Ansible roles for provisioning JBoss Fuse servers
- **ansible-ec2-provision** - launches AWS EC2 resources for JBoss Fuse servers
- **fuse-patching** - a very brief guide to the JBoss Fuse patching process
- **fuse-standalone** - Provisions a single Fuse instance on AWS and (optionally) deploys some demo features

## Pre-requisites

Check that you have satisfied this requirements first before running the playbooks:

- Ansible - I use `pip install ansible`
- Download the appropriate JBoss Fuse distribution (zip) and copy to `/tmp`

(Optionally) If you're using the playbooks to create EC2 instances on AWS:

- Get the `aws` command line tool for your operating system, then use `aws configure` to set up your AWS access keys
- Edit the `ansible.cfg` file and set the location of your private key for SSHing to your EC2 instances

## Notes on using AWS with these demos

- AWS will release the public IP address of an EC2 instance when it's stopped or terminated. This means that a stopped instance will receive a new public IP address when it's restarted. These provisioning scripts are intended only for creating ephemeral Fuse instances, not for creating permanent infrastructure.
- The provisioning scripts do not terminate EC2 instances. Make sure you terminate instances when you're finished with them. Check your AWS console regularly unless you like huge bills (Clinton excepted)
- The EBS volumes backing the Fuse instances are not set to automatically be removed on termination. After terminating EC2 instances, make sure to check your AWS console, and delete any orphaned EBS volumes to avoid extra charges.


[buildstatus]: https://api.travis-ci.org/monodot/fuse-demos.svg?branch=master
[licence]: https://img.shields.io/github/license/monodot/fuse-demos.svg
[eggs]: https://img.shields.io/badge/eggs-baked-yellow.svg
