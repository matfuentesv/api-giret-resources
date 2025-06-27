package com.giret.apigiretresources.service;

import com.giret.apigiretresources.model.Recurso;
import com.giret.apigiretresources.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    ResourceRepository resourceRepository;



    @Override
    public List<Recurso> findAllResource() {
        return resourceRepository.findAll();

    }

    @Override
    public Recurso findResourceById(Long id) {
        if(resourceRepository.findById(id).isPresent()){
            return resourceRepository.findById(id).get();
        }else {
            return new Recurso();
        }

    }

    @Override
    public Recurso saveResource(Recurso body) {
        return resourceRepository.save(body);
    }

    @Override
    public Recurso updateResource(Long id, Recurso body) {
        body.setIdRecurso(id);
        return resourceRepository.save(body);
    }

    @Override
    public Boolean deleteResource(Long id) {
         resourceRepository.deleteById(id);
         return true;
    }

    @Override
    public long countResources() {
        return resourceRepository.count();
    }

    @Override
    public List<Object[]> countByState() {
        return resourceRepository.countGroupByState();
    }

    @Override
    public List<Recurso> findByState(String state) {
        return resourceRepository.findByEstado(state);
    }
    @Override
    public List<Recurso> searchResource(String term) {
        return resourceRepository.findByModeloContainingIgnoreCaseOrNumeroSerieContainingIgnoreCase(term, term);
    }


}
