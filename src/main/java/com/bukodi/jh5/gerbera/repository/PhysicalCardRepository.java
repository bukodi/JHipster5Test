package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.PhysicalCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PhysicalCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhysicalCardRepository extends JpaRepository<PhysicalCard, Long> {

}
