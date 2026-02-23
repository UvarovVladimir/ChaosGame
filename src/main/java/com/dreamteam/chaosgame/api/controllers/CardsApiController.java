package com.dreamteam.chaosgame.api.controllers;

import com.dreamteam.chaosgame.api.dtos.CardDTO;
import com.dreamteam.chaosgame.api.validators.CardCreateApiValidator;
import com.dreamteam.chaosgame.business.CardManagerService;
import com.dreamteam.chaosgame.db.Card;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardsApiController {

    private final CardManagerService cardManagerService;
    private final CardCreateApiValidator cardCreateApiValidator;

    public CardsApiController(CardManagerService cardManagerService,
                              CardCreateApiValidator cardCreateApiValidator) {
        this.cardManagerService = cardManagerService;
        this.cardCreateApiValidator = cardCreateApiValidator;
    }


    @GetMapping("/cards/{cardId}")
    public CardDTO getCard(@PathVariable("infoId") String infoId,
                           @RequestParam(name = "type", required = false) String type) {


        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/7
        return new CardDTO();
    }


    @PostMapping("/cards")
    public CardDTO createNewCard(@RequestBody CardDTO cardDTO) {

        cardCreateApiValidator.validate(cardDTO);

        Card cardFromUI = new Card(cardDTO.getName(),
                cardDTO.getType(),
                cardDTO.getRang(),
                cardDTO.getRarety(),
                cardDTO.getDuration(),
                cardDTO.getRecoveryTime()
        );

        Card createdCard = cardManagerService.createCard(cardFromUI);

        return new CardDTO(createdCard.getName(),
                createdCard.getType(),
                createdCard.getRang(),
                createdCard.getRarety(),
                createdCard.getDuration(),
                createdCard.getRecoveryTime()
        );
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
