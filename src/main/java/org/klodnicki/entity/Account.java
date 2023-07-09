package org.klodnicki.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(mappedBy = "account")
    private List<LendingInformation> lendingInformationAboutAccountList = new ArrayList<>();
    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(name = "second_name")
    private String secondName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    @Column(nullable = false, unique = true)
    private String pesel;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column
    private String email;
    @Column(nullable = false)
    private String address;

    public Account() {
    }

    public Account(String firstName, String secondName, String lastName, String pesel, String phoneNumber, String email, String address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(pesel, account.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", lendingInformationList=" + lendingInformationAboutAccountList +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPesel() {
        return pesel;
    }
}
