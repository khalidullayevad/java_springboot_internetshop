package kz.csse.jwtauth.reactjwtauth.services.impl;


import kz.csse.jwtauth.reactjwtauth.entities.CardTasks;
import kz.csse.jwtauth.reactjwtauth.entities.Cards;
import kz.csse.jwtauth.reactjwtauth.repositories.CardRepository;
import kz.csse.jwtauth.reactjwtauth.repositories.CardTaskRepository;
import kz.csse.jwtauth.reactjwtauth.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImp implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardTaskRepository taskRepository;

    @Override
    public List<Cards> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public Cards addCard(Cards card) {
        return cardRepository.save(card);
    }

    @Override
    public Cards getCard(Long id) {
        return cardRepository.findByIdEquals(id);
    }

    @Override
    public Cards editCard(Cards Card) {
        return cardRepository.save(Card);
    }

    @Override
    public void deleteCard(Cards Card) {
        cardRepository.delete(Card);

    }

    @Override
    public List<Cards> getAllCardsByName(String name) {
        return cardRepository.findAllByName(name);
    }

    @Override
    public void deleteTask(CardTasks task) {
        taskRepository.delete(task);
    }

    @Override
    public CardTasks addTask(CardTasks task) {
        return taskRepository.save(task);
    }

    @Override
    public CardTasks getTask(Long id) {
        return taskRepository.findByIdEquals(id);
    }

    @Override
    public CardTasks editStatus(CardTasks task) {
        return taskRepository.save(task);
    }

    @Override
    public List<CardTasks> getAllCardsByCardId(Long id) {
        return taskRepository.findAllByCard_Id(id);
    }

}
