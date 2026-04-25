package com.dreamteam.chaosgame.business;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;
import com.dreamteam.chaosgame.db.Card;
import com.dreamteam.chaosgame.db.CardCrudService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

    public Card updateCardFields(Card card) {
        int cardId = card.getId();
        Card cardFromDb = cardCrudService.get(String.valueOf(cardId));
        if (cardFromDb == null) {
            throw new RuntimeException("Card with id " + cardId + " not found");
        }
        // достать карту по ID
        //
        // Card cardFromDb = cardCrudService.get(cardId);


        // Обновить все поля пришедшие из UI в объекте из БД
        if (card.getName() != null) {
            cardFromDb.setName(card.getName());
        }
        if (card.getRang() != null) {
            cardFromDb.setRang(card.getRang());
        }
        if (card.getDuration() != null) {
            cardFromDb.setDuration(card.getDuration());
        }
        if (card.getRecoveryTime() != null) {
            cardFromDb.setRecoveryTime(card.getRecoveryTime());
        }
        if (card.getRarety() != null) {
            cardFromDb.setRarety(card.getRarety());
        }
        if (card.getType() != null) {
            cardFromDb.setType(card.getType());
        }


        // Вызвать обновление

        return cardCrudService.updateFields(cardFromDb);
    }

    /**
     * @return path до сохраненного файла.
     */
    public String uploadCardIcon(int cardId, MultipartFile file) throws IOException {

        Card card = cardCrudService.get(cardId);
        String uploadPath = "/home/slider/Downloads/ChaosGame/" + cardId;

        // Получаем информацию о файле
        String originalFilename = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();
        byte[] content = file.getBytes();

        // Здесь логика сохранения файла
        // Например, сохранение на диск:
        file.transferTo(new java.io.File(uploadPath));


        card.setOriginImage(uploadPath);

        cardCrudService.update(card);
        return uploadPath;
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

    public Page<Card> getCardsByParams(@Nullable String name,
                                       @Nullable CardType type,
                                       @Nullable CardRang rang,
                                       @Nullable Rarety rarety,
                                       int pageNumber,
                                       int limit) {

        return cardCrudService.getCardsByParams(name, type, rang, rarety, pageNumber, limit);
    }

}
