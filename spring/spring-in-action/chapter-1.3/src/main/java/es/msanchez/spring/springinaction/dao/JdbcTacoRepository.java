package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Ingredient;
import es.msanchez.spring.springinaction.entities.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Arrays;

@Repository
public class JdbcTacoRepository implements TacoRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoRepository(final JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Taco save(final Taco taco) {
        final long tacoId = this.saveTacoInfo(taco);
        taco.setId(tacoId);
        taco.getIngredients().forEach(ing -> this.saveIngredientToTaco(ing, tacoId));
        return taco;
    }

    private void saveIngredientToTaco(final Ingredient ingredient,
                                      final long tacoId) {
        this.jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)",
                tacoId, ingredient.getId());
    }

    private long saveTacoInfo(final Taco taco) {
        taco.setCreatedAt(Date.valueOf(LocalDate.now()));
        final PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?,?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(Arrays.asList(taco.getName(),
                new Timestamp(taco.getCreatedAt().getTime())));

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbc.update(psc, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
