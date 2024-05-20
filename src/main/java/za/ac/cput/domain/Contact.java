package za.ac.cput.domain;



import jakarta.persistence.*;
import java.util.Objects;




@Entity
public class Contact {
    @Id
    private String emailAddress;
    private String phoneNumber;

    protected Contact() {}

    private Contact(Builder builder) {
        this.emailAddress = builder.emailAddress;
        this.phoneNumber = builder.phoneNumber;
    }

    public String getEmailAddress() { return emailAddress; }
    public String getPhoneNumber() { return phoneNumber; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(emailAddress, contact.emailAddress) &&
                Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress, phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static class Builder {
        private String emailAddress;
        private String phoneNumber;

        public Builder setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder copy(Contact contact) {
            this.emailAddress = contact.emailAddress;
            this.phoneNumber = contact.phoneNumber;
            return this;
        }

        public Contact build() {
            return new Contact(this);
        }
    }
}
