package com.dreamteam.chaosgame.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Сервис отвечающий за СRUD-операции с сущностью {@link  Card}.
 */
@Service
public class CardCrudService {

    private final CardRepository cardRepository;

    @Autowired
    public CardCrudService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    /**
     * Создание новой карты в БД.
     */
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card get(String cardId) {

        // TODO !!!!
        return new Card();
    }

    public Card update(Card card) {

        // TODO !!!!
        return card;
    }


    public Card remove(int cardId) {

        // TODO !!!!
        return new Card();
    }


}
