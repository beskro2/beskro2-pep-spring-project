package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
    return all messages
     */ 
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
        
    }

    /*
     * add new message to db
     */
    public Message saveMessage(Message message){
        System.out.println("got to savemessage");
        if(message.getMessageText().length()<255 
        && message.getMessageText().length()>0 
        && accountservice.getAccountByID(message.getPostedBy())!=null){
        return messageRepository.save(message);
    }

    System.out.println("\n\n Was not added \n\n");
    return null;
    }

    /*
     * return 
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

    /*update a message by id */
    public int updateMessageById(int messageID, Message message){
        if(message.getMessageText().length()>0 && message.getMessageText().length()<255){
       return messageRepository.updateMessageById( messageID , message.getMessageText());
        }else{
            return 0;
        }
    }


    /*Delete message */
    public int DeleteMessageByID(Integer messageID){
     int test= messageRepository.deleteByIdWithCount(messageID);
      return test;
       
    }

    /*get all messages from one user */
    public List<Message> getallmessagesFromUser(int userId){

        System.out.println("service layer");
        List<Message> returnlist = messageRepository.getAllMessagesFromUser(userId);
     return returnlist;
    }
}
