package com.jungbook.repository;

import com.jungbook.mapper.AccountMapper;
import com.jungbook.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final AccountMapper accountMapper;

    @Autowired
    public AccountRepository(AccountMapper accountMapper){
        this.accountMapper = accountMapper;
    }

    public Account save(Account account, String role) {
        accountMapper.insertUser(account);
        accountMapper.insertUserAutority(account.getId(), role);
        return account;
    }

    public Account findById(String username) {
        // TODO Auto-generated method stub
        Account account = accountMapper.readAccount(username);
        account.setAuth(accountMapper.readAutorities(username));
        return account;
    }

    public List<String> findAuthoritiesByID(String username) {
        List<String> list = new ArrayList<String>();
        list.add(accountMapper.readAutorities(username));
        return list;
    }

    public Account readAccount(String id){
        return accountMapper.readAccount(id);
    }
}