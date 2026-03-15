package com.dreamteam.chaosgame.db;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CardRepository extends CrudRepository<Card, Long> {


    List<Card> getCardsByParams(String name, CardType type, CardRang rang, Rarety rarety, int offset, int limit);
}
