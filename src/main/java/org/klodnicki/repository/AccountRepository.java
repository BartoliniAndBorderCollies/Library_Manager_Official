package org.klodnicki.repository;

import jakarta.persistence.*;
import org.klodnicki.entity.Account;

import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private final EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public void create(Account account) {
        entityManager.getTransaction().begin();
        entityManager.persist(account);
        entityManager.getTransaction().commit();
    }

    public Optional<Account> findAccountByFirstNameAndLastNameAndPesel(String firstName, String lastName, String pesel) {
        String hqlQuery = "FROM Account a WHERE a.firstName = :firstName AND a.lastName = :lastName AND a.pesel = :pesel";
        TypedQuery<Account> query = entityManager.createQuery(hqlQuery, Account.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        query.setParameter("pesel", pesel);

        Account singleResult;

        try {
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(singleResult);
    }

    public List<Account> findAccountsByFirstNameAndLastName(String firstName, String lastName) {
        String hqlQuery = "FROM Account a WHERE a.firstName = :firstName AND a.lastName = :lastName";
        TypedQuery<Account> query = entityManager.createQuery(hqlQuery, Account.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);

        return query.getResultList();
    }

    public List<Account> findAllAccountsOrderByParameter(String parameter) {
        String hqlQuery = "FROM Account a ORDER BY a." + parameter;
        TypedQuery<Account> query = entityManager.createQuery(hqlQuery, Account.class);

        return query.getResultList();
    }

    public void mergeAccount(String peselAccountToModify, String parameterToModify, String newData) {

        entityManager.getTransaction().begin();
        String hqlQuery = "UPDATE Account a SET a." + parameterToModify + " = :" + parameterToModify +
                " WHERE a.pesel = :peselAccountToModify";

        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter(parameterToModify, newData);
        query.setParameter("peselAccountToModify", peselAccountToModify);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public List<Account> findAllAccounts() {
        String hqlQuery = "FROM Account";
        TypedQuery<Account> query = entityManager.createQuery(hqlQuery, Account.class);

        return query.getResultList();
    }

    public void removeAccount(String pesel) {

        entityManager.getTransaction().begin();
        String hqlQuery = "DELETE FROM Account a WHERE a.pesel = :pesel";
        Query query = entityManager.createQuery(hqlQuery);
        query.setParameter("pesel", pesel);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    }

    public Optional<Account> findAccountByPesel(String pesel) {
        String hqlQuery = "FROM Account a WHERE a.pesel = :pesel";
        TypedQuery<Account> query = entityManager.createQuery(hqlQuery, Account.class);
        query.setParameter("pesel", pesel);

        Account singleResult;

        try {
            singleResult = query.getSingleResult();
        } catch (NoResultException e) {
            return Optional.empty();
        }

        return Optional.ofNullable(singleResult);
    }

}
