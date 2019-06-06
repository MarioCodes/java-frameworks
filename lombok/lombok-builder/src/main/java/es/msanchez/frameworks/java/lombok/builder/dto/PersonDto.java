package es.msanchez.frameworks.java.lombok.builder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private String name;

    private int age;

    @Singular
    private Set<String> hobbies;

    private long id;

}
