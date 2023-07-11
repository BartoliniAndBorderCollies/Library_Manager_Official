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


}
