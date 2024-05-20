package za.ac.cput.factory;



import za.ac.cput.domain.Name;

public class NameFactory {
    public static Name createName(String firstName, String lastName) {
        return new Name.Builder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }
}
