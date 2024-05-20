package za.ac.cput.factory;



import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
public class ContactFactory {
    public static Contact createContact(String email, String mobile) {
        return new Contact.Builder()
                .setEmailAddress(email)
                .setPhoneNumber(mobile)

                .build();
    }
}

