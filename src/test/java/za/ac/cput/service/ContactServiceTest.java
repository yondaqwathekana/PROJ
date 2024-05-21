package za.ac.cput.service;

import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Contact;
import za.ac.cput.factory.ContactFactory;

import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    // Define test data
    private static final Contact contact1 = ContactFactory.createContact("contact1@example.com", "1234567890");
    private static final Contact contact2 = ContactFactory.createContact("contact2@example.com", "0987654321");

    @Test
    @Order(1)
    void createContact() {
        // Insert valid data
        Contact createdContact1 = contactService.create(contact1);
        assertNotNull(createdContact1);

        Contact createdContact2 = contactService.create(contact2);
        assertNotNull(createdContact2);

        // Ensure created contact matches expected data
        assertEquals(contact1.getEmailAddress(), createdContact1.getEmailAddress());
        assertEquals(contact1.getPhoneNumber(), createdContact1.getPhoneNumber());

        assertEquals(contact2.getEmailAddress(), createdContact2.getEmailAddress());
        assertEquals(contact2.getPhoneNumber(), createdContact2.getPhoneNumber());
    }

    @Test
    @Order(2)
    void readContact() {
        Contact foundContact1 = contactService.read("contact1@example.com");
        assertNotNull(foundContact1);
        assertEquals(contact1.getEmailAddress(), foundContact1.getEmailAddress());

        Contact foundContact2 = contactService.read("contact2@example.com");
        assertNotNull(foundContact2);
        assertEquals(contact2.getEmailAddress(), foundContact2.getEmailAddress());
    }

    @Test
    @Order(3)
    void updateContact() {
        Contact updatedContact = new Contact.Builder().copy(contact1).setPhoneNumber("1111111111").build();
        Contact updatedContactResult = contactService.update(updatedContact);
        assertNotNull(updatedContactResult);
        assertEquals("1111111111", updatedContactResult.getPhoneNumber());
    }

    @Test
    @Order(4)
    void deleteContact() {
        Contact contactToDelete = contactService.read("contact2@example.com");
        assertNotNull(contactToDelete);
        contactService.delete(contactToDelete.getEmailAddress());
        assertThrows(NoSuchElementException.class, () -> contactService.read("contact2@example.com"));
    }

    @Test
    @Order(5)
    void getAllContacts() {
        Set<Contact> allContacts = contactService.getAll();
        assertNotNull(allContacts);
        assertTrue(allContacts.size() > 0);
    }
}
