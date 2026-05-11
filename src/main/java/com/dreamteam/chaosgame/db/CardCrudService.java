package com.dreamteam.chaosgame.db;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


    public Card get(String cardId) {
        int id = Integer.parseInt(cardId);
        Optional<Card> optional = cardRepository.findById((long) id);
        return optional.orElse(null);
    }

    public Page<Card> getCardsByParams(String name,
                                       CardType type,
                                       CardRang rang,
                                       Rarety rarety,
                                       int pageNumber,
                                       int limit) {

        // Комбинируем все спецификации через and()
        Specification<Card> spec = Specification.unrestricted();
        spec = spec
                .and(CardSpecifications.nameContains(name))
                .and(CardSpecifications.typeEquals(type))
                .and(CardSpecifications.rangEquals(rang))
                .and(CardSpecifications.raretyEquals(rarety));


        // Создаём Pageable: page — номер страницы (начиная с 0), size — количество элементов на странице
        Pageable pageable = PageRequest.of(pageNumber, limit);

        // Выполняем запрос с пагинацией
        return cardRepository.findAll(spec, pageable);
    }

    /**
     * Создание новой карты в БД.
     */
    public Card create(Card card) {
        return cardRepository.save(card);
    }

    public Card update(Card card) {
        // Проверяем существование
        Optional<Card> existing = cardRepository.findById((long) card.getId());
        if (existing.isEmpty()) {
            throw new RuntimeException("Card not found with id: " + card.getId());
        }
        return cardRepository.save(card);
    }

    public Card updateFields(Card card) {

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