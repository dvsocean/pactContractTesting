package services;

import com.google.gson.Gson;
import java.io.IOException;
import objects.Burner;
import org.apache.http.client.fluent.Request;

public class BurnerService {

  public Burner findBurner(String url, int id) throws IOException {
    String res = Request.Get(url + "/hardware/" + id).execute().returnContent().asString();
    Gson gson = new Gson();
    return gson.fromJson(res, Burner.class);
  }

}
