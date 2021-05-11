package com.atexo.carte.service.impl;


import com.atexo.carte.constant.ConstantField;
import com.atexo.carte.dao.CardRepository;
import com.atexo.carte.dto.CardDto;
import com.atexo.carte.dto.MainDto;
import com.atexo.carte.entity.Card;
import com.atexo.carte.enumeration.Color;
import com.atexo.carte.enumeration.Figure;
import com.atexo.carte.exceptions.CardException;
import com.atexo.carte.service.ICardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class CardServiceImpl implements ICardService, ConstantField {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardException.class);

    /**
     * Inject Repository
     */
    @Autowired
    private CardRepository cardRepository;

    @Override
    public MainDto getRandomMainCards() throws CardException {
        List<Card> mainCards = constructCards();
        if (!mainCards.isEmpty()){
            return disturbCard(mainCards);
        }
        return null;
    }

    @Override
    public List<Card> constructCards() throws CardException {
        List<Card> cards = new ArrayList<>();
        try{
            for(byte color = 0; color < Color.values().length; color++){
                for (Figure figure : Figure.values()){
                    byte value = figure.getCode().byteValue();
                    cards.add(new Card(value, color));
                }
                for(byte value = 0; value <=8; value++){
                    cards.add(new Card(value, color));
                }
            }
        } catch (Exception e){
            LOGGER.error(createCardError ,e.getMessage());
            throw new CardException(createCardError + e.getMessage(), e);
        }
        return cards;
    }

    @Override
    public List<Card> saveCards() throws CardException {
        /**
         * Pour n'est pas sauvegarder à cahque fois les cartes (Toujours on a 52 cartes)
         */
        if (!cardRepository.findAll().isEmpty()){
            return cardRepository.findAll();
        }
        List<Card> cards = constructCards();
        cards.forEach(card -> {
            card.setLibelle(card.toString().toUpperCase());
        });
        return cardRepository.saveAll(cards);
    }

    private MainDto disturbCard(List<Card> cards) throws CardException{
        List<CardDto> cardsDto = new ArrayList<>();
        try{
            List<Card> mainCard = createMainCard(cards);
            for (Card card : mainCard){
                cardsDto.add(transformToDto(card));
            }
        } catch (Exception e){
            throw new CardException(e);
        }
        return new MainDto(cardsDto);
    }

    private List<Card> createMainCard(List<Card> cards) {
        int main = 10;
        List<Card> mainCard = new ArrayList<>();
        Random random = new Random();
        while (main > 0){
            int randomInt = random.nextInt(cards.size());
            Card card = cards.get(randomInt);
            if(!mainCard.contains(card)){
                mainCard.add(card);
                main--;
            }
        }
        /**
         * Mélanger la liste
         */
        Collections.shuffle(mainCard);
        return mainCard;
    }

    @Override
    public CardDto transformToDto(Card card) throws CardException {
        CardDto cardDto;
        try {
            cardDto = new CardDto(card.getId(), card.getValue(), card.getColor(), card.getLibelle());
        } catch (Exception e){
            LOGGER.error(transformError, e.getMessage(), e);
            throw new CardException(transformError, e);
        }
        return cardDto;
    }
}
