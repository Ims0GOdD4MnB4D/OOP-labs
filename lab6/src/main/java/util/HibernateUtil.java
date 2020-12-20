package util;

import lombok.Getter;
import model.employee.Employee;
import model.report.Report;
import model.task.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactory();
    @Getter
    private static final Session session = getSessionFactory().openSession();
    private static SessionFactory buildSessionFactory() {
        try {
            if (sessionFactory == null) {
                Configuration configuration = new Configuration()
                        .configure(HibernateUtil.class
                                .getResource("/hibernate.cfg.xml"));
                configuration.addAnnotatedClass(Task.class);
                configuration.addAnnotatedClass(Employee.class);
                configuration.addAnnotatedClass(Report.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            return sessionFactory;
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
