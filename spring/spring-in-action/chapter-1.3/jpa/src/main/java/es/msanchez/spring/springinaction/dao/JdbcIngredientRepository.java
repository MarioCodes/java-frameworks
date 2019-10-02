package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Ingredient;
import es.msanchez.spring.springinaction.enums.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        return this.jdbcTemplate.query("select id, name, type from Ingredient", this::mapRowToIngredient);
    }

    private Ingredient mapRowToIngredient(final ResultSet rs,
                                          final int rowNum) throws SQLException {
        return new Ingredient(rs.getString("id"),
                rs.getString("name"),
                Type.valueOf(rs.getString("type")));
    }

    @Override
    public Ingredient findOne(final String id) {
        return this.jdbcTemplate.queryForObject("select id, name, type from Ingredient where id = ?",
                this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(final Ingredient ingredient) {
        this.jdbcTemplate.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }

}
