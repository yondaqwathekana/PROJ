package za.ac.cput.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Astronaut;

import java.util.List;

@Repository
public interface AstronautRepository extends JpaRepository<Astronaut, String> {
    List<Astronaut> findByAge(int age);
}
