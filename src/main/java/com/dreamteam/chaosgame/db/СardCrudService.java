package com.dreamteam.chaosgame.db;

import org.springframework.data.repository.CrudRepository;

public interface СardCrudService extends CrudRepository<Card, Long> {

    // TODO сделать валидацию карты https://github.com/UvarovVladimir/ChaosGame/issues/8 ???
}
