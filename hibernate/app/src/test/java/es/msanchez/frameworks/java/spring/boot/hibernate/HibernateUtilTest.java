package es.msanchez.frameworks.java.spring.boot.hibernate;

import es.msanchez.frameworks.java.spring.boot.entity.Hobby;
import es.msanchez.frameworks.java.spring.boot.entity.Person;
import org.hibernate.Session;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void testInsertHobby() {
        // @GIVEN
        session.beginTransaction();

        final Hobby hobby = new Hobby();
        hobby.setName("Swimming");

        // @WHEN
        session.save(hobby);
        session.getTransaction().commit();

        // @THEN
    }

    @Test
    public void testInsertPerson() {
        // @GIVEN
        session.beginTransaction();

        final List<Hobby> hobbies = new ArrayList<>();
        final Hobby hobby = new Hobby();
        hobby.setName("Climbing");
        hobbies.add(hobby);

        final Person person = new Person();
        person.setName("Mario");
        person.setAge(1);
        person.setHobbies(hobbies);

        // @WHEN
        session.save(hobby);
        session.save(person);
        session.getTransaction().commit();

        // @THEN
    }
}