package org.jyoshiriro.pocs.marsexplorer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jyoshiriro.pocs.marsexplorer.model.Plane;
import org.jyoshiriro.pocs.marsexplorer.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("plane")
@Tag(
    name = "I - Plane",
    description = "Create, update or retrieve the plane"
)
public class PlaneController {

    @Autowired
    private PlaneService service;

    @PostMapping
    @Operation(description = "Register the plane")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "The plane has been already been defined")
    })
    public ResponseEntity<Void> postPlane(@RequestBody @Valid Plane newPlane) {
        service.create(newPlane);
        return status(201).build();
    }

    @PutMapping
    @Operation(description = "Update the plane")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movement done"),
            @ApiResponse(responseCode = "400", description = "The plane has not been been defined yet")
    })
    public ResponseEntity<Void> putPlane(@RequestBody @Valid Plane updatedPlane) {
        service.update(updatedPlane);
        return ok().build();
    }

    @GetMapping
    @Operation(description = "Retrieve the plane")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "The plane has not been been defined yet")
    })
    public ResponseEntity<Plane> getPlane() {
        return of(service.getPlane());
    }
}
