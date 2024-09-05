package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService {

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
        if(message.getMessageText().length()<255 && message.getMessageText().length()>0){
        return messageRepository.save(message);
    }

    System.out.println("\n\n Was not added \n\n");
    return null;
    }

    /*
     * return 
     */
  
    public Message getMessageByID(Long messageId){
        return messageRepository.findById(messageId).orElse(null);
    }

    
}
