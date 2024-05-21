package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import za.ac.cput.domain.Astronaut;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.factory.AstronautFactory;
import za.ac.cput.factory.ContactFactory;
import za.ac.cput.factory.NameFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AstronautControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:8080/astronauts";
    private static Astronaut astronaut1, astronaut2;

    @BeforeAll
    public static void setUp() {
        Contact contact1 = ContactFactory.createContact("astronaut1@example.com", "1234567890");
        Name name1 = NameFactory.createName("John", "Doe");
        astronaut1 = AstronautFactory.createAstronaut(1L, 35, "Male", contact1, name1);

        Contact contact2 = ContactFactory.createContact("astronaut2@example.com", "0987654321");
        Name name2 = NameFactory.createName("Jane", "Smith");
        astronaut2 = AstronautFactory.createAstronaut(2L, 40, "Female", contact2, name2);
    }

    @Test
    @Order(1)
    void createAstronaut() {
        ResponseEntity<Astronaut> postResponse1 = restTemplate.postForEntity(BASE_URL + "/create", astronaut1, Astronaut.class);
        ResponseEntity<Astronaut> postResponse2 = restTemplate.postForEntity(BASE_URL + "/create", astronaut2, Astronaut.class);

        assertEquals(HttpStatus.CREATED, postResponse1.getStatusCode());
        assertEquals(HttpStatus.CREATED, postResponse2.getStatusCode());

        Astronaut createdAstronaut1 = postResponse1.getBody();
        Astronaut createdAstronaut2 = postResponse2.getBody();

        assertNotNull(createdAstronaut1);
        assertNotNull(createdAstronaut2);

        assertEquals(astronaut1.getAstronautNumber(), createdAstronaut1.getAstronautNumber());
        assertEquals(astronaut1.getAge(), createdAstronaut1.getAge());
        assertEquals(astronaut1.getGender(), createdAstronaut1.getGender());
        assertEquals(astronaut1.getContact(), createdAstronaut1.getContact());
        assertEquals(astronaut1.getName(), createdAstronaut1.getName());

        assertEquals(astronaut2.getAstronautNumber(), createdAstronaut2.getAstronautNumber());
        assertEquals(astronaut2.getAge(), createdAstronaut2.getAge());
        assertEquals(astronaut2.getGender(), createdAstronaut2.getGender());
        assertEquals(astronaut2.getContact(), createdAstronaut2.getContact());
        assertEquals(astronaut2.getName(), createdAstronaut2.getName());
    }

    @Test
    @Order(2)
    void readAstronaut() {
        String readURL = BASE_URL + "/read/" + astronaut1.getAstronautNumber();
        ResponseEntity<Astronaut> response = restTemplate.getForEntity(readURL, Astronaut.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(astronaut1.getAstronautNumber(), response.getBody().getAstronautNumber());
    }

    @Test
    @Order(3)
    void updateAstronaut() {
        Astronaut updatedAstronaut = new Astronaut.Builder().copy(astronaut2).setAge(42).build();
        HttpEntity<Astronaut> requestEntity = new HttpEntity<>(updatedAstronaut);
        ResponseEntity<Astronaut> response = restTemplate.exchange(BASE_URL + "/update", HttpMethod.PUT, requestEntity, Astronaut.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(42, response.getBody().getAge());
    }

    @Test
    @Order(4)
    void deleteAstronaut() {
        String deleteURL = BASE_URL + "/delete/" + astronaut1.getAstronautNumber();
        restTemplate.delete(deleteURL);

        ResponseEntity<Astronaut> response = restTemplate.getForEntity(BASE_URL + "/read/" + astronaut1.getAstronautNumber(), Astronaut.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @Order(5)
    void getAllAstronauts() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(BASE_URL + "/getAll", HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
