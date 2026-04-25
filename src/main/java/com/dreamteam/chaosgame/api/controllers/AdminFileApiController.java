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
public class AdminFileApiController {

    private final CardManagerService cardManagerService;
    private final CardCreateApiValidator cardCreateApiValidator;
    private final CardMapper cardMapper;

    public AdminFileApiController(CardManagerService cardManagerService,
                                  CardCreateApiValidator cardCreateApiValidator,
                                  CardMapper cardMapper) {

        this.cardManagerService = cardManagerService;
        this.cardCreateApiValidator = cardCreateApiValidator;
        this.cardMapper = cardMapper;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(
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

            return ResponseEntity.ok("Файл успешно загружен: " + originalFilename);

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Ошибка при загрузке файла: " + e.getMessage());
        }
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
