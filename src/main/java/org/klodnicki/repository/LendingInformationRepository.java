package org.klodnicki.repository;

import jakarta.persistence.EntityManager;
import org.klodnicki.entity.LendingInformation;

public class LendingInformationRepository {

    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void add(LendingInformation lendingInformation) {
        entityManager.getTransaction().begin();
        entityManager.persist(lendingInformation);
        entityManager.getTransaction().commit();
    }
}
