package kz.csse.jwtauth.reactjwtauth.controllers;


import kz.csse.jwtauth.reactjwtauth.entities.CardTasks;
import kz.csse.jwtauth.reactjwtauth.entities.Cards;
import kz.csse.jwtauth.reactjwtauth.entities.Users;
import kz.csse.jwtauth.reactjwtauth.models.UserDTO;
import kz.csse.jwtauth.reactjwtauth.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MainRestController {

    @GetMapping(value = "/profile")
    public ResponseEntity<?> profilePage(){
        Users user = getUser();
        return new ResponseEntity<>(new UserDTO(user.getId(), user.getEmail(), user.getRoles()), HttpStatus.OK);
    }

    private Users getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            Users user = (Users) authentication.getPrincipal();
            return user;
        }
        return null;
    }


    @Autowired
    private CardService cardService;

    @GetMapping(value = "allCards")
    public ResponseEntity<?> getAllCards(){
        List<Cards> cards = cardService.getAllCards();
        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping(value = "/addCard")
    public ResponseEntity<?> addItem(@RequestBody Cards cards){
        Date currentSqlDate = new Date(System.currentTimeMillis());
        cards.setAddedDate(currentSqlDate);
        cardService.addCard(cards);
        return ResponseEntity.ok(cards);
    }
    @GetMapping(value = "/getCards/{id}")
    public ResponseEntity<?> getCard(@PathVariable Long id) {
        System.out.println("GET CARD");
        Cards card = cardService.getCard(id);
        if (card != null) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }
        System.out.println("GET CARD 2");
        return null;
    }


    @DeleteMapping(value = "/deleteCard/{id}")
    public ResponseEntity<?> deleteCard (@PathVariable Long id){

        Cards task = cardService.getCard(id);
        List<CardTasks> tasks = cardService.getAllCardsByCardId(task.getId());
        for (CardTasks t : tasks) {
            cardService.deleteTask(t);
        }
        if (task != null) {
            cardService.deleteCard(task);
        }
        return ResponseEntity.ok(task);
    }


    @GetMapping(value = "/getTasks/{card_id}")
    public ResponseEntity<?> getCardTasks (@PathVariable Long card_id){
        System.out.println("GET TASKS");
        List<CardTasks> cardTasks = cardService.getAllCardsByCardId(card_id);
        System.out.println("GET TASKS 2");
        return new ResponseEntity<>(cardTasks, HttpStatus.OK);
    }



    @PostMapping(value = "/addTask")
    public ResponseEntity<?> addCardTask (@RequestBody CardTasks task){
        Long card_id = task.getId();
        Cards card = cardService.getCard(card_id);

        task.setCard(card);
        task.setId(null);
        Date currentSqlDate = new Date(System.currentTimeMillis());
        task.setAddedDate(currentSqlDate);

        cardService.addTask(task);

        return ResponseEntity.ok(task);
    }



    @PostMapping(value = "/update_status/{id}")
    public ResponseEntity<?> updateStatusCardTask (@PathVariable Long id){

        CardTasks task = cardService.getTask(id);
        task.setDone(!task.isDone());
        cardService.editStatus(task);
        return ResponseEntity.ok(task);
    }
    @PutMapping (value = "/update_cardtask")
    public ResponseEntity<?> updateCardTask(@RequestBody CardTasks task){
        System.out.println(task + " -------- ");
        cardService.addTask(task);
        return ResponseEntity.ok(task);
    }
    @PostMapping(value = "/delete_cardtask/{id}")
    public ResponseEntity<?> addCardTask(@PathVariable Long id){

        CardTasks task = cardService.getTask(id);
        if (task!=null) {
            cardService.deleteTask(task);
        }
        return ResponseEntity.ok(task);
    }
    @GetMapping(value = "/searchByName/{name}")
    public ResponseEntity<?> getCardByName(@PathVariable String name) {
        List<Cards> cards=cardService.getAllCardsByName(name);
        System.out.println(cards);
        if (cards!=null) {
            return new ResponseEntity<>(cards, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
