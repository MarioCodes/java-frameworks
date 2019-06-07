package examples.junit.serviceclass.interfaces;

import examples.junit.serviceclass.categories.Category1;
import examples.junit.serviceclass.categories.Category2;
import examples.junit.serviceclass.entity.Account;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountServiceImplTest {

    private final AccountService service = new AccountServiceImpl();

    @Test
    @Category(Category1.class)
    public void testNewAccount() {
        // Given
        final Account input = new Account();
        input.setName("Mario");
        input.setDescription("Rubio");

        // When
        final Account output = this.service.create(input);

        // Then
        assertThat(output).isNotNull();
        assertThat(input).isNotNull().isEqualTo(output);
    }

    @Test
    @Category(Category2.class)
    @Ignore("not yet implemented")
    public void testCreateSecondCategory() {
        // Given
        final Account input = new Account();
        input.setName("Mario");
        input.setDescription("Rubio");

        // When
        final Account output = this.service.create(input);

        // Then
        assertThat(output).isNotNull();
        assertThat(input).isNotNull().isEqualTo(output);
    }
}
