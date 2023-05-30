package org.klodnicki.database;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.klodnicki.entities.Account;

public class AccountRepository {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private final EntityManager entityManager = sessionFactory.createEntityManager();


    public void create(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }
}
