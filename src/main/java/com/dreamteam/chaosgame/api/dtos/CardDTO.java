package com.dreamteam.chaosgame.api.dtos;

import java.time.Duration;

/**
 * Это объект для свяки с UI при CRUD операциях с картами игрока.
 */
public class CardDTO {

    private String id;
    private String name;
    private String fullDescription;
    private CardType type;
    private CardRang rang;
    private Rarety rarety;
    private Duration duration;
    private Duration recoveryTime;
    private String originImage;


    public CardDTO() {
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

    public String getOriginImage() {
        return originImage;
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

    public void setOriginImage(String originImage) {
        this.originImage = originImage;
    }


    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

}
