# fuse-standalone

Provisions single JBoss Fuse instance (and optionally start EC2 instances) and deploys bundles.

Pre-requisites:

- Ansible - I use `pip install ansible`
- Download the appropriate JBoss Fuse distribution (zip) and copy to `/tmp`
- Get the `aws` command line tool, then use `aws configure` to set up your AWS access keys
- Edit the `ansible.cfg` file and set the location of your private key for SSHing to your EC2 instances

## To run

Firstly, provision AWS EC2 instances:

    ansible-playbook aws-launch.yaml

Then, to run (Ansible will use the `ec2.py` inventory sync script to discover EC2 hosts to provision):

    ansible-playbook -i ec2.py site.yaml

