package services;

import com.google.gson.Gson;
import java.io.IOException;
import objects.Person;
import org.apache.http.client.fluent.Request;

public class PersonService {

  public Person findPerson(String url, int id) throws IOException {
    String res = Request.Get(url + "/person/" + id).execute().returnContent().asString();
    Gson gson = new Gson();
    return gson.fromJson(res, Person.class);
  }

}
