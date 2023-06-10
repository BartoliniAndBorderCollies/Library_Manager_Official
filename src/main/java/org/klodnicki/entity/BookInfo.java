package org.klodnicki.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book_info")
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER) // wiele kopii ksiazek moze przynalezec do wielu kont
    @JoinTable(name = "lending_info",
            joinColumns = { @JoinColumn(name = "book_info_id") }, //join tyczy sie klasy tej(czyli w tym wypadki BookInfo)
            //brackety są po to, żeby umiescić wiele wartości innych kolumn, w tym przypadku nie potrzebne
            inverseJoinColumns = { @JoinColumn(name = "account_id") }// inverseJoinColumns tyczy sie klasy, którą łaczymy
    )
    private List<Account> accounts = new ArrayList<>(); //to jest lista kont, które mają wypożyczone książki
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column
    private String isbn;
    @Column
    private String publisher;
    @Column(name = "publication_year")
    private int publicationYear;
    @Column
    private int edition;
    @Column
    private String genre;
    @Column
    private String description;
    @Column
    private String language;
    @Column(name = "copies_number")
    private int copiesNumber;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public BookInfo() {
    }

    public BookInfo(String title, String author, String isbn, String publisher, int publicationYear, int edition,
                    String genre, String description, String language, int copiesNumber) {

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.edition = edition;
        this.genre = genre;
        this.description = description;
        this.language = language;
        this.copiesNumber = copiesNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCopiesNumber() {
        return copiesNumber;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
    }
}

