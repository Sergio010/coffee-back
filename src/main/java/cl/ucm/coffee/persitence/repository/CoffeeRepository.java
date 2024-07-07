package cl.ucm.coffee.persitence.repository;

import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<CoffeeEntity,Long> {
    List<CoffeeEntity> findByName(String name);

    //Optional<CoffeeEntity> findById(Long id);
    //CoffeeEntity findByIdCoffee(int idCoffee);


    @EntityGraph(attributePaths = "testimonials")
    Optional<CoffeeEntity> findById(Long id);

}
