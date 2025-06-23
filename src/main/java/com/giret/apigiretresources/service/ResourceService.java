package com.giret.apigiretresources.service;

import com.giret.apigiretresources.model.Resource;
import java.util.List;


public interface ResourceService {

    List<Resource> findAllResource();
    Resource findResourceById(Long id);
    boolean saveResource(Resource body);
    boolean updateResource(Long id,Resource body);
    boolean deleteResource(Long id);
}
