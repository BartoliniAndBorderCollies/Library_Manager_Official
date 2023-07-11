package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.klodnicki.entity.Account;
import org.klodnicki.entity.BookInfo;
import org.klodnicki.entity.LendingInformation;

import java.util.Optional;

public class LendingInformationRepository {

    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void add(LendingInformation lendingInformation) {
        entityManager.getTransaction().begin();
        entityManager.persist(lendingInformation);
        entityManager.getTransaction().commit();
    }

    public void remove(LendingInformation lendingInformation) {

        entityManager.getTransaction().begin();
        String hqlQuery = "DELETE FROM LendingInformation l WHERE l.id = :id";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("id", lendingInformation.getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public Optional<LendingInformation> findLendingInformationByAccountAndBookInfo(Account account, BookInfo bookInfo) {
        String hqlQuery = "FROM LendingInformation l WHERE l.account = :account AND l.bookInfo = :bookInfo";
        TypedQuery<LendingInformation> query = entityManager.createQuery(hqlQuery, LendingInformation.class);
        query.setParameter("account", account);
        query.setParameter("bookInfo", bookInfo);

        LendingInformation singleResult;

        try {
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(singleResult);
    }
}
