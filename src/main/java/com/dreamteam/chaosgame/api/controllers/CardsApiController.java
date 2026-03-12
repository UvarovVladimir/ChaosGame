package com.dreamteam.chaosgame.api.controllers;

import com.dreamteam.chaosgame.api.dtos.CardDTO;
import com.dreamteam.chaosgame.api.mappers.CardMapper;
import com.dreamteam.chaosgame.api.validators.CardCreateApiValidator;
import com.dreamteam.chaosgame.business.CardManagerService;
import com.dreamteam.chaosgame.db.Card;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CardsApiController {

    private final CardManagerService cardManagerService;
    private final CardCreateApiValidator cardCreateApiValidator;
    private final CardMapper cardMapper;

    public CardsApiController(CardManagerService cardManagerService,
                              CardCreateApiValidator cardCreateApiValidator,
                              CardMapper cardMapper) {
        this.cardManagerService = cardManagerService;
        this.cardCreateApiValidator = cardCreateApiValidator;
        this.cardMapper = cardMapper;
    }

    @GetMapping("/cards/{cardId}")
    public CardDTO getCard(@PathVariable("cardId") String cardId,
                           @RequestParam(name = "type", required = false) String type) {
        // TODO: возможно, использовать type для фильтрации, пока игнорируем
        Card card = cardManagerService.getCard(cardId);
        return cardMapper.mapEntityToDTO(card);
    }

    @PostMapping("/cards")
    public CardDTO createNewCard(@RequestBody CardDTO cardDTO) {
        cardCreateApiValidator.validate(cardDTO);
        Card card = cardMapper.mapDtoToEntity(cardDTO);
        Card createdCard = cardManagerService.createCard(card);
        return cardMapper.mapEntityToDTO(createdCard);
    }

    /**
     * Частичное обновление полей.
     */
    @PatchMapping("/cards/{cardId}")
    public CardDTO updateCardFields(@PathVariable("cardId") String cardId,
                                    @RequestBody Map<String, Object> updates) {
        Card updatedCard = cardManagerService.updateCardFields(cardId, updates);
        return cardMapper.mapEntityToDTO(updatedCard);
    }

    /**
     * Полная замена карты.
     */
    @PutMapping("/cards/{cardId}")
    public CardDTO updateCard(@PathVariable("cardId") int cardId,
                              @RequestBody CardDTO cardDTO) {
        cardCreateApiValidator.validate(cardDTO);
        Card cardFromUI = cardMapper.mapDtoToEntity(cardDTO);
        cardFromUI.setId(cardId);
        Card updatedCard = cardManagerService.updateCard(cardFromUI);
        return cardMapper.mapEntityToDTO(updatedCard);
    }

    /**
     * Удаление карты.
     */
    @DeleteMapping("/cards/{cardId}")
    public CardDTO deleteCards(@PathVariable("cardId") String cardId) {
        Card removedCard = cardManagerService.removeCard(cardId);
        return cardMapper.mapEntityToDTO(removedCard);
    }
}