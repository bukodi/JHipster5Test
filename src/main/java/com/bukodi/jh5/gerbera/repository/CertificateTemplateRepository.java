package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.CertificateTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CertificateTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CertificateTemplateRepository extends JpaRepository<CertificateTemplate, Long> {

}
