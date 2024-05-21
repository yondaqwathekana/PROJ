package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Astronaut;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AstronautFactoryTest {

    private static final Contact validContact = ContactFactory.createContact("astronaut@example.com", "1234567890");
    private static final Name validName = NameFactory.createName("John", "Doe");

    @Test

    @Order(1)
    void createAstronautWithValidDetails() {
        Astronaut astronaut = AstronautFactory.createAstronaut(1L, 35, "Male", validContact, validName);
        assertNotNull(astronaut);
        assertEquals(1L, astronaut.getAstronautNumber());
        assertEquals(35, astronaut.getAge());
        assertEquals("Male", astronaut.getGender());
        assertEquals(validContact, astronaut.getContact());
        assertEquals(validName, astronaut.getName());
        System.out.println(astronaut);
    }

    @Test
    @Order(2)
    void createAstronautWithInvalidAge() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AstronautFactory.createAstronaut(2L, -5, "Male", validContact, validName);
        });
        String expectedMessage = "Invalid age";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);
    }

    @Test
    @Order(3)
    void createAstronautWithNullContact() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AstronautFactory.createAstronaut(3L, 28, "Female", null, validName);
        });
        String expectedMessage = "Contact cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);
    }

    @Test
    @Order(4)
    void createAstronautWithNullName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AstronautFactory.createAstronaut(4L, 28, "Female", validContact, null);
        });
        String expectedMessage = "Name cannot be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);
    }
}
