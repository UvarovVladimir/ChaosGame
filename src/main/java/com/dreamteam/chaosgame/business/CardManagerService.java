package com.dreamteam.chaosgame.business;

import com.dreamteam.chaosgame.db.Card;
import com.dreamteam.chaosgame.db.CardCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    public Card createCard(Card card) {
        cardValidationService.validateNewCard(card);
        // TODO: сохранить картинку карты на диск
        return cardCrudService.create(card);
    }

    public Card updateCard(Card card) {
        // Проверяем, что карта существует
        Card existing = cardCrudService.get(String.valueOf(card.getId()));
        if (existing == null) {
            throw new RuntimeException("Card with id " + card.getId() + " not found");
        }
        // Можно добавить бизнес-валидацию для обновления
        return cardCrudService.update(card);
    }

    public Card updateCardFields(String cardId, Map<String, Object> updates) {
        Card existing = cardCrudService.get(cardId);
        if (existing == null) {
            throw new RuntimeException("Card with id " + cardId + " not found");
        }
        // Применяем обновления (упрощённо, через рефлексию или вручную)
        // В реальном проекте лучше использовать специальный DTO или JsonPatch
        // Здесь для простоты реализуем через проверку ключей
        updates.forEach((key, value) -> {
            switch (key) {
                case "name":
                    existing.setName((String) value);
                    break;
                case "type":
                    existing.setType(com.dreamteam.chaosgame.api.dtos.CardType.valueOf((String) value));
                    break;
                case "rang":
                    existing.setRang(com.dreamteam.chaosgame.api.dtos.CardRang.valueOf((String) value));
                    break;
                case "rarety":
                    existing.setRarety(com.dreamteam.chaosgame.api.dtos.Rarety.valueOf((String) value));
                    break;
                case "duration":
                    // Duration можно передавать как число секунд или строку
                    existing.setDuration(java.time.Duration.parse((String) value));
                    break;
                case "recoveryTime":
                    existing.setRecoveryTime(java.time.Duration.parse((String) value));
                    break;
                // поле id не обновляем
            }
        });
        return cardCrudService.update(existing);
    }

    public Card removeCard(String cardId) {
        return cardCrudService.remove(Integer.parseInt(cardId));
    }

    public Card getCard(String cardId) {
        Card card = cardCrudService.get(cardId);
        if (card == null) {
            throw new RuntimeException("Card with id " + cardId + " not found");
        }
        return card;
    }
}