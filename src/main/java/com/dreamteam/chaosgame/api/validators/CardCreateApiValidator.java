package com.dreamteam.chaosgame.api.validators;

import com.dreamteam.chaosgame.api.dtos.CardDTO;
import org.springframework.stereotype.Service;

@Service
public class CardCreateApiValidator {

    public void validate(CardDTO cardDTO) {

        if (cardDTO == null) {
            throwValidateException("Object is null");
        }

        if (cardDTO.getName() == null || cardDTO.getName().isBlank()) {
            throwValidateException("Object is null");

        }

        if (cardDTO.getRang() == null) {
            throwValidateException("Object is null");
        }

    }

    private void throwValidateException(String message) {
        throw new IllegalArgumentException(message);
    }
}
