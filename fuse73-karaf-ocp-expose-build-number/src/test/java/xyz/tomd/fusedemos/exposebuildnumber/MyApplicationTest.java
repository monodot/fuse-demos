package xyz.tomd.fusedemos.exposebuildnumber;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.junit.Test;

public class MyApplicationTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "/OSGI-INF/blueprint/camel-context.xml";
    }

    @Test
    public void testReturnsProperty() throws Exception {
        assertTrue(context.getStatus().isStarted());

        HttpClient httpClient = new HttpClient();
        httpClient.start();

        ContentResponse response = httpClient.GET("http://localhost:8093/buildnumber");
        System.out.println(response);

        assertEquals(200, response.getStatus());
        assertEquals("11111", response.getContentAsString());
    }

}
