package es.msanchez.spring.springinaction.repositories;

import es.msanchez.spring.springinaction.entities.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;

  /**
   * @param jdbcTemplate -
   */
  @Autowired
  public JdbcIngredientRepository(final JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Iterable<Ingredient> findAll() {
    return null;
  }

  @Override
  public Ingredient findOne(final String id) {
    return null;
  }

  @Override
  public Ingredient save(final Ingredient ingredient) {
    return null;
  }

}
