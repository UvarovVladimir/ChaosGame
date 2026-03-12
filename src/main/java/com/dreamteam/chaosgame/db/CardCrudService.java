package com.dreamteam.chaosgame.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Card update(Card card) {
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