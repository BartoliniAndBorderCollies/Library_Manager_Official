package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.klodnicki.entity.Account;

public class AccountRepository {
    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void create(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }

    public Account findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel) {
        String hqlQuery = "FROM Account a WHERE a.firstName = :firstName AND a.lastName = :lastName AND a.pesel = :pesel";
        TypedQuery<Account> query = entityManager.createQuery(hqlQuery, Account.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("pesel", pesel);

        return query.getSingleResult();
    }
}
