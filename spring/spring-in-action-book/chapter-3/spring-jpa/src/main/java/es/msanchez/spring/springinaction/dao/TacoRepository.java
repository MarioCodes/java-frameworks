package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Taco;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface TacoRepository extends CrudRepository<Taco, Long> {

    List<Taco> findAllByCreatedAt(final Date createdAt);

}