package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
