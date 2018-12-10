package es.msanchez.frameworks.assertj.dto;

import enums.Race;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TolkienCharacter {

    private String name;
    private int age;
    private Race race;

}
