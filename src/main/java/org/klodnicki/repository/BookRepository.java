package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import org.klodnicki.entity.Book;

public class BookRepository {

    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void add(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

}
