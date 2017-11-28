package examples.junit.serviceclass.interfaces;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import examples.junit.serviceclass.categories.Category1;
import examples.junit.serviceclass.categories.Category2;
import examples.junit.serviceclass.dto.AccountDto;

public class AccountServiceImplTest {
	AccountService service = new AccountServiceImpl();

	@Test
	@Category(Category1.class)
	public void testNewAccount() throws Exception {
		// Given
		AccountDto account = new AccountDto();
		account.setName("Mario");
		account.setDescription("Rubio");

		AccountDto accountTwo;

		// When
		accountTwo = this.service.createNewAccount(account);

		// Then
		assertThat(account).isNotNull();
		assertThat(accountTwo).isNotNull();
		assertThat(account).isEqualTo(accountTwo);
	}

	@Test
	@Category(Category2.class)
	@Ignore
	public void testCreateSecondCategory() throws Exception {
		// Given
		AccountDto account = new AccountDto();
		account.setName("Mario");
		account.setDescription("Rubio");

		AccountDto accountTwo;

		// When
		accountTwo = this.service.createNewAccount(account);

		// Then
		assertThat(account).isNotNull();
		assertThat(accountTwo).isNotNull();
		assertThat(account).isEqualTo(accountTwo);
		assertThat(false).isTrue();
	}
}
