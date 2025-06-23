package com.giret.apigiretresources.service;

import com.giret.apigiretresources.model.Resource;
import com.giret.apigiretresources.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    ResourceRepository recursoRepository;



    @Override
    public List<Resource> findAllResource() {
        return recursoRepository.findAllResource();
    }

    @Override
    public Resource findResourceById(Long id) {
        return recursoRepository.findResourceById(id);
    }

    @Override
    public boolean saveResource(Resource body) {
        return recursoRepository.saveResource(body);
    }

    @Override
    public boolean updateResource(Long id,Resource body) {
        return recursoRepository.updateResource(id,body);
    }

    @Override
    public boolean deleteResource(Long id) {
        return recursoRepository.deleteResource(id);
    }

}
