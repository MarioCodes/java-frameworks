package examples.junit.serviceclass.interfaces;

import examples.junit.serviceclass.entity.Account;
import examples.junit.serviceclass.entity.Transaction;

import java.util.List;

public interface AccountService extends AbstractService<Account> {

    List<Transaction> listAllTransactions(final Account account);

}
