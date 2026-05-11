package com.dreamteam.chaosgame.api.mappers;

import com.dreamteam.chaosgame.api.dtos.CardDTO;
import com.dreamteam.chaosgame.db.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(source = "description", target = "fullDescription")
    CardDTO toDto(Card card);

    @Mapping(source = "fullDescription", target = "description")
    Card toEntity(CardDTO cardDto);

}
