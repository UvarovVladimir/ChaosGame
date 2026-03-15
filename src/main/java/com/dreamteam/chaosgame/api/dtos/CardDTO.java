package com.dreamteam.chaosgame.api.dtos;

import java.time.Duration;

/**
 * Это объект для свяки с UI при CRUD операциях с картами игрока.
 */
public class CardDTO {

    private String id;
    private String name;
    private CardType type;
    private CardRang rang;
    private Rarety rarety;
    private Duration duration;
    private Duration recoveryTime;

    public CardDTO() {
    }

    public CardDTO(String id,
                   String name,
                   CardType type,
                   CardRang rang,
                   Rarety rarety,
                   Duration duration,
                   Duration recoveryTime) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.rang = rang;
        this.rarety = rarety;
        this.duration = duration;
        this.recoveryTime = recoveryTime;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CardType getType() {
        return type;
    }

    public CardRang getRang() {
        return rang;
    }

    public Rarety getRarety() {
        return rarety;
    }

    public Duration getDuration() {
        return duration;
    }

    public Duration getRecoveryTime() {
        return recoveryTime;
    }

}
