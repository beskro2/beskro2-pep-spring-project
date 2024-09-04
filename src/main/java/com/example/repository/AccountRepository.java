package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>  {




/*
 * @Param usernmae and password of the account
 * @Return the account with the provided name and password
 * querry to ensure an account exists with specific Username and password
 */

@Query("FROM Account WHERE usernamevar = :username AND password = :passwordvar")
Account verifyAccount(@Param("usernamevar") String username, @Param("passwordvar") String password);
}
