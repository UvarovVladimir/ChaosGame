package com.dreamteam.chaosgame.business;

import com.dreamteam.chaosgame.db.Card;
import com.dreamteam.chaosgame.db.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class DuplicateCardService {

    private final CardRepository cardRepository;

    public DuplicateCardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public boolean isDuplicate(Card card) {
        // Проверяем, существует ли карта с таким же именем
        // Для полноценной проверки можно добавить другие поля
        return cardRepository.findByName(card.getName()).isPresent();
    }
}

