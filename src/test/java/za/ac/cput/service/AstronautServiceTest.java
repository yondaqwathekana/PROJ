package za.ac.cput.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.domain.Astronaut;
import za.ac.cput.repository.AstronautRepository;
import za.ac.cput.service.AstronautService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import za.ac.cput.domain.Astronaut;
import za.ac.cput.repository.AstronautRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AstronautServiceTest {

    @Mock
    private AstronautRepository astronautRepository;

    @InjectMocks
    private AstronautService astronautService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAstronaut() {
        Astronaut astronautToCreate = new Astronaut.Builder()
                .setAstronautNumber(1L)
                .setAge(35)
                .setGender("Male")
                .build();

        when(astronautRepository.save(astronautToCreate)).thenReturn(astronautToCreate);

        Astronaut createdAstronaut = astronautService.create(astronautToCreate);

        assertNotNull(createdAstronaut);
        assertEquals(astronautToCreate, createdAstronaut);
    }

    @Test
    void testReadAstronaut() {
        long astronautNumber = 1L;
        Astronaut expectedAstronaut = new Astronaut.Builder()
                .setAstronautNumber(astronautNumber)
                .setAge(35)
                .setGender("Male")
                .build();

        when(astronautRepository.findById(String.valueOf(astronautNumber))).thenReturn(Optional.of(expectedAstronaut));

        Astronaut retrievedAstronaut = astronautService.read(String.valueOf(astronautNumber));

        assertNotNull(retrievedAstronaut);
        assertEquals(expectedAstronaut, retrievedAstronaut);
    }

    @Test
    void testUpdateAstronaut() {
        Astronaut astronautToUpdate = new Astronaut.Builder()
                .setAstronautNumber(1L)
                .setAge(35)
                .setGender("Male")
                .build();

        when(astronautRepository.save(astronautToUpdate)).thenReturn(astronautToUpdate);

        Astronaut updatedAstronaut = astronautService.update(astronautToUpdate);

        assertNotNull(updatedAstronaut);
        assertEquals(astronautToUpdate, updatedAstronaut);
    }

    @Test
    void testDeleteAstronaut() {
        long astronautNumber = 1L;
        Astronaut astronautToDelete = new Astronaut.Builder()
                .setAstronautNumber(astronautNumber)
                .setAge(35)
                .setGender("Male")
                .build();

        when(astronautRepository.findById(String.valueOf(astronautNumber))).thenReturn(Optional.of(astronautToDelete));

        Astronaut deletedAstronaut = astronautService.delete(String.valueOf(astronautNumber));

        assertNotNull(deletedAstronaut);
        assertEquals(astronautToDelete, deletedAstronaut);

        verify(astronautRepository, times(1)).delete(astronautToDelete);
    }

    @Test
    void testGetAllAstronauts() {
        Astronaut astronaut1 = new Astronaut.Builder()
                .setAstronautNumber(1L)
                .setAge(35)
                .setGender("Male")
                .build();
        Astronaut astronaut2 = new Astronaut.Builder()
                .setAstronautNumber(2L)
                .setAge(40)
                .setGender("Female")
                .build();

        List<Astronaut> allAstronauts = Arrays.asList(astronaut1, astronaut2);

        when(astronautRepository.findAll()).thenReturn(allAstronauts);

        Set<Astronaut> retrievedAstronauts = astronautService.getAll();

        assertNotNull(retrievedAstronauts);
        assertEquals(2, retrievedAstronauts.size());
        assertTrue(retrievedAstronauts.contains(astronaut1));
        assertTrue(retrievedAstronauts.contains(astronaut2));
    }
}
