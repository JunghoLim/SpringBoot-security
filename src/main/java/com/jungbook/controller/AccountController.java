package com.jungbook.controller;

import com.jungbook.mapper.AccountMapper;
import com.jungbook.service.AccountService;
import com.jungbook.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    AccountService accountService;
    AccountMapper accountMapper;

    @Autowired
    public AccountController(AccountService accountService, AccountMapper accountMapper){
        this.accountMapper = accountMapper;
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String indexP(){
        return "index";
    }

    @GetMapping("/user")
    public String loginP(){
        return "login";
    }

    @GetMapping("/user/new")
    public String signUpP(){
        return "/signup";
    }

    @PostMapping("/user/new")
    public String signup(Account account){
        String role = "ROLE_USER";
        accountService.save(account, role);
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminP(){
        return "admin";
    }

    @PostMapping("/user/duplicate")
    @ResponseBody
    public int userDuplicateCheck(@RequestParam(required = false, value = "id")String id){
        return accountService.idDuplicateCheck(id);
    }

}