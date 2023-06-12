package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.klodnicki.entity.BookInfo;

import java.util.List;

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

    public BookInfo findBookByTitleAndAuthorAndEdition(String title, String author, String edition) {
        String hqlQuery = "FROM BookInfo b WHERE b.title = :title AND b.author = :author AND b.edition = :edition";
        TypedQuery<BookInfo> query = entityManager.createQuery(hqlQuery, BookInfo.class);
        query.setParameter("title", title);
        query.setParameter("author", author);
        query.setParameter("edition", edition);

        return query.getSingleResult();
    }

    public List<BookInfo> findBooksByTitleAndAuthorReturnList(String title, String author) {
        String hqlQuery = "FROM BookInfo b WHERE b.title = :title AND b.author = :author";
        TypedQuery<BookInfo> query = entityManager.createQuery(hqlQuery, BookInfo.class);
        query.setParameter("title", title);
        query.setParameter("author", author);

        return query.getResultList();
    }

    public void update(BookInfo bookInfo) {
        entityManager.getTransaction().begin();
        entityManager.merge(bookInfo);
        entityManager.getTransaction().commit();
    }
}
