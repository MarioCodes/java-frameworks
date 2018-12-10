package es.msanchez.frameworks.assertj;

import enums.Race;
import es.msanchez.frameworks.assertj.dto.TolkienCharacter;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

public class ListAssertions {

    // //
    // Testing when you have the Original Beans to compare against.
    // //

    @Test
    public void testBasicFilteringAndAssert() {
        // Given
        final TolkienCharacter aragorn = new TolkienCharacter("aragon", 200, Race.HUMAN);
        final TolkienCharacter frodo = new TolkienCharacter("frodo", 30, Race.HOBBIT);
        final TolkienCharacter sam = new TolkienCharacter("sam", 30, Race.HOBBIT);
        final List<TolkienCharacter> fellowship = Lists.newArrayList(aragorn, frodo, sam);

        // When

        // Then
        Assertions.assertThat(fellowship)
                .filteredOn(character -> character.getName().contains("a"))
                .hasSize(2)
                .containsOnly(aragorn, sam);

        Assertions.assertThat(fellowship)
                .filteredOn(character -> character.getRace().equals(Race.HUMAN))
                .hasSize(1)
                .containsOnly(aragorn);
    }

    @Test
    public void testFilteringAndAssertByField() {
        // Given
        final TolkienCharacter aragorn = new TolkienCharacter("aragon", 200, Race.HUMAN);
        final TolkienCharacter frodo = new TolkienCharacter("frodo", 30, Race.HOBBIT);
        final TolkienCharacter sam = new TolkienCharacter("sam", 30, Race.HOBBIT);
        final List<TolkienCharacter> fellowship = Lists.newArrayList(aragorn, frodo, sam);

        // When

        // Then
        Assertions.assertThat(fellowship)
                .filteredOn("race", Race.HUMAN)
                .hasSize(1)
                .containsOnly(aragorn);

        Assertions.assertThat(fellowship)
                .filteredOn("race", Assertions.notIn(Race.HUMAN))
                .hasSize(2)
                .containsOnly(frodo, sam);
    }

    // //
    // Testing when you wouldn't have the original Beans or it would be too much work to create them.
    // //

    @Test
    public void testExtractAndAssertField() {
        // Given
        final List<TolkienCharacter> fellowship = this.prepareFellowship();

        // When

        // Then
        Assertions.assertThat(fellowship)
                .extracting("name", String.class)
                .contains("aragorn", "frodo", "sam")
                .doesNotContain("sauron");
    }

    private List<TolkienCharacter> prepareFellowship() {
        final TolkienCharacter aragorn = new TolkienCharacter("aragorn", 200, Race.HUMAN);
        final TolkienCharacter frodo = new TolkienCharacter("frodo", 30, Race.HOBBIT);
        final TolkienCharacter sam = new TolkienCharacter("sam", 30, Race.HOBBIT);
        return Lists.newArrayList(aragorn, frodo, sam);
    }

    @Test
    public void testExtractAndAssertMoreThanOneField() {
        // Given
        final List<TolkienCharacter> fellowship = this.prepareFellowship();

        // When

        // Then
        Assertions.assertThat(fellowship)
                .extracting("name", "age", "race.name")
                .contains(Assertions.tuple("aragorn", 200, "HUMAN"),
                        Assertions.tuple("frodo", 30, "HOBBIT"),
                        Assertions.tuple("sam", 30, "HOBBIT"));
    }
}
