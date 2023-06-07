package org.klodnicki.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) // bo książka bedzie przynależeć do któregoś konta, a nie konto do książki
    @JoinColumn(name = "account_id")
    private Account account;

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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Book() {
    }

    public Book(String title, String author, String isbn, String publisher, int publicationYear, int edition,
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
}

