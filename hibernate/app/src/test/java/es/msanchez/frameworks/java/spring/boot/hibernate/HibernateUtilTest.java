package es.msanchez.frameworks.java.spring.boot.hibernate;

import es.msanchez.frameworks.java.spring.boot.entity.Hobby;
import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HibernateUtilTest {

    private Session session;

    @BeforeMethod
    public void setUp() {
        this.session = HibernateUtil.getSESSION_FACTORY().openSession();
    }

    @AfterMethod
    public void tearDown() {
        HibernateUtil.shutdown();
    }

    @Test
    public void testInsert() {
        // @GIVEN
        session.beginTransaction();

        final Hobby hobby = new Hobby();
        hobby.setName("Swimming");

        // @WHEN
        session.save(hobby);
        session.getTransaction().commit();

        // @THEN
    }
}