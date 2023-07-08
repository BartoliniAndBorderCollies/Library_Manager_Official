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

    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "book_info_id")
    private Long bookInfoId;
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
