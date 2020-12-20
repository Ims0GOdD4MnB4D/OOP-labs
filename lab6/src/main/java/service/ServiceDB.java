package service;

import lombok.Getter;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.UUID;

public class ServiceDB {
    @Getter
    private final Session session = HibernateUtil.getSession();


}
