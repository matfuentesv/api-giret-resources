package com.giret.apigiretresources.service;

import com.giret.apigiretresources.model.Resource;
import com.giret.apigiretresources.model.States;
import com.giret.apigiretresources.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService{

    @Autowired
    ResourceRepository resourceRepository;



    @Override
    public List<Resource> findAllResource() {
        return resourceRepository.findAllResource();
    }

    @Override
    public Resource findResourceById(Long id) {
        return resourceRepository.findResourceById(id);
    }

    @Override
    public boolean saveResource(Resource body) {
        return resourceRepository.saveResource(body);
    }

    @Override
    public boolean updateResource(Long id,Resource body) {
        return resourceRepository.updateResource(id,body);
    }

    @Override
    public boolean deleteResource(Long id) {
        return resourceRepository.deleteResource(id);
    }

    @Override
    public List<States> findAllStates() {
        return resourceRepository.findAllStates();
    }

}
