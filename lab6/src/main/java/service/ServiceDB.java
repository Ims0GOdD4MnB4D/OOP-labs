package service;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.UUID;

@Getter
public class ServiceDB {
    private final Session session;
    private final Transaction transaction;

    public ServiceDB() {
        session = HibernateUtil.getSession();
        transaction = session.beginTransaction();
    }

    public void commit() {
        transaction.commit();
    }

    public void closeSession() {
        session.close();
    }
}
