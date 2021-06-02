package kz.csse.jwtauth.reactjwtauth.services;



import kz.csse.jwtauth.reactjwtauth.entities.CardTasks;
import kz.csse.jwtauth.reactjwtauth.entities.Cards;

import java.util.List;

public interface CardService {
    List<Cards> getAllCards();
    Cards addCard(Cards card);
    Cards getCard(Long id);
    Cards editCard(Cards Card);
    void deleteCard(Cards Card);
    List<Cards> getAllCardsByName(String name);

    void deleteTask(CardTasks task);
    CardTasks addTask(CardTasks task);
    CardTasks getTask(Long id);
    CardTasks editStatus(CardTasks task);
    List<CardTasks> getAllCardsByCardId(Long id);



}
