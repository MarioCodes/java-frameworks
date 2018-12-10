package es.msanchez.frameworks.assertj;

import enums.Race;
import es.msanchez.frameworks.assertj.dto.TolkienCharacter;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

public class DtoAssertions {

    /**
     * When it fails, it stops on the first one which is not as expected.
     */
    @Test
    public void testBadAssertion() {
        // Given
        final TolkienCharacter aragorn = new TolkienCharacter("aragorn", 200, Race.HUMAN);

        // When

        // Then
        Assertions.assertThat(aragorn.getName()).isEqualTo("ragorn");
        Assertions.assertThat(aragorn.getAge()).isEqualTo(190);
        Assertions.assertThat(aragorn.getRace()).isEqualTo(Race.DWARF);
    }

    /**
     * It asserts everything, even when one fails.
     */
    @Test
    public void testSoftAssertion() {
        // Given
        final TolkienCharacter aragorn = new TolkienCharacter("aragorn", 200, Race.HUMAN);

        // When

        // Then
        final SoftAssertions soft = new SoftAssertions();
        soft.assertThat(aragorn.getName()).isEqualTo("ragorn");
        soft.assertThat(aragorn.getAge()).isEqualTo(190);
        soft.assertThat(aragorn.getRace()).isEqualTo(Race.DWARF);
        soft.assertAll();
    }

    @Test
    public void testSoftAssertionStaticCall() {
        // Given
        final TolkienCharacter aragorn = new TolkienCharacter("aragorn", 200, Race.HUMAN);

        // When

        // Then
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(aragorn.getName()).isEqualTo("ragorn");
            soft.assertThat(aragorn.getAge()).isEqualTo(190);
            soft.assertThat(aragorn.getRace()).isEqualTo(Race.DWARF);
        });
    }


}
