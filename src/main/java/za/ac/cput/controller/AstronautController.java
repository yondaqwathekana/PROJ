package za.ac.cput.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Astronaut;
import za.ac.cput.service.AstronautService;

import java.util.Set;

@RestController
@RequestMapping("/astronauts")
public class AstronautController {

    private final AstronautService astronautService;

    @Autowired
    public AstronautController(AstronautService astronautService) {
        this.astronautService = astronautService;
    }

    @PostMapping("/create")
    public ResponseEntity<Astronaut> create(@RequestBody Astronaut astronaut) {
        Astronaut createdAstronaut = astronautService.create(astronaut);
        return new ResponseEntity<>(createdAstronaut, HttpStatus.CREATED);
    }

    @GetMapping("/read/{astronautNumber}")
    public ResponseEntity<Astronaut> read(@PathVariable String astronautNumber) {
        Astronaut astronaut = astronautService.read(astronautNumber);
        return new ResponseEntity<>(astronaut, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Astronaut> update(@RequestBody Astronaut astronaut) {
        Astronaut updatedAstronaut = astronautService.update(astronaut);
        return new ResponseEntity<>(updatedAstronaut, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{astronautNumber}")
    public ResponseEntity<Void> delete(@PathVariable String astronautNumber) {
        astronautService.delete(astronautNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Set<Astronaut>> getAll() {
        Set<Astronaut> astronauts = astronautService.getAll();
        return new ResponseEntity<>(astronauts, HttpStatus.OK);
    }
}
