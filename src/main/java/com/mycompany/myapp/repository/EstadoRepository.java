package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Estado entity.
 */
public interface EstadoRepository extends JpaRepository<Estado,Long>{

}
