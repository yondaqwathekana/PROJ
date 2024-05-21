package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Name;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class NameFactoryTest {

    @Test
    @Order(1)
    void createNameWithValidDetails() {
        Name name = NameFactory.createName("John", "Doe");
        assertNotNull(name);
        assertEquals("John", name.getFirstName());
        assertEquals("Doe", name.getLastName());
        System.out.println(name);
    }

    @Test
    @Order(2)
    void createNameWithNullFirstName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            NameFactory.createName(null, "Doe");
        });
        String expectedMessage = "First name cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);
    }

    @Test
    @Order(3)
    void createNameWithEmptyLastName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            NameFactory.createName("John", "");
        });
        String expectedMessage = "Last name cannot be null or empty";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);
    }
}
