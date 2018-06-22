package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.LogicalCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the LogicalCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogicalCardRepository extends JpaRepository<LogicalCard, Long> {

    @Query(value = "select distinct app_logical_card from LogicalCard app_logical_card left join fetch app_logical_card.certificates",
        countQuery = "select count(distinct app_logical_card) from LogicalCard app_logical_card")
    Page<LogicalCard> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct app_logical_card from LogicalCard app_logical_card left join fetch app_logical_card.certificates")
    List<LogicalCard> findAllWithEagerRelationships();

    @Query("select app_logical_card from LogicalCard app_logical_card left join fetch app_logical_card.certificates where app_logical_card.id =:id")
    Optional<LogicalCard> findOneWithEagerRelationships(@Param("id") Long id);

}
