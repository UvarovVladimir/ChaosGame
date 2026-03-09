package com.dreamteam.chaosgame.business;

import com.dreamteam.chaosgame.db.Card;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * Сервис отвечающий за валидацию сущности {@link Card} с использованием бизнес логики.
 */
@Service
public class CardValidationService {

    private final DuplicateCardService duplicateCardService;

    public CardValidationService(DuplicateCardService duplicateCardService) {
        this.duplicateCardService = duplicateCardService;
    }

    public void validateNewCard(Card card) {

        if (card.getId() != 0) {
            throw new RuntimeException("Card id must be 0 while create new card");
        }

        if (ObjectUtils.isEmpty(card.getName())) {
            throw new RuntimeException("Card id must be 0 while create new card");
        }

        if (card.getType() == null) {
            throw new RuntimeException("Card id must be 0 while create new card");
        }

        if (card.getRang() == null) {
            throw new RuntimeException("Card id must be 0 while create new card");
        }

        if (card.getRarety() == null) {
            throw new RuntimeException("Card id must be 0 while create new card");
        }

        if (card.getDuration() == null) {
            throw new RuntimeException("Card id must be 0 while create new card");
        }

        if (card.getRecoveryTime() == null) {
            throw new RuntimeException("Card id must be 0 while create new card");
        }

        duplicateCardService.isDuplicate(card);




    }
}
