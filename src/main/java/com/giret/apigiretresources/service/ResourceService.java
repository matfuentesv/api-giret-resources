package com.giret.apigiretresources.service;

import com.giret.apigiretresources.model.Recurso;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ResourceService {

    List<Recurso> findAllResource();
    Recurso findResourceById(Long id);
    Recurso saveResource(Recurso body);
    Recurso updateResource(Long id, Recurso body);
    Boolean deleteResource(Long id);
    long countResources();
    List<Object[]> countByState();
    List<Recurso> findByState(String state);
    List<Recurso> searchResource(String term);
    Recurso updateState(Long id,String estado);
}
