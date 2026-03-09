package com.dreamteam.chaosgame.business;

import com.dreamteam.chaosgame.db.Card;
import org.springframework.stereotype.Service;


/**
 * Сервис отвечающий за проверку наличия дубликатов карт в БД при создании карты.
 */
@Service
public class DuplicateCardService {

    public boolean isDuplicate(Card card) {

        // TODO https://github.com/UvarovVladimir/ChaosGame/issues/21

        return false;
    }
}
