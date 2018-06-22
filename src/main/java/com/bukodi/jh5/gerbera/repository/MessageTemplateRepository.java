package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.MessageTemplate;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MessageTemplate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {

}
