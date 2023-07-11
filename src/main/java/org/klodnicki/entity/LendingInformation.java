package org.klodnicki.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lending_info")
public class LendingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    // Bo wypożyczeń jest wiele, a druga strona - book info - jest jedno. Pierwsza zmienna to klasa w ktorej sie znajduje
    @JoinColumn(name = "book_info_id")
    private BookInfo bookInfo;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "lending_date")
    private LocalDateTime lendingDate;
    @Column(name = "returning_date")
    private LocalDateTime returningDate;

    public LendingInformation() {
    }

    public LendingInformation(BookInfo bookInfo, Account account, LocalDateTime lendingDate) {
        this.bookInfo = bookInfo;
        this.account = account;
        this.lendingDate = lendingDate;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getLendingDate() {
        return lendingDate;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public Account getAccount() {
        return account;
    }

    public LocalDateTime getReturningDate() {
        return returningDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLendingDate(LocalDateTime lendingDate) {
        this.lendingDate = lendingDate;
    }

    public void setReturningDate(LocalDateTime returningDate) {
        this.returningDate = returningDate;
    }
}
