package get;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import java.io.IOException;
import objects.Burner;
import org.junit.Rule;
import org.junit.Test;
import services.BurnerService;

public class BurnerTest {

@Rule
public PactProviderRuleMk2 mockProvider
    = new PactProviderRuleMk2("burner", "localhost", 8000, this);

  @Pact(provider="burner", consumer="military_consumer")
  public RequestResponsePact genResponseData(PactDslWithProvider builder) {
    return builder
        .given("hardware exists")
        .uponReceiving("request for a specific hardware")
        .path("/hardware/1")
        .method("GET")
        .willRespondWith()
        .status(200)
        .body("{\"model\": \"1911 45. ACP\"}")
        .toPact();
  }

  @PactVerification("burner")
  @Test
  public void get_burner() throws IOException {
    BurnerService bs = new BurnerService();
    Burner resp = bs.findBurner("http://localhost:8000", 1);
    assertThat(resp.getModel(), is("1911 45. ACP"));
    System.out.println("Found burner --> " + resp.getModel());
  }

}
