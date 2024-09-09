package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;



//@RequestMApping("/api/users")

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 * 
 * week 10 day 1
 */
@Controller
//@RequestMapping("/messages")
public class SocialMediaController {

AccountService accountService;
MessageService messageService;

public SocialMediaController(MessageService messageService, AccountService accountService){
    this.messageService = messageService;
    this.accountService= accountService;
}

@GetMapping("/messages")
public @ResponseBody List<Message> getAllMessages(){
    return messageService.getAllMessages();
}

@PostMapping("/messages")
public ResponseEntity<Message> CreateANewMessage( @RequestBody Message message){
System.out.println("\nwhat the hell\n");
    Message returnmessage = messageService.saveMessage(message);
    if(returnmessage!=null){
        return ResponseEntity.ok(returnmessage);
    }else{
        return ResponseEntity.status(400).body(null);
    }

}

    //localhost:8080/messages/{messageId}.
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageByID(@PathVariable int messageId){
      
        Message returnmessage = messageService.getMessageByID(messageId);
        if(returnmessage!=null){
            return ResponseEntity.ok(returnmessage);
        }else{
            return ResponseEntity.status(200).body(null);
        }
}

@DeleteMapping("/messages/{messageId}")
public ResponseEntity<Integer> deleteById(@PathVariable int messageId){
   int rowsaffected = messageService.DeleteMessageByID(messageId);
   if(rowsaffected ==1 ){
    return ResponseEntity.ok(rowsaffected);
   }
   else {
    return ResponseEntity.ok(null);
   }
}

@PatchMapping("/messages/{messageId}")
public ResponseEntity<Integer> updateMessage(@PathVariable int messageId,@RequestBody Message message){
    System.out.println("\n\nWhat the hell is going on\n\n");
    int rowsaffected = messageService.updateMessageById(messageId, message);
if(rowsaffected == 1){
    return ResponseEntity.ok(rowsaffected);
}else{
    return ResponseEntity.status(400).body(null);
}
}

@GetMapping(value="/accounts/{accountId}/messages")
public @ResponseBody List<Message> getAllMessagesFromUser(@PathVariable int accountId){
    System.out.println("this\nis\na\ntest\n");
    return messageService.getallmessagesFromUser(accountId);
    
}




@PostMapping("/login")
public ResponseEntity<Account> login(@RequestBody Account account){
Account returnaccount = accountService.verifyAccount(account);

    if(returnaccount !=null){
    return ResponseEntity.ok(returnaccount);
   
}else{
    return ResponseEntity.status(401).body(null);
}
}

@PostMapping("/register")
public ResponseEntity<Account> registerNewAccount(@RequestBody Account account){
    if(accountService.noDuplicatesCheck(account.getUsername()) != null){
        return ResponseEntity.status(409).body(null);
    }
   Account returnaccount = accountService.persistAccount(account);
   
    if(returnaccount != null){
     return ResponseEntity.ok(returnaccount);
    }else{
    return ResponseEntity.status(400).body(null);
    }
}

}

