package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.CertificateAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CertificateAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificateAuthorityRepository extends JpaRepository<CertificateAuthority, Long> {

}
