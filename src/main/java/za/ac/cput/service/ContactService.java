package za.ac.cput.service;

import za.ac.cput.domain.Contact;
import za.ac.cput.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class ContactService implements IContactService {

    private final ContactRepository repository;

    @Autowired
    public ContactService(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact create(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact read(String email) {
        return repository.findById(email)
                .orElseThrow(() -> new NoSuchElementException("Contact with email " + email + " does not exist"));
    }

    @Override
    public Contact update(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact delete(String email) {
        Contact contactToDelete = read(email);
        repository.delete(contactToDelete);
        return contactToDelete;
    }

    @Override
    public Set<Contact> getAll() {
        return new HashSet<>(repository.findAll());
    }
}

