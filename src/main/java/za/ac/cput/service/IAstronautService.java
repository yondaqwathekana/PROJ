package za.ac.cput.service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Astronaut;

import java.util.Set;


import java.util.Set;

public interface IAstronautService extends IService<Astronaut, String> {

    Astronaut delete(String astronautNumber);
    Set<Astronaut> getAll();
}
