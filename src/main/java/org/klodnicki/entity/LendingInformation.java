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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLendingDate() {
        return lendingDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getBookInfoId() {
        return bookInfoId;
    }

    public void setLendingDate(LocalDateTime lendingDate, Long accountId, Long bookInfoId) {
        this.lendingDate = lendingDate;
        this.accountId = accountId;
        this.bookInfoId = bookInfoId;
    }

    public void setReturningDate(LocalDateTime returningDate, Long accountId, Long bookInfoId) {
        this.returningDate = returningDate;
        this.accountId = accountId;
        this.bookInfoId = bookInfoId;
    }
}
