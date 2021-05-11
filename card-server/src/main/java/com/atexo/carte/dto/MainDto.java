package com.atexo.carte.dto;

import com.atexo.carte.response.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
public class MainDto extends Response implements Serializable {

    private static final long serialVersionUID = 1239805673254256789L;

    @Getter @Setter
    private List<CardDto> mainCards;

    @Getter @Setter
    private List<CardDto> mainCardsSorted;

    public MainDto(List<CardDto> cards){
        this.mainCards = cards;
        List<CardDto> sortedCards = new ArrayList<>(cards);
        sortedCards.sort(Comparator.comparing(CardDto::getColor).thenComparing(CardDto::getValue));
        this.mainCardsSorted = sortedCards;
    }
}
