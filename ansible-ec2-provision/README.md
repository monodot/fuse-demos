# ansible-ec2-provision

Ansible playbook for provisioning AWS EC2 instances suitable for running JBoss Fuse.

- EC2 Instance Type: `t2.medium`
- AMI: _Red Hat Enterprise Linux 7.3 (HVM), SSD Volume Type_

Pre-requisites:

1. Create an AWS account, and set up billing if required.
2. Generate a Key Pair and install it on the machine where you will be running Ansible:
    - From EC2 Dashboard &rarr; Key Pairs &rarr; Create Key Pair.
    - Give the Key Pair a name, e.g. `ansible` and click _Create_ to create the key and download the key file (e.g. `ansible.pem`).
    - Move the downloaded `.pem` file to `$HOME/.ssh/`
    - Secure the key file: `chmod 600 $HOME/.ssh/ansible.pem`
    - **Note:** The Ansible playbook assumes a key name of `ansible`. If you have chosen a different name, update the value of `ec2_keypair` in `group_vars/aws.yaml`.

--

To run standalone:

    ansible-playbook -i hosts create-ec2-instance.yaml
    
