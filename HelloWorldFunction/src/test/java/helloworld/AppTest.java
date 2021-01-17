package helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

public class AppTest {
  @Test
  public void successfulResponse() {
    App app = new App();

    // Create the Input
    final HashMap<String, String> input_headers = new HashMap<>();
    input_headers.put("Content-Type", "text/plain");
    final HashMap<String, Object> input = new HashMap<>();
    input.put("headers", Collections.unmodifiableMap(input_headers));
    input.put("body", "This is a test. My name is LambdaTestFunction, and I just watched the T.V. show Wanda Vision.");

    // Context
    final TestContext context = new TestContext();

    // Invoke the App
    final GatewayResponse result = app.handleRequest(Collections.unmodifiableMap(input), context);

    // Assert Results
    assertEquals(result.getStatusCode(), 200);
    assertEquals(result.getHeaders().get("Content-Type"), "application/xml");
    String content = result.getBody();
    assertNotNull(content);
    assertTrue(content.contains("GateDocument version=\"3\""));
  }
}
