package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.IdentityProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IdentityProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdentityProfileRepository extends JpaRepository<IdentityProfile, Long> {

}
