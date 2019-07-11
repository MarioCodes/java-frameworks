package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Taco;

public interface TacoRepository {

    public Taco save(final Taco taco);

}