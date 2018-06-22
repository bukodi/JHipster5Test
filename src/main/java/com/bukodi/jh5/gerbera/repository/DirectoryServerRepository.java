package com.bukodi.jh5.gerbera.repository;

import com.bukodi.jh5.gerbera.domain.DirectoryServer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DirectoryServer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectoryServerRepository extends JpaRepository<DirectoryServer, Long> {

}
