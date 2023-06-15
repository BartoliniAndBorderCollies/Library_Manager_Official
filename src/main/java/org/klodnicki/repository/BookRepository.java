package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import org.klodnicki.entity.BookInfo;

import java.util.List;
import java.util.Optional;

public class BookRepository {

    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void add(BookInfo bookInfo) {
        entityManager.getTransaction().begin();
        entityManager.persist(bookInfo);
        entityManager.getTransaction().commit();
    }

    public Optional<BookInfo> findBookByTitleAndAuthor(String title, String author) {
        String hqlQuery = "FROM BookInfo b WHERE b.title = :title AND b.author = :author";
        TypedQuery<BookInfo> query = entityManager.createQuery(hqlQuery, BookInfo.class);
        query.setParameter("title", title);
        query.setParameter("author", author);
        // 1 solution: always return List of objects

        // 2 solution: returning optional. TO BE COMPLETED with orElseThrow
        BookInfo singleResult = null;
        try {
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Returns first element
            for (BookInfo bookInfo : query.getResultList()) {
                return Optional.of(bookInfo);
            }
        }
        return Optional.ofNullable(singleResult);
    }

    public Optional<BookInfo> findBookByTitleAndAuthorAndEdition(String title, String author, String edition) {
        String hqlQuery = "FROM BookInfo b WHERE b.title = :title AND b.author = :author AND b.edition = :edition";
        TypedQuery<BookInfo> query = entityManager.createQuery(hqlQuery, BookInfo.class);
        query.setParameter("title", title);
        query.setParameter("author", author);
        query.setParameter("edition", edition);

        BookInfo singleResult = null;
        try {
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (NonUniqueResultException e) {
            // Returns first element
            for (BookInfo bookInfo : query.getResultList()) {
                return Optional.of(bookInfo);
            }
        }
        return Optional.ofNullable(singleResult);
    }

    public List<BookInfo> findBooksByTitleAndAuthor(String title, String author) {
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
