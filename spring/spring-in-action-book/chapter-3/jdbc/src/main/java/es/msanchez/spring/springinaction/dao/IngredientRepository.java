package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();

    Ingredient findOne(final String id);

    Ingredient save(final Ingredient ingredient);

}
