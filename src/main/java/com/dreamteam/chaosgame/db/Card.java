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

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public void setRang(CardRang rang) {
        this.rang = rang;
    }

    public void setRarety(Rarety rarety) {
        this.rarety = rarety;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setRecoveryTime(Duration recoveryTime) {
        this.recoveryTime = recoveryTime;
    }
}
