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
