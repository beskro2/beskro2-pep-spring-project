package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>  {




/*
 * @Param usernmae and password of the account
 * @Return the account with the provided name and password
 * querry to ensure an account exists with specific Username and password
 */

@Query("FROM Account WHERE username = :username AND password = :password")
Account verifyAccount(@Param("username") String username, @Param("password") String password);

@Query("SELECT a FROM Account a WHERE a.username = :username")
Account existanceCheck(@Param("username") String username);
}
