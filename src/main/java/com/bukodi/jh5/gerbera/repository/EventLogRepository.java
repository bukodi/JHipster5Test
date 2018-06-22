package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.EventLog;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EventLog entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventLogRepository extends JpaRepository<EventLog, Long> {

}
