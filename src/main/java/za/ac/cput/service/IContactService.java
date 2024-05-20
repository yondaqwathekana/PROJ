package za.ac.cput.service;

import za.ac.cput.domain.Contact;

import java.util.Set;




import java.util.Set;

public interface IContactService extends IService<Contact, String> {

    Contact delete(String email);
    Set<Contact> getAll();
}
