package com.dreamteam.chaosgame.api.controllers;

import com.dreamteam.chaosgame.api.dtos.*;
import com.dreamteam.chaosgame.api.mappers.CardMapper;
import com.dreamteam.chaosgame.business.CardManagerService;
import com.dreamteam.chaosgame.db.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCardsApiController {

    private final CardManagerService cardManagerService;
    private final CardMapper cardMapper;


    @Autowired
    public UserCardsApiController(CardManagerService cardManagerService,
                                  CardMapper cardMapper) {
        this.cardManagerService = cardManagerService;
        this.cardMapper = cardMapper;
    }


    @GetMapping("/cards")
    public CardsByParamsResponseDTO getCards(@RequestParam(name = "name", required = false) String name,
                                             @RequestParam(name = "type", required = false) CardType type,
                                             @RequestParam(name = "rang", required = false) CardRang rang,
                                             @RequestParam(name = "rarety", required = false) Rarety rarety,
                                             @RequestParam(name = "offset", required = true) int offset,
                                             @RequestParam(name = "limit", required = true) int limit) {

        // TODO Запросить слой бизнес логики чтобы вернул карты подходящие под условия
        List<Card> cardsByParams = cardManagerService.getCardsByParams(name, type, rang, rarety, offset, limit);


        //TODO Посчитать общее число карт подходящих под критерии
        int totalCount = 80; /// ХАРДКО ПОКА  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


        // TODO перемапать Card -> CardDTO
        List<CardDTO> dtoList = cardsByParams.stream()
                .map(card -> cardMapper.mapEntityToDTO(card))
                .toList();

        boolean hasNext = offset + limit < totalCount;

        // TODO Сформировать и отдать ответ с учетом пагинации
        return new CardsByParamsResponseDTO(dtoList, limit, offset, hasNext, totalCount);

    }
}
