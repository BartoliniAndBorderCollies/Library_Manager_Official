package org.klodnicki.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany (fetch = FetchType.LAZY, //jedno konto może miec wiele ksiązek
    mappedBy = "account")
    private List<Book> books;

    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column (name = "second_name")
    private String secondName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private String pesel;
    @Column (name = "phone_number")
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
}
