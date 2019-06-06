package es.msanchez.frameworks.java.lombok.builder.dto.rest;

import org.assertj.core.api.BDDAssertions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonRestResourceTest {

    private PersonRestResource resource;

    @BeforeMethod
    public void setUp() {
        this.resource = new PersonRestResource();
    }

    @Test
    public void testIndexResult() {
        // @GIVEN
        final String expectedResult = "oh no!";

        // @WHEN
        final String result = this.resource.index();

        // @THEN
        BDDAssertions.assertThat(result).isEqualTo(expectedResult);
    }

}