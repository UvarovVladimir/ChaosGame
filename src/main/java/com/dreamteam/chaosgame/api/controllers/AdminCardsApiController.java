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
    public CardDTO getCard(@PathVariable("cardId") String cardId) {

        Card card = cardManagerService.getCard(cardId);

        // TODO !!!!
        return new CardDTO(card.getName(),
                card.getType(),
                card.getRang(),
                card.getRarety(),
                card.getDuration(),
                card.getRecoveryTime()
        );
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
        return new CardDTO(card.getName(), card.getType(), card.getRang(), card.getRarety(), card.getDuration(), card.getRecoveryTime());
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


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
            @RequestParam("cardId") int cardId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "description", required = false) String description) {

        // Проверка на пустой файл
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Файл не выбран");
        }
        String imagePath;
        try {
            imagePath = cardManagerService.uploadCardIcon(cardId, file);
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Проблемы с загрузкой файла");
        }

        return ResponseEntity.ok(imagePath);
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get("/home/slider/Downloads/ChaosGame/").resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Определяем Content-Type
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
