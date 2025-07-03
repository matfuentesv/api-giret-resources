package com.giret.apigiretresources.repository;


import com.giret.apigiretresources.model.Recurso;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResourceRepository extends JpaRepository<Recurso,Long> {

    @Query("SELECT r.estado, COUNT(r) FROM Recurso r GROUP BY r.estado")
    List<Object[]> countGroupByState();
    List<Recurso> findByEstado(String estado);
    List<Recurso> findByModeloContainingIgnoreCaseOrNumeroSerieContainingIgnoreCase(String modelo, String numeroSerie);
    @Transactional
    @Modifying
    @Query("UPDATE Recurso r SET r.estado = :estado WHERE r.idRecurso = :id")
    int updateState(@Param("id") Long id, @Param("estado") String estado);






}
