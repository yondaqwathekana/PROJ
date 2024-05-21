package za.ac.cput.factory;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import za.ac.cput.domain.Contact;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactFactoryTest {

    @Test
    @Order(1)
    void createContactWithValidDetails() {
        Contact contact = ContactFactory.createContact("contact@example.com", "1234567890");
        assertNotNull(contact);
        assertEquals("contact@example.com", contact.getEmailAddress());
        assertEquals("1234567890", contact.getPhoneNumber());
        System.out.println(contact);
    }

    @Test
    @Order(2)
    void createContactWithInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ContactFactory.createContact("invalid-email", "1234567890");
        });
        String expectedMessage = "Invalid email address";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);
    }

    @Test
    @Order(3)
    void createContactWithInvalidPhoneNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ContactFactory.createContact("contact@example.com", "invalid-phone");
        });
        String expectedMessage = "Invalid phone number";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        System.out.println(actualMessage);
    }
}
