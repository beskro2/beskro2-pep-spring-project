package com.example.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;


public interface MessageRepository extends JpaRepository<Message, Integer> {
//week 10 day 1
   
  /* Query to return the number of rows affected by delete 
   * 
   * 
   */
 
   @Modifying
   @Transactional
   @Query("DELETE FROM Message m WHERE m.id = :messageId")
   int deleteByIdWithCount(@Param("messageId") Integer messageId);

   @Modifying
   @Transactional
   @Query("UPDATE Message m SET m.messageText = :messageText  WHERE m.id = :messageId")
   int updateMessageById(@Param("messageId") Integer messageId, @Param("messageText") String messageText);

   @Transactional
   @Query("SELECT m FROM Message m WHERE m.postedBy = :userId")
   List<Message> getAllMessagesFromUser(@Param("userId") Integer userId);

}
