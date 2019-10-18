# Fuse with Spring Cloud Contract

Demo of integrating Spring Cloud Contract with a Fuse 7 application to test Pact contracts.

Includes a Camel RouteBuilder class which defines a REST service, and a test base class, `CatalogueServiceBase`, which spins up the application context, fires up the web service on a random port, and configures mock data which will satisfy the contract.

This example is for Fuse 7, and therefore Spring Boot 1.5.x. So we use the _Edgware_ release train for Spring Cloud (see [Release Trains on this page][1]).

To run:

    mvn clean install

This will:

- Read the pact JSON file in `src/test/resources/contracts/...`
- Generate a test class `CatalogueServiceTest` (in `target/generated-test-sources`) which extends `CatalogueServiceBase`. This is a custom class for our app, which bootstraps and controls the application while under test, using a Camel Mock component.
- Run the generated test case
- If the response returned by the service doesn't match the contract, the test will fail.

Look for the following in the logs which shows the auto-generated tests completion:

    2019-07-26 22:05:37.839  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : ********************************************************************************
    2019-07-26 22:05:37.839  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : Testing done: validate_pact_0(xyz.tomd.fusedemos.springcloudcontract.CatalogueServiceTest)
    2019-07-26 22:05:37.839  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : Took: 0.821 seconds (821 millis)
    2019-07-26 22:05:37.840  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : ********************************************************************************
    2019-07-26 22:05:37.862  INFO 9589 --- [  XNIO-2 task-2] search-route                             : Received a search request
    2019-07-26 22:05:37.871  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : ********************************************************************************
    2019-07-26 22:05:37.871  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : Testing done: validate_pact_1(xyz.tomd.fusedemos.springcloudcontract.CatalogueServiceTest)
    2019-07-26 22:05:37.871  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : Took: 0.029 seconds (29 millis)
    2019-07-26 22:05:37.872  INFO 9589 --- [           main] x.t.f.s.CatalogueServiceTest             : ********************************************************************************



[1]: https://spring.io/projects/spring-cloud
