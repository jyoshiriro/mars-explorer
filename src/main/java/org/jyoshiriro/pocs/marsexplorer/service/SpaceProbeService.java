package org.jyoshiriro.pocs.marsexplorer.service;

import org.jyoshiriro.pocs.marsexplorer.exception.InvalidMovementException;
import org.jyoshiriro.pocs.marsexplorer.exception.MarsExplorerEntityNotFoundException;
import org.jyoshiriro.pocs.marsexplorer.exception.PlaneNotDefinedException;
import org.jyoshiriro.pocs.marsexplorer.model.Movement;
import org.jyoshiriro.pocs.marsexplorer.model.SpaceProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpaceProbeService {

    private static final String PROBE_NOT_FOUND_ERROR = "Spaceprobe not found";

    @Autowired
    private PlaneService planeService;

    private List<SpaceProbe> spaceProbes = new ArrayList<>();

    public void create(SpaceProbe newSpaceProbe) {
        checkPlaneIsDefined();

        newSpaceProbe.setPlane(planeService.getPlane().get());
        spaceProbes.add(newSpaceProbe);
    }

    public void move(int id, String movements) {
        checkPlaneIsDefined();

        List<Movement> validMovements = new ArrayList<>();

        String[] letters = movements.split("");
        for (String letter : letters) {
            try {
                validMovements.add(Movement.valueOf(letter));
            } catch (IllegalArgumentException e) {
                throw new InvalidMovementException(letter);
            }
        }

        validMovements.forEach(movement -> move(id, movement));
    }

    public List<SpaceProbe> findAll() {
        return spaceProbes;
    }

    public Optional<SpaceProbe> findById(int id) {
        return isValidId(id) ? Optional.of(spaceProbes.get(id-1)) : Optional.empty();
    }

    public void deleteById(int id) {
        if (!isValidId(id)) {
            throw new MarsExplorerEntityNotFoundException(PROBE_NOT_FOUND_ERROR);
        }

        spaceProbes.remove(id-1);
    }

    private void checkPlaneIsDefined() {
        if (!planeService.hasPlane()) {
            throw new PlaneNotDefinedException();
        }
    }

    private boolean isValidId(int id) {
        return id-1 < spaceProbes.size();
    }

    private void move(int id, Movement movement) {
        Optional<SpaceProbe> spaceProbeOptional = findById(id);

        if (spaceProbeOptional.isEmpty()) {
            throw new MarsExplorerEntityNotFoundException(PROBE_NOT_FOUND_ERROR);
        }

        SpaceProbe spaceProbe = spaceProbeOptional.get();
        spaceProbe.move(movement);
        spaceProbes.set(id-1, spaceProbe);
    }


}
