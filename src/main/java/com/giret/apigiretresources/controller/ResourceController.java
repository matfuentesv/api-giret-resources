package com.giret.apigiretresources.controller;

import com.giret.apigiretresources.model.Resource;
import com.giret.apigiretresources.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    ResourceService resourceService;



    @GetMapping("/findAllResource")
    public List<Resource> findAllResource() {
        return resourceService.findAllResource();
    }
}
