package es.msanchez.frameworks.java.lombok.builder.dto.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonRestResource {

    @GetMapping("/")
    public String index() {
        return "oh no!";
    }

}
