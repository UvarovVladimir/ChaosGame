package com.dreamteam.chaosgame.api.dtos;

import java.util.List;

public class CardsByParamsResponseDTO {

    private List<CardDTO> cards;

    private int limit;

    private int offset;

    private boolean hasNext;

    private int totalCount;


    public CardsByParamsResponseDTO(List<CardDTO> cards,
                                    int limit,
                                    int offset,
                                    boolean hasNext,
                                    int totalCount) {
        this.cards = cards;
        this.limit = limit;
        this.offset = offset;
        this.hasNext = hasNext;
        this.totalCount = totalCount;
    }

    public List<CardDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardDTO> cards) {
        this.cards = cards;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
