package za.ac.cput.service;

import za.ac.cput.domain.Astronaut;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.AstronautFactory;
import za.ac.cput.factory.ContactFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;




import za.ac.cput.domain.Name;

import za.ac.cput.factory.NameFactory;

import static org.junit.jupiter.api.Assertions.*;



@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AstronautServiceTest {

    @Autowired
    private AstronautService astronautService;

    @Autowired
    private ContactService contactService;

    @Test
    @Order(1)
    void createAstronaut() {
        // Create contact
        Contact contact = ContactFactory.createContact("astronaut@example.com", "1234567890");
        Contact createdContact = contactService.create(contact);
        assertNotNull(createdContact);

        // Create name
        Name name = NameFactory.createName("John", "Doe");

        // Create astronaut with the created contact and name
        Astronaut astronaut = AstronautFactory.createAstronaut(1L, 35, "Male", createdContact,name);
        Astronaut createdAstronaut = astronautService.create(astronaut);
        assertNotNull(createdAstronaut);

        // Ensure created astronaut matches expected data
        assertEquals(astronaut.getAstronautNumber(), createdAstronaut.getAstronautNumber());
        assertEquals(astronaut.getAge(), createdAstronaut.getAge());
        assertEquals(astronaut.getGender(), createdAstronaut.getGender());
        assertEquals(astronaut.getContact(), createdAstronaut.getContact());
        assertEquals(astronaut.getName(), createdAstronaut.getName());
    }

    @Test
    @Order(2)
    void readAstronaut() {
        Astronaut foundAstronaut = astronautService.read("1");
        assertNotNull(foundAstronaut);
        System.out.println(foundAstronaut);
    }

    @Test
    @Order(3)
    void updateAstronaut() {
        Astronaut foundAstronaut = astronautService.read("1");
        assertNotNull(foundAstronaut);

        Astronaut updatedAstronaut = new Astronaut.Builder().copy(foundAstronaut).setAge(36).build();
        updatedAstronaut = astronautService.update(updatedAstronaut);
        assertEquals(36, updatedAstronaut.getAge());
        System.out.println(updatedAstronaut);
    }

    @Test
    @Order(5)
    void getAllAstronauts() {
        Set<Astronaut> allAstronauts = astronautService.getAll();
        assertNotNull(allAstronauts);
        assertTrue(allAstronauts.size() > 0);
        System.out.println(allAstronauts);
    }
}
