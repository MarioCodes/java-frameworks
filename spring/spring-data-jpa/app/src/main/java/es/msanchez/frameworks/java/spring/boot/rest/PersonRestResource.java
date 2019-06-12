package es.msanchez.frameworks.java.spring.boot.rest;

import es.msanchez.frameworks.java.spring.boot.dao.PersonDao;
import es.msanchez.frameworks.java.spring.boot.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonRestResource {

    private final PersonDao personDao;

    @Autowired
    public PersonRestResource(final PersonDao personDao) {
        this.personDao = personDao;
    }

    @PutMapping("create/{name}")
    public String createPerson(@PathVariable("name") final String name) {
        log.info("create person called w. name '{}'", name);
        final Person person = new Person();
        person.setName(name);

        this.personDao.save(person);
        return "created";
    }

}
