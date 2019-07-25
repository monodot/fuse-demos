# Fuse with Spring Cloud Contract for PACT testing

Demo of integrating Spring Cloud Contract with a Fuse 7 application.

Includes a Camel RouteBuilder class which defines a REST service, and a test base class, `CatalogueServiceBase`, which spins up the application context, fires up the web service on a random port, and configures mock data which will satisfy the contract.

This example is for Fuse 7, and therefore Spring Boot 1.5.x. So we use the _Edgware_ release train for Spring Cloud (see [Release Trains on this page][1]).

To run:

    mvn clean install

This will:

- Read the pact JSON file in `src/test/resources/contracts/...`
- Generate a test class `CatalogueServiceTest` which extends `CatalogueServiceBase`
- Run the generated test case
- If the response returned by the service doesn't match the contract, the test will fail.


[1]: https://spring.io/projects/spring-cloud
