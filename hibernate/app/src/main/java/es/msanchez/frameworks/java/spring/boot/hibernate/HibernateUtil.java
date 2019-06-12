package es.msanchez.frameworks.java.spring.boot.hibernate;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateUtil {

    @Getter
    private static final SessionFactory SESSION_FACTORY = HibernateUtil.buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch(final Throwable ex) {
            log.error("Failure on ini hibernate session. ", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }

}
