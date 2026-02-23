package com.dreamteam.chaosgame.api.controllers;

import com.dreamteam.chaosgame.api.CardDTO;
import com.dreamteam.chaosgame.business.CardManagerService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsApiController {

    private final CardManagerService cardManagerService;

    public CardsApiController(CardManagerService cardManagerService) {
        this.cardManagerService = cardManagerService;
    }


    @GetMapping("/cards/{cardId}")
    public CardDTO getCard(@PathVariable("infoId") String infoId,
                           @RequestParam(name = "type", required = false) String type) {


        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/7
        return new CardDTO();
    }


    @PostMapping("/cards")
    public CardDTO createNewCard(@RequestBody CardDTO cardDTO) {

        // Определиться с полями DTO

        // Валидация запроса

        // Вызвать менеджер работы с картами для сохранения

        return new CardDTO();
    }


    @PatchMapping("/cards/{cardId}")
    public CardDTO updateCardFields(@PathVariable("infoId") String infoId,
                                        @RequestParam(name = "type", required = false) String type) {


        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/7
        return new CardDTO();
    }


    @PutMapping("/cards/{cardId}")
    public CardDTO updateCard(@PathVariable("infoId") String infoId,
                                  @RequestParam(name = "type", required = false) String type) {

        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/7
        return new CardDTO();
    }


    @DeleteMapping("/cards/{cardId}")
    public CardDTO deleteCards(@PathVariable("infoId") String infoId,
                                   @RequestParam(name = "type", required = false) String type) {

        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/7
        return new CardDTO();
    }

}
