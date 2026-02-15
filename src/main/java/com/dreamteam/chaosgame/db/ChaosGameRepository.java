package com.dreamteam.chaosgame.db;

import org.springframework.data.repository.CrudRepository;

public interface ChaosGameRepository extends CrudRepository<Player, Long> {
}