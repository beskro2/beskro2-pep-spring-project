package com.example.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;




@Service
public class MessageService {
    @Autowired
    private AccountService accountservice;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    /*
 * @Param 
 * @Return all messages 
 * Method to Return all messages in DB
 */
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
        
    }

     /*
 * @Param Message without messageid
 * @Return Message with messageid
 * Method to save a message to the DB and then return the message with its new ID
 */
    public Message saveMessage(Message message){
      
        if(message.getMessageText().length()<255 
        && message.getMessageText().length()>0 
        && accountservice.getAccountByID(message.getPostedBy())!=null){
        return messageRepository.save(message);
    }

    return null;
    }

    /* 
    * @Param MessageID
    * @Return Message
    * Method to return a method by its ID
    */
  
    public Message getMessageByID(int messageId){
        Optional<Message> optionalmessage = messageRepository.findById(messageId);
        if (optionalmessage.isPresent()){
            Message returnmessage = optionalmessage.get();
            return returnmessage;
        }
        else{
            return null;
        }
    }
    /* 
    * @Param Message ID and Message
    * @Return 
    * Method to save a message to the DB and then return the message with its new ID
    */
    public int updateMessageById(int messageID, Message message){
        if(message.getMessageText().length()>0 && message.getMessageText().length()<255){
       return messageRepository.updateMessageById( messageID , message.getMessageText());
        }else{
            return 0;
        }
    }


    /*Delete message by id */
    public int DeleteMessageByID(Integer messageID){
     int test= messageRepository.deleteByIdWithCount(messageID);
      return test;
       
    }

    /*get all messages from one user */
    public List<Message> getallmessagesFromUser(int userId){

     
        List<Message> returnlist = messageRepository.getAllMessagesFromUser(userId);
     return returnlist;
    }
}
