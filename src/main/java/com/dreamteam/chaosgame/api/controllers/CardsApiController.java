package com.dreamteam.chaosgame.api.controllers;

import com.dreamteam.chaosgame.api.dtos.CardDTO;
import com.dreamteam.chaosgame.api.mappers.CardMapper;
import com.dreamteam.chaosgame.api.validators.CardCreateApiValidator;
import com.dreamteam.chaosgame.business.CardManagerService;
import com.dreamteam.chaosgame.db.Card;
import org.springframework.web.bind.annotation.*;

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
    public CardDTO getCard(@PathVariable("infoId") String infoId,
                           @RequestParam(name = "type", required = false) String type) {

        // TODO !!!!
        return new CardDTO();
    }


    @PostMapping("/cards")
    public CardDTO createNewCard(@RequestBody CardDTO cardDTO) {

        cardCreateApiValidator.validate(cardDTO);

        Card cardFromUI = cardMapper.mapDtoToEntity(cardDTO);

        Card createdCard = cardManagerService.createCard(cardFromUI);

        return cardMapper.mapEntityToDTO(createdCard);
    }


    /**
     * Частичное обновление полей.
     */
    @PatchMapping("/cards/{cardId}")
    public CardDTO updateCardFields(@PathVariable("infoId") String infoId,
                                    @RequestParam(name = "type", required = false) String type) {


        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/7
        return new CardDTO();
    }


    /**
     * Полная замена карты.
     */
    //Обрабатываем поступающие HTTP PUT запросы по URL "/cards/{cardId}"
    @PutMapping("/cards/{cardId}")
    // Извлекаем из пути значение переменной {cardId} и присваиваем параметру cardId
    public CardDTO updateCard(@PathVariable("cardId") int cardId,
                              // Преобразуем тело запроса (JSON) в объект CardDTO и связываем с параметром
                              @RequestBody CardDTO cardDTO) {
        // Валидация входящих данных: проверяем корректность полей CardDTO
        cardCreateApiValidator.validate(cardDTO);
        // Преобразуем DTO в сущность Card (объект базы данных)
        Card cardFromUI = cardMapper.mapDtoToEntity(cardDTO);
        // Устанавливаем идентификатор карты, полученный из пути запроса, в сущность
        cardFromUI.setId(cardId);
        // Вызываем сервис для обновления карты в БД; возвращаем обновлённую сущность
        Card createdCard = cardManagerService.updateCard(cardFromUI);
        // Преобразуем обновлённую сущность обратно в DTO и возвращаем в ответе
        return cardMapper.mapEntityToDTO(createdCard);
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
