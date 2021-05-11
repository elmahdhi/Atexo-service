package com.atexo.carte.service;


import com.atexo.carte.dto.CardDto;
import com.atexo.carte.dto.MainDto;
import com.atexo.carte.entity.Card;
import com.atexo.carte.exceptions.CardException;

import java.util.List;

public interface ICardService {

    /**
     * Cette méthode retourne une main de 10 cartes aléatoires
     * @return
     */
    MainDto getRandomMainCards() throws CardException;


    /**
     * Cette méthode construit une liste statique de 52 carte
     * Value = AS, 5, 10, 8, 6, 5, 7, 4, 2, 3, 9, Dame, Roi, Valet
     * Couleur = Carreaux, Coeur, Pique, Trèfle
     */
    List<Card> constructCards() throws CardException;

    /**
     * Cette méthode sauvegarde les cartes dans la base H2
     * Ce service n'est pas utilisé
     */
    List<Card> saveCards() throws CardException;

    /**
     * Tranformation de l'objet Card vers un dto
     */
    CardDto transformToDto(Card card) throws CardException;
}
