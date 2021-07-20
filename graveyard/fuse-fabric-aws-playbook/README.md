# fuse-fabric

Provisions JBoss Fuse in a Fabric (optionally also creating EC2 instances) and deploys bundles.

**Pre-requisites: see the README.md in the root of this repo**

Notes:

- _Inventories_ are used as a way of deploying different example Fabric configurations (i.e. scenarios)
- Each subdirectory under `inventories/` defines a different configuration
- The 'default' inventory provisions a simple Fuse Fabric setup.

CONT: Create passwordless SSH between EC2 instances. Run fabric:create command. Then container-create-ssh to provision new Fabric containers on the fuse-fabric-default-container nodes. Figure out how to use the ec2 inventory sync script, in addition to inventories/default

## Scenarios

- **default**: 2 x container nodes, creates a fabric on the first node, and then provisions a Fabric container on the second node using `container-create-ssh` (work in progress!)

## To run

Firstly, provision AWS EC2 instances:

    $ ansible-playbook -i inventories/default aws-provision.yml

Then, to run (Ansible implicitly loads and runs the `ec2.py` module to discover and tag EC2 hosts):

    $ ansible-playbook -i inventories/default site.yml

Subsequent manual steps:

    user@workstation$ ssh -i ~/.ssh/ansible.pem centos@fabric-root-node
    centos@fuse$ /opt/fuse/.../bin/client
    karaf@root> fabric:container-create-ssh --host ip-172-31-27-51.eu-west-1.compute.internal --resolver manualip --manual-ip=172.31.27.51 --path /opt/rh/containers --user centos --jvm-opts "$JVM_APP_OPTS -Djava.rmi.server.hostname=172.31.27.51" --profile default fabric-002

### Other useful commands

To list all resources in AWS:

    $ cd ../aws-inventory-ansible
    $ ./ec2.py
    
To reset the cached list of EC2 instances:

    $ ./ec2.py --refresh-cache

