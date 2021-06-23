package org.jyoshiriro.pocs.marsexplorer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jyoshiriro.pocs.marsexplorer.model.SpaceProbe;
import org.jyoshiriro.pocs.marsexplorer.service.PlaneService;
import org.jyoshiriro.pocs.marsexplorer.service.SpaceProbeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("probes")
@Tag(
    name = "II - Space probes",
    description = "Create, move, delete or retrieve space probes"
)
public class SpaceProbeController {

    private SpaceProbeService spaceProbeService;
    private PlaneService planeService;

    @Autowired
    public SpaceProbeController(SpaceProbeService spaceProbeService, PlaneService planeService) {
        this.spaceProbeService = spaceProbeService;
        this.planeService = planeService;
    }

    @PostMapping
    @Operation(description =
            "Register a space probe in the plane. The plane must to be already registered! See <b>POST /plane</b>")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "400", description = "The plane has not been been defined yet")
    })
    public ResponseEntity<Void> postSpaceProbe(@RequestBody @Valid SpaceProbe newSpaceProbe) {
        spaceProbeService.create(newSpaceProbe);
        return status(201).build();
    }


    @PostMapping("{id}/move/{movements}")
    @Operation(description = "Move a space probe on the plane. Current space probe data comes in response")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Movement done. Current space probe data comes in response"),
        @ApiResponse(responseCode = "404", description = "No space probe found for provided id"),
        @ApiResponse(responseCode = "400",
                        description = "The plane has not been been defined yet OR One of the movements is not valid")
    })
    public ResponseEntity<SpaceProbe> moveSpaceProbe(
            @Parameter(description = "Space probe identifier") @PathVariable int id,

            @Parameter(
                description = "One or more Movements to perform. Allowed values per movement: M (move ahead), L (turn left), R (turn rigth)",
                example = "M or L or R or MRL or MMMM or MMLLRLRM"
            )
            @PathVariable String movements) {
        spaceProbeService.move(id, movements);
        return ok().body(spaceProbeService.findById(id).get());
    }

    @GetMapping
    @Operation(description = "Retrieve all space probes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "204", description = "No space probe found")
    })
    public ResponseEntity<List<SpaceProbe>> getSpaceProbes() {
        List<SpaceProbe> probes = spaceProbeService.findAll();
        return probes.isEmpty() ? noContent().build() : ok(probes);
    }

    @GetMapping("{id}")
    @Operation(description = "Retrieve a plane by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No space probe found for provided id")
    })
    public ResponseEntity<SpaceProbe> getSpaceProbe(@PathVariable int id) {
        return of(spaceProbeService.findById(id));
    }

    @DeleteMapping("{id}")
    @Operation(description = "Delete a plane by identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "No space probe found for provided id")
    })
    public ResponseEntity<Void> deleteSpaceProbe(@PathVariable int id) {
        spaceProbeService.deleteById(id);
        return ok().build();
    }
}
