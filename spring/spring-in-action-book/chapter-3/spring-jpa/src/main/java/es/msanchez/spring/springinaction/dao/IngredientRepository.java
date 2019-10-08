package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
