package examples.junit.serviceclass.interfaces;

import examples.junit.serviceclass.entity.Account;
import examples.junit.serviceclass.entity.Transaction;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    public Account create(final Account dto) {
        return dto;
    }

    public Account update(final Account dto) {
        return null;
    }

    public Account remove(final Account dto) {
        return null;
    }

    public List<Transaction> listAllTransactions(final Account account) {
        return null;
    }

}
