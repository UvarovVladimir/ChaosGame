package com.dreamteam.chaosgame.db;

import com.dreamteam.chaosgame.api.dtos.CardRang;
import com.dreamteam.chaosgame.api.dtos.CardType;
import com.dreamteam.chaosgame.api.dtos.Rarety;
import org.springframework.data.jpa.domain.Specification;

public class CardSpecifications {

    public static Specification<Card> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); // условие, которое всегда истинно
            }
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Card> typeEquals(CardType type) {
        return (root, query, criteriaBuilder) -> {
            if (type == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("type"), type);
        };
    }

    public static Specification<Card> rangEquals(CardRang rang) {
        return (root, query, criteriaBuilder) -> {
            if (rang == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("rang"), rang);
        };
    }

    public static Specification<Card> raretyEquals(Rarety rarety) {
        return (root, query, criteriaBuilder) -> {
            if (rarety == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("rarety"), rarety);
        };
    }
}