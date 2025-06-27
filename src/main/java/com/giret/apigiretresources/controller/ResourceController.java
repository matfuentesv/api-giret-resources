package com.giret.apigiretresources.controller;

import com.giret.apigiretresources.model.Recurso;
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
    public ResponseEntity<List<Recurso>> findAllResource() {
        return ResponseEntity.ok(resourceService.findAllResource());
    }

    @GetMapping("/findResourceById/{id}")
    public ResponseEntity<Recurso> findResourceById(@PathVariable("id")Long id) {
        return ResponseEntity.ok(resourceService.findResourceById(id));
    }

    @PostMapping("/saveResource")
    public ResponseEntity<Recurso> saveResource(@RequestBody Recurso recurso) {
        return ResponseEntity.ok(resourceService.saveResource(recurso));
    }

    @PutMapping("/updateResource/{id}")
    public ResponseEntity<Recurso> updateResource(@PathVariable ("id")Long id, @RequestBody Recurso recurso) {
        return ResponseEntity.ok(resourceService.updateResource(id, recurso));
    }

    @DeleteMapping("/deleteResource/{id}")
    public ResponseEntity<Boolean> deleteResource(@PathVariable ("id")Long id) {
        return ResponseEntity.ok(resourceService.deleteResource(id));
    }

    @GetMapping("/resource/count")
    public ResponseEntity<Long> countResources() {
        return ResponseEntity.ok(resourceService.countResources());
    }

    @GetMapping("/resource/countByEstado")
    public ResponseEntity<List<Object[]>> countByEstado() {
        return ResponseEntity.ok(resourceService.countByState());
    }

    @GetMapping("/resource/findByEstado/{estado}")
    public ResponseEntity<List<Recurso>> findByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(resourceService.findByState(estado));
    }

    @GetMapping("/resource/search")
    public ResponseEntity<List<Recurso>> searchResource(@RequestParam String term) {
        return ResponseEntity.ok(resourceService.searchResource(term));
    }

}
