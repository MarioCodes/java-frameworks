package es.msanchez.spring.springinaction.dao;

import es.msanchez.spring.springinaction.entities.Ingredient;
import es.msanchez.spring.springinaction.entities.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.util.Arrays;

@Slf4j
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
        final Date now = Date.valueOf(LocalDate.now());
        log.info("Date timestamp: {}", now);
        taco.setCreatedAt(now);
        final PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?,?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(Arrays.asList(taco.getName(),
                new Timestamp(taco.getCreatedAt().getTime())));
        log.info("PreparedStatement: {}", psc.toString());

        final int result = this.jdbc.update(psc);
        log.info("result from update: {}", result);
        /*
         * The original example uses -> return number.longValue(); this comes from a KeyHolder
         * but the implementation doesnt work as it returns a null.
         */
        return result;
    }
}
