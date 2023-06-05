package org.klodnicki.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    public Book() {
    }

    public Book(String title, String author, String isbn, String publisher, int publicationYear, int edition,
                String genre, String description, String language, int copiesNumber) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

