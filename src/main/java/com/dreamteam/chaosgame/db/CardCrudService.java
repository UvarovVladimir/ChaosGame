package com.dreamteam.chaosgame.db;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Card get(int cardId) {

        // TODO !!!!
        return new Card();
    }

    public List<Card> getCardsByParams(String name,
                                       CardType type,
                                       CardRang rang,
                                       Rarety rarety,
                                       int offset,
                                       int limit) {


        return cardRepository.getCardsByParams(name, type, rang, rarety, offset, limit);
    }

    /**
     * Создание новой карты в БД.
     */
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card update(Card card) {

        return cardRepository.save(card);
    }

    public Card updateFields(Card card) {

        // TODO !!!!

        return cardRepository.save(card);
    }


    public Card remove(int cardId) {

        // TODO !!!!
        return new Card();
    }

}
