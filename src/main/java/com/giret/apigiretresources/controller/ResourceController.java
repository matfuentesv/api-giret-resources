package com.giret.apigiretresources.controller;

import com.giret.apigiretresources.model.Resource;
import com.giret.apigiretresources.model.States;
import com.giret.apigiretresources.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    ResourceService resourceService;


    @GetMapping("/findAllResource")
    public ResponseEntity<List<Resource>> findAllResource() {
        return ResponseEntity.ok(resourceService.findAllResource());
    }

    @GetMapping("/findResourceById/{id}")
    public ResponseEntity<Resource> findResourceById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(resourceService.findResourceById(id));
    }

    @PostMapping("/saveResource")
    public ResponseEntity<Boolean> saveResource(@RequestBody Resource resource) {
        return ResponseEntity.ok(resourceService.saveResource(resource));
    }

    @PutMapping("/updateResource/{id}")
    public ResponseEntity<Boolean> updateResource(@PathVariable ("id")Long id, @RequestBody Resource resource) {
        return ResponseEntity.ok(resourceService.updateResource(id,resource));
    }

    @DeleteMapping("/deleteResource/{id}")
    public ResponseEntity<Boolean> deleteResource(@PathVariable ("id")Long id) {
        return ResponseEntity.ok(resourceService.deleteResource(id));
    }

    @GetMapping("/findAllState")
    public ResponseEntity<List<States>> findAllState() {
        return ResponseEntity.ok(resourceService.findAllStates());
    }
}
