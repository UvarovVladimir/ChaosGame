package com.dreamteam.chaosgame.db;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;

import java.time.Duration;

public class Card {

    private String id;
    private String name;
    private CardType type;
    private CardRang rang;
    private Rarety rarety;
    private Duration duration;
    private Duration recoveryTime;

    public Card() {
    }

    public Card(String name,
                CardType type,
                CardRang rang,
                Rarety rarety,
                Duration duration,
                Duration recoveryTime) {
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
