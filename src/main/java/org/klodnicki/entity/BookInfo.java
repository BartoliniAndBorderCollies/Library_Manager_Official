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
    @OneToMany(mappedBy = "bookInfo")
    //Pierwsza zmienna to klasa, w której się znajduję> One-> jedno BookInfo do wielu wypożyczen
    private List<LendingInformation> lendingInformationAboutBooksList = new ArrayList<>(); //jest to lista wypożyczen. BookInfo jest jedno, wypozyczeń moze byc wiele
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
    private String edition;
    @Column
    private String genre;
    @Column
    private String description;
    @Column
    private String language;
    @Column(name = "copies_number")
    private int copiesNumber;

    public BookInfo() {
    }

    public BookInfo(String title, String author, String isbn, String publisher, int publicationYear, String edition,
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

    public List<LendingInformation> getLendingInformationAboutBooksList() {
        return lendingInformationAboutBooksList;
    }

    public void setLendingInformationAboutBooksList(List<LendingInformation> lendingInformationAboutBooksList) {
        this.lendingInformationAboutBooksList = lendingInformationAboutBooksList;
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

    public void addLendingInformationAboutBooksList(LendingInformation lendingInformation) {
        lendingInformationAboutBooksList.add(lendingInformation);
    }

    public void removeLendingInformationAboutBooksList(LendingInformation lendingInformation) {
        lendingInformationAboutBooksList.remove(lendingInformation);
    }

    public void setCopiesNumber(int copiesNumber) {
        this.copiesNumber = copiesNumber;
    }

    public String getEdition() {
        return edition;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +

                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publication year=" + publicationYear +
                ", edition='" + edition + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", copies number=" + copiesNumber +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

