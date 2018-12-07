package es.msanchez.frameworks.catchexception;

import com.googlecode.catchexception.apis.BDDCatchException;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ExamplesTest {

    private Examples examples;

    @Before
    public void setUp() {
        this.examples = new Examples();
    }

    @Test
    public void testBasicUsage() {
        // Given

        // When
        BDDCatchException.when(this.examples).getImpossibleIndex();

        // Then
        Assertions.assertThat(BDDCatchException.caughtException())
                    .isNotNull()
                    .isExactlyInstanceOf(IndexOutOfBoundsException.class);
    }

}