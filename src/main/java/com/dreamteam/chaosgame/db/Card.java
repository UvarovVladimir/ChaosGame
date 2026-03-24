package com.dreamteam.chaosgame.db;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;
import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private CardType type;

    @Column(name = "rang", nullable = false)
    private CardRang rang;

    @Column(name = "rarety", nullable = false)
    private Rarety rarety;

    @Column(name = "duration", nullable = false)
    private Duration duration;

    @Column(name = "recoveryTime", nullable = false)
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardRang getRang() {
        return rang;
    }

    public void setRang(CardRang rang) {
        this.rang = rang;
    }

    public Rarety getRarety() {
        return rarety;
    }

    public void setRarety(Rarety rarety) {
        this.rarety = rarety;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(Duration recoveryTime) {
        this.recoveryTime = recoveryTime;
    }
}
