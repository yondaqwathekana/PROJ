package za.ac.cput.factory;

import org.springframework.context.annotation.EnableMBeanExport;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Astronaut;
import za.ac.cput.domain.Name;


import za.ac.cput.domain.Contact;
import za.ac.cput.domain.Name;
import za.ac.cput.domain.Astronaut;

public class AstronautFactory {
    public static Astronaut createAstronaut(long astronautNumber, int age, String gender, Contact contact, Name name) {
        return new Astronaut.Builder()
                .setAstronautNumber(astronautNumber)
                .setAge(age)
                .setGender(gender)
                .setContact(contact)
                .setName(name)
                .build();
    }
}
