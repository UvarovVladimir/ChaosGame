package com.dreamteam.chaosgame.api.mappers;

import com.dreamteam.chaosgame.api.dtos.CardDTO;
import com.dreamteam.chaosgame.db.Card;
import org.springframework.stereotype.Service;

@Service
public class CardMapper {

    public Card mapDtoToEntity(CardDTO cardDTO) {
        return new Card(
                cardDTO.getName(),
                cardDTO.getType(),
                cardDTO.getRang(),
                cardDTO.getRarety(),
                cardDTO.getDuration(),
                cardDTO.getRecoveryTime(),
                cardDTO.getOriginImage()
        );
    }

    public CardDTO mapEntityToDTO(Card card) {
        return new CardDTO(
                String.valueOf(card.getId()),
                card.getName(),
                card.getType(),
                card.getRang(),
                card.getRarety(),
                card.getDuration(),
                card.getRecoveryTime(),
                card.getOriginImage()
        );
    }
}
