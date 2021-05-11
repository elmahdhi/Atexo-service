package com.atexo.carte.dao;

import com.atexo.carte.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface Repository pour l'interaction avec la base de donnée (DATA)
 */
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
