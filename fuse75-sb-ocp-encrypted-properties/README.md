# fuse-spring-boot-encrypted-properties

Encrypting application properties for a Fuse application.

To encrypt a property, first [install the Spring CLI][springcli]. Then run:

    spring install org.springframework.cloud:spring-cloud-cli:2.1.0.RELEASE
    spring encrypt password_to_encrypt --key your_secret_key

To run:

    export ENCRYPT_KEY=ABC123ABC123ABC123
    mvn clean spring-boot:run

This should output the super-secret password in the console log.

[springcli]: https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-cli/2.1.7.RELEASE/spring-boot-cli-2.1.7.RELEASE-bin.tar.gz
