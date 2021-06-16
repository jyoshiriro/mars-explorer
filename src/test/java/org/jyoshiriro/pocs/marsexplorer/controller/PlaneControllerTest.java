package org.jyoshiriro.pocs.marsexplorer.controller;

import org.junit.jupiter.api.Test;
import org.jyoshiriro.pocs.marsexplorer.model.Plane;
import org.jyoshiriro.pocs.marsexplorer.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class PlaneControllerTest {

    @Autowired
    PlaneController controller;

    @MockBean
    PlaneService service;

    @Test
    void postPlaneShouldReturn201AndNoBody() {
        ResponseEntity<Void> response = controller.postPlane(any(Plane.class));

        assertEquals(201, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void putPlaneShouldReturn200AndNoBody() {
        ResponseEntity<Void> response = controller.putPlane(any(Plane.class));

        assertEquals(200, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    void postPlaneParameterShouldBeAnnotedWithValid() throws NoSuchMethodException {
        Method putPlane = controller.getClass().getDeclaredMethod("postPlane", Plane.class);

        assertTrue(putPlane.getParameters()[0].isAnnotationPresent(Valid.class));
    }


    @Test
    void putPlaneParameterShouldBeAnnotedWithValid() throws NoSuchMethodException {
        Method putPlane = controller.getClass().getDeclaredMethod("putPlane", Plane.class);

        assertTrue(putPlane.getParameters()[0].isAnnotationPresent(Valid.class));
    }

    @Test
    void getPlaneShouldReturn200AndWithPlaneAsBodyIfThereIsPlane() {
        Plane fakePlane = new Plane(1,1);
        when(service.getPlane()).thenReturn(Optional.of(fakePlane));

        ResponseEntity<Plane> response = controller.getPlane();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(fakePlane, response.getBody());
    }

    @Test
    void getPlaneShouldReturn404AndNoBodyIfThereIsNoPlane() {
        when(service.getPlane()).thenReturn(Optional.empty());

        ResponseEntity<Plane> response = controller.getPlane();

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

}