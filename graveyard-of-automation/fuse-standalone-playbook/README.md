# fuse-standalone

Provisions single JBoss Fuse instance (and optionally start EC2 instances) and deploys bundles.

**Pre-requisites: see the README.md in the root of this repo**

## To run

Firstly, provision AWS EC2 instances:

    ansible-playbook aws-launch.yaml

Then, to run (Ansible will use the `ec2.py` inventory sync script to discover EC2 hosts to provision):

    ansible-playbook -i ec2.py site.yaml

