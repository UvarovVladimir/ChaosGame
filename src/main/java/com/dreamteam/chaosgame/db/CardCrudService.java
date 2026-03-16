package com.dreamteam.chaosgame.db;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card get(String cardId) {
        int id = Integer.parseInt(cardId);
        Optional<Card> optional = cardRepository.findById((long) id);
        return optional.orElse(null);
    }

    public  public List<Card> getCardsByParams(String name,
                                               CardType type,
                                               CardRang rang,
                                               Rarety rarety,
                                               int offset,
                                               int limit) {


        return cardRepository.getCardsByParams(name, type, rang, rarety, offset, limit);
    }
    Card update(Card card) {
        // Проверяем существование
        Optional<Card> existing = cardRepository.findById((long) card.getId());
        if (existing.isEmpty()) {
            throw new RuntimeException("Card not found with id: " + card.getId());
        }
        return cardRepository.save(card);
    }

    public Card remove(int cardId) {
        Optional<Card> optional = cardRepository.findById((long) cardId);
        if (optional.isPresent()) {
            Card card = optional.get();
            cardRepository.deleteById((long) cardId);
            return card;
        } else {
            throw new RuntimeException("Card not found with id: " + cardId);
        }
    }
}