package es.msanchez.spring.springinaction.controllers;

import es.msanchez.spring.springinaction.dao.IngredientRepository;
import es.msanchez.spring.springinaction.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(final IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(final String id) {
        return ingredientRepo.findById(id).get();
    }

}
