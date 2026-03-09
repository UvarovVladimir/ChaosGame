package com.dreamteam.chaosgame.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardRepository extends CrudRepository<Card, Long> {

}
