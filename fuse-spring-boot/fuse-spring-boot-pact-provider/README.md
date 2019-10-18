# Fuse with Pact contract testing

Demo of integrating Pact contract testing for a Fuse 7 application. This uses the `pact-jvm-provider-spring` component.

There is a Camel RouteBuilder class, which defines a REST service using the _servlet_ component.

The test class, `CatalogueServiceContractTest`, uses the `SpringPactRunner`. It starts up the application context and fires up the web service on a random port. It uses Camel Mock endpoint to stub the call to the downstream system (which may be a database or object store) and return mock data, which will be populated in the response and satisfy the contract.

This example is for applications which align to the Red Hat Fuse 7 BOM, and are therefore using Spring Boot 1.5.x, and JUnit 4. 💃

To run:

    mvn clean test

This will:

- Run the test class
- Read the pact JSON file in `pacts/`
- Start up the application
- Simulate each _interaction_ in the contract and check for the expected response
- In each test case, a Camel Mock endpoint is configured to return the expected data, as if it were coming from a DB.

Look for output like this in the logs, which shows the results of the Pact test:

    returns a response which
      has status code 200 (OK)
      includes headers
        "Content-Type" with value "application/json" (OK)
      has a matching body (OK)



Inspired by this [excellent repo from Christian Draeger][1].

[1]: https://github.com/christian-draeger/pact-example

