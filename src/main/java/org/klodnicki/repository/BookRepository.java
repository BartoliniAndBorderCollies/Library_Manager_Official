package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.klodnicki.entity.Book;

public class BookRepository {

    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void add(Book book) {
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
    }

    public Book findBookByTitleAndAuthor(String title, String author) {
        String hqlQuery = "FROM Book b WHERE b.title = :title AND b.author = :author";
        TypedQuery<Book> query = entityManager.createQuery(hqlQuery, Book.class);
        query.setParameter("title", title);
        query.setParameter("author", author);

        return query.getSingleResult();
    }
}
