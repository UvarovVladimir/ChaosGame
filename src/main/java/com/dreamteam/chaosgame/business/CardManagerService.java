package com.dreamteam.chaosgame.business;

import com.dreamteam.chaosgame.db.Card;
import com.dreamteam.chaosgame.db.CardCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO https://github.com/UvarovVladimir/ChaosGame/issues/8
 */
@Service
public class CardManagerService {

    private final CardValidationService cardValidationService;
    private final CardCrudService cardCrudService;

    @Autowired
    public CardManagerService(CardValidationService cardValidationService,
                              CardCrudService cardCrudService) {

        this.cardValidationService = cardValidationService;
        this.cardCrudService = cardCrudService;
    }

    /**
     * Создание карты.
     * Сохранение карты в БД.
     */
    public Card createCard(Card card) {

        cardValidationService.validateNewCard(card);

        // TODO * Сохранить картинку карты на диск

        cardCrudService.create(card);

        return card;
    }

    public Card updateCard(Card card) {

        // TODO сделать валидацию карты https://github.com/UvarovVladimir/ChaosGame/issues/8 ??? или в отд задаче

        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/8
        return card;

    }

    public Card updateCardFields() {
        // TODO сделать валидацию карты https://github.com/UvarovVladimir/ChaosGame/issues/8 ??? или в отд задаче

        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/8
        return new Card();

    }

    public Card removeCard(String cardId) {
        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/8
        return new Card();

    }

    public Card getCard(String cardId) {
        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/8
        return new Card();
    }
}
