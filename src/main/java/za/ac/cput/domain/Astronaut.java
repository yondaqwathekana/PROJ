package za.ac.cput.domain;


import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Astronaut {
    @Id
    private long astronautNumber;
    private int age;
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_email", referencedColumnName = "emailAddress")
    private Contact contact;

    @Embedded
    private Name name;

    public Astronaut() {}

    public Astronaut(Builder builder) {
        this.astronautNumber = builder.astronautNumber;
        this.age = builder.age;
        this.gender = builder.gender;
        this.contact = builder.contact;
        this.name = builder.name;
    }

    public long getAstronautNumber() { return astronautNumber; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public Contact getContact() { return contact; }
    public Name getName() { return name; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Astronaut astronaut = (Astronaut) object;
        return astronautNumber == astronaut.astronautNumber &&
                age == astronaut.age &&
                Objects.equals(gender, astronaut.gender) &&
                Objects.equals(contact, astronaut.contact) &&
                Objects.equals(name, astronaut.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(astronautNumber, age, gender, contact, name);
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "astronautNumber: " + astronautNumber +
                ", age: " + age +
                ", gender: '" + gender + '\'' +
                ", contact: " + contact +
                ", name: " + name +
                '}';
    }

    public static class Builder {
        private long astronautNumber;
        private int age;
        private String gender;
        private Contact contact;
        private Name name;

        public Builder setAstronautNumber(long astronautNumber) {
            this.astronautNumber = astronautNumber;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder setName(Name name) {
            this.name = name;
            return this;
        }

        public Builder copy(Astronaut astronaut) {
            this.astronautNumber = astronaut.astronautNumber;
            this.age = astronaut.age;
            this.gender = astronaut.gender;
            this.contact = astronaut.contact;
            this.name = astronaut.name;
            return this;
        }

        public Astronaut build() { return new Astronaut(this); }
    }
}
