package examples.junit.serviceclass.interfaces;

import java.util.List;

import examples.junit.serviceclass.dto.AccountDto;

public interface AccountService {
	AccountDto createNewAccount(AccountDto dto);

	AccountDto updateAccount(AccountDto dto);

	AccountDto removeAccount(AccountDto dto);

	List listAllTransactios(AccountDto account);
}
