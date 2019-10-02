package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Order;

public interface OrderRepository {

    public Order save(final Order order);

}
