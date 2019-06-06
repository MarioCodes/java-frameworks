package es.msanchez.frameworks.java.lombok.builder.dto.rest;

import es.msanchez.frameworks.java.lombok.builder.dto.PersonDto;
import org.assertj.core.api.BDDAssertions;
import org.assertj.core.api.SoftAssertions;
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

        // @WHEN
        final PersonDto result = this.resource.buildPerson();

        // @THEN
        BDDAssertions.assertThat(result).isNotNull();
        SoftAssertions.assertSoftly(soft -> {
            soft.assertThat(result.getAge()).isEqualTo(18);
            soft.assertThat(result.getHobbies()).hasSize(1).containsExactly("live");
            soft.assertThat(result.getName()).isEqualTo("Sergey");
            soft.assertThat(result.getId()).isEqualTo(11235434L);
        });
    }

}