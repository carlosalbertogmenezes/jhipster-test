package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Pais entity.
 */
public interface PaisRepository extends JpaRepository<Pais,Long>{

}
