package com.ascend.example.demospringboot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.net.URL;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {

//bind the above RANDOM_PORT
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getHello() throws Exception {

      ResponseEntity<String> response = restTemplate.getForEntity(
          new URL("http://localhost:" + 8080 + "/").toString(), String.class);
      assertEquals("Hello Controller", response.getBody());

  }
  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
