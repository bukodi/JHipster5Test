package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.CardType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CardType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CardTypeRepository extends JpaRepository<CardType, Long> {

}
