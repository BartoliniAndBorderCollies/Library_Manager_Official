package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.klodnicki.entity.BookInfo;

public class BookRepository {

    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void add(BookInfo bookInfo) {
        entityManager.getTransaction().begin();
        entityManager.persist(bookInfo);
        entityManager.getTransaction().commit();
    }

    public BookInfo findBookByTitleAndAuthor(String title, String author) {
        String hqlQuery = "FROM BookInfo b WHERE b.title = :title AND b.author = :author";
        TypedQuery<BookInfo> query = entityManager.createQuery(hqlQuery, BookInfo.class);
        query.setParameter("title", title);
        query.setParameter("author", author);

        return query.getSingleResult();
    }

    public void update(BookInfo bookInfo) {
        entityManager.getTransaction().begin();
        entityManager.merge(bookInfo);
        entityManager.getTransaction().commit();
    }
}
