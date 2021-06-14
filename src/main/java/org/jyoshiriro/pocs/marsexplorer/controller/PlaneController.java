package org.jyoshiriro.pocs.marsexplorer.controller;

import org.jyoshiriro.pocs.marsexplorer.model.Plane;
import org.jyoshiriro.pocs.marsexplorer.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("planes")
public class PlaneController {

    @Autowired
    private PlaneService service;

    @PostMapping
    public ResponseEntity<Void> postPlane(@RequestBody @Valid Plane newPlane) {
        service.create(newPlane);
        return status(201).build();
    }

    @GetMapping
    public ResponseEntity<Plane> getPlane() {
        return of(service.getPlane());
    }

    @PutMapping
    public ResponseEntity<Void> putPlane(@RequestBody @Valid Plane updatedPlane) {
        service.update(updatedPlane);
        return ok().build();
    }
}
