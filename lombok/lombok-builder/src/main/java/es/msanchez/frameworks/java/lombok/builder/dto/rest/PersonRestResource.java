package es.msanchez.frameworks.java.lombok.builder.dto.rest;

import es.msanchez.frameworks.java.lombok.builder.dto.PersonDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestResource {

    @GetMapping("/")
    public PersonDto buildPerson() {
        return PersonDto.builder().age(18).hobby("live")
                .name("Sergey").id(11235434L).build();
    }

}
