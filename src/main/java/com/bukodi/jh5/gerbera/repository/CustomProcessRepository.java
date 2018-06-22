package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.CustomProcess;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CustomProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomProcessRepository extends JpaRepository<CustomProcess, Long> {

}
