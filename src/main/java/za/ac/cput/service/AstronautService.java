package za.ac.cput.service;

import za.ac.cput.domain.Astronaut;
import za.ac.cput.repository.AstronautRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Set;

import java.util.HashSet;


@Service
public class AstronautService implements IAstronautService {

    private final AstronautRepository repository;

    @Autowired
    public AstronautService(AstronautRepository repository) {
        this.repository = repository;
    }

    @Override
    public Astronaut create(Astronaut astronaut) {
        return repository.save(astronaut);
    }

    @Override
    public Astronaut read(String astronautNumber) {
        return repository.findById(astronautNumber)
                .orElseThrow(() -> new NoSuchElementException("Astronaut with number " + astronautNumber + " does not exist"));
    }

    @Override
    public Astronaut update(Astronaut astronaut) {
        return repository.save(astronaut);
    }

    @Override
    public Astronaut delete(String astronautNumber) {
        Astronaut astronautToDelete = read(astronautNumber);
        repository.delete(astronautToDelete);
        return astronautToDelete;
    }

    @Override
    public Set<Astronaut> getAll() {
        return new HashSet<>(repository.findAll());
    }
}
