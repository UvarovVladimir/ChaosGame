package com.dreamteam.chaosgame.business;

import com.dreamteam.chaosgame.db.Card;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CardValidationService {

    private final DuplicateCardService duplicateCardService;

    public CardValidationService(DuplicateCardService duplicateCardService) {
        this.duplicateCardService = duplicateCardService;
    }

    public void validateNewCard(Card card) {
        if (card.getId() != 0) {
            throw new RuntimeException("Card id must be 0 while creating new card");
        }
        if (ObjectUtils.isEmpty(card.getName())) {
            throw new RuntimeException("Card name must not be empty");
        }
        if (card.getType() == null) {
            throw new RuntimeException("Card type must not be null");
        }
        if (card.getRang() == null) {
            throw new RuntimeException("Card rang must not be null");
        }
        if (card.getRarety() == null) {
            throw new RuntimeException("Card rarety must not be null");
        }
        if (card.getDuration() == null) {
            throw new RuntimeException("Card duration must not be null");
        }
        if (card.getRecoveryTime() == null) {
            throw new RuntimeException("Card recoveryTime must not be null");
        }

        if (duplicateCardService.isDuplicate(card)) {
            throw new RuntimeException("Card is duplicated");
        }
        duplicateCardService.isDuplicate(card);
    }
}