package es.msanchez.frameworks.java.lombok.builder.dto;

import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class PersonDto {

    private String name;

    private int age;

    private Set<String> hobbies;

    private long id;

}
