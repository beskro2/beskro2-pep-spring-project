package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {
//week 10 day 1
    // example for reference
  //  @Query("FROM Pet WHERE name = :nameVar")
  //  List<Pet> example1(@Param("nameVar") String name);




  /* Query to  delete a message identified by a message ID
   * 
   * 
   */


  /* Query to update a message text identified by a message ID.
   * 
   * 
   */ 

  /*Query to retrieve all messages written by a particular user..
   * 
   * 
   */ 

    
}
