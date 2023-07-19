package com.example.api.perf.test.integrationTests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Before
    public void setup() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        restTemplate.exchange(createURLWithPort("/students"), HttpMethod.POST, entity, String.class);
    }

    @Test
    public void testCreateStudent() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/students"), HttpMethod.POST, entity, String.class);
        String actual = Objects.requireNonNull(response.getHeaders().get(HttpHeaders.LOCATION)).get(0);

        assertTrue(actual.contains("/students"));
    }

    @Test
    public void testRetrieveStudent() throws Exception {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/students/1"), HttpMethod.GET, entity, String.class);
        String expected = "{\"id\":1,\"name\":\"Paddy Fox\",\"description\":\"Class 9-5\"}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String createURLWithPort(String uri) {

        return "http://localhost:" + port + uri;
    }
}

