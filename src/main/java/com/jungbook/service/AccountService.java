package com.jungbook.service;

import com.jungbook.repository.AccountRepository;
import com.jungbook.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class AccountService implements UserDetailsService {


    AccountRepository accounts;
    PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accounts, PasswordEncoder passwordEncoder){
        this.accounts = accounts;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Account account=accounts.findById(username);

        UserDetails userDetails=new UserDetails() {

            @Override
            public boolean isEnabled() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public boolean isAccountNonExpired() {
                // TODO Auto-generated method stub
                return true;
            }

            @Override
            public String getUsername() {
                // TODO Auto-generated method stub
                return account.getId();
            }

            @Override
            public String getPassword() {
                // TODO Auto-generated method stub
                return account.getPw();
            }

            @Override
            public Collection getAuthorities() {
                // TODO Auto-generated method stub

                return account.getAuthorities();
            }
        };
        return userDetails;
    }

    public Account save(Account account,String role) {
        // TODO Auto-generated method stub
        account.setPw(passwordEncoder.encode(account.getPw()));
        account.setAccountNonExpired(true);
        account.setAccountNonLocked(true);
        account.setCredentialsNonExpired(true);
        account.setEnabled(true);
        return accounts.save(account, role);
    }

    public int idDuplicateCheck(String id){
        Account account = accounts.readAccount(id);
        int result = (account == null) ? 0 : 1;
        return result;
    }

}
