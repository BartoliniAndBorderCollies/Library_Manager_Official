package org.klodnicki;

import org.hibernate.Session;
import org.klodnicki.database.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Check database version (because there is nothing else in database)
        String sql = "select version()";

        String result = session.createNativeQuery(sql, String.class).getSingleResult();
        System.out.println(result);

        session.close();
        HibernateUtil.shutdown();
    }
}
