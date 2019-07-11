package es.msanchez.spring.springinaction.entities;

import es.msanchez.spring.springinaction.enums.Type;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

}
