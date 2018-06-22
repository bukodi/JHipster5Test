package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.PhysicalCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the PhysicalCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhysicalCardRepository extends JpaRepository<PhysicalCard, Long> {

    @Query("select app_physical_card from PhysicalCard app_physical_card where app_physical_card.owner.login = ?#{principal.username}")
    List<PhysicalCard> findByOwnerIsCurrentUser();

}
