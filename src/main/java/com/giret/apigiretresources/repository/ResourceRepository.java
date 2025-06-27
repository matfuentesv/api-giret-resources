package com.giret.apigiretresources.repository;


import com.giret.apigiretresources.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Recurso,Long> {

    @Query("SELECT r.estado, COUNT(r) FROM Recurso r GROUP BY r.estado")
    List<Object[]> countGroupByState();
    List<Recurso> findByEstado(String estado);
    List<Recurso> findByModeloContainingIgnoreCaseOrNumeroSerieContainingIgnoreCase(String modelo, String numeroSerie);




}
