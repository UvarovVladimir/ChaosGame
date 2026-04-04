package com.dreamteam.chaosgame.api.controllers;

import com.dreamteam.chaosgame.api.dtos.CardDTO;
import com.dreamteam.chaosgame.api.mappers.CardMapper;
import com.dreamteam.chaosgame.api.validators.CardCreateApiValidator;
import com.dreamteam.chaosgame.business.CardManagerService;
import com.dreamteam.chaosgame.db.Card;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class AdminCardsApiController {

    private final CardManagerService cardManagerService;
    private final CardCreateApiValidator cardCreateApiValidator;
    private final CardMapper cardMapper;

    public AdminCardsApiController(CardManagerService cardManagerService,
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

        Card card = cardMapper.mapDtoToEntity(cardDTO);

        Card createdCard = cardManagerService.createCard(card);

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
    @PutMapping("/cards/{cardId}")
    public CardDTO updateCard(@PathVariable("cardId") int cardId,
                              @RequestBody CardDTO cardDTO) {

        cardCreateApiValidator.validate(cardDTO);

        Card cardFromUI = cardMapper.mapDtoToEntity(cardDTO);
        cardFromUI.setId(cardId);

        Card createdCard = cardManagerService.updateCard(cardFromUI);

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


    @PostMapping("/cards/{cardId}/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("cardId") int cardId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) {

        // Проверка на пустой файл
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Файл не выбран");
        }

        try {
            // Получаем информацию о файле
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            long size = file.getSize();
            byte[] content = file.getBytes();

            // Здесь логика сохранения файла
            // Например, сохранение на диск:
            String uploadPath = "/home/slider/Downloads/ChaosGame/" + originalFilename;
            file.transferTo(new java.io.File(uploadPath));

            // TODO !!!!!  Запись сохраненной картинки в БД карты

            return ResponseEntity.ok("Файл успешно загружен: " + originalFilename);

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}
