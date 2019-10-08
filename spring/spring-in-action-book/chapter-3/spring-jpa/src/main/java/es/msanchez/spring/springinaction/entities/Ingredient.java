package es.msanchez.spring.springinaction.entities;

import es.msanchez.spring.springinaction.enums.Type;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class Ingredient {

    @Id
    private final String id;

    private final String name;
    private final Type type;

}
