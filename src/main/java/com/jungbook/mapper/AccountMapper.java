package com.jungbook.mapper;

import com.jungbook.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AccountMapper {

    Account readAccount(String username);
    String readAutorities(String id);
    void insertUser(@Param("account") Account account);
    void insertUserAutority(@Param("id") String id, @Param("autority") String autority);
    List readAllUsers();
}
