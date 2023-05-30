package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import org.klodnicki.entity.Account;

public class AccountRepository {
    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void create(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }
}
