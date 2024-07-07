package cl.ucm.coffee.web.service;

import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import cl.ucm.coffee.persitence.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<CoffeeEntity> findByName(String name) {
        return coffeeRepository.findByName(name);
    }

    public List<CoffeeEntity> searchCoffeesByName(String name) {
        return coffeeRepository.findByName(name);
    }

    public List<CoffeeEntity> listCoffees() {
        return coffeeRepository.findAll();
    }

    public Optional<CoffeeEntity> findById(Long id) {
        return coffeeRepository.findById(id);
    }
}
