package org.jyoshiriro.pocs.marsexplorer.controller;

import org.jyoshiriro.pocs.marsexplorer.model.Movement;
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
public class SpaceProbeController {

    private SpaceProbeService spaceProbeService;
    private PlaneService planeService;

    @Autowired
    public SpaceProbeController(SpaceProbeService spaceProbeService, PlaneService planeService) {
        this.spaceProbeService = spaceProbeService;
        this.planeService = planeService;
    }

    @PostMapping
    public ResponseEntity<Void> postSpaceProbe(@RequestBody @Valid SpaceProbe newSpaceProbe) {
        spaceProbeService.create(newSpaceProbe);
        return status(201).build();
    }

    @PostMapping("{id}/single-movement/{movement}")
    public ResponseEntity<Void> moveSpaceProbe(@PathVariable int id, @PathVariable Movement movement) {
        spaceProbeService.move(id, movement);
        return ok().build();
    }

    @PostMapping("{id}/multiple-movements/{movements}")
    public ResponseEntity<Void> moveSpaceProbe(@PathVariable int id, @PathVariable String movements) {
        spaceProbeService.move(id, movements);
        return ok().build();
    }

    @GetMapping
    public ResponseEntity<List<SpaceProbe>> getSpaceProbes() {
        List<SpaceProbe> probes = spaceProbeService.findAll();
        return probes.isEmpty() ? noContent().build() : ok(probes);
    }

    @GetMapping("{id}")
    public ResponseEntity<SpaceProbe> getSpaceProbe(@PathVariable int id) {
        return of(spaceProbeService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSpaceProbe(@PathVariable int id) {
        spaceProbeService.deleteById(id);
        return ok().build();
    }
}
