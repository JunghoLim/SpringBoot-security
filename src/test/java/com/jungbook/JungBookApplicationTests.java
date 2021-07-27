package com.jungbook;

import com.jungbook.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
class JungBookApplicationTests {

    private final AccountService accountService;

    @Autowired
    JungBookApplicationTests(AccountService accountService) {
        this.accountService = accountService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void getAutorityTest(){
        UserDetails account = accountService.loadUserByUsername("admin");
        System.out.println(account);
    }
}
