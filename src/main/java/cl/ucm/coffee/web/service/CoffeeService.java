package cl.ucm.coffee.web.service;

import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.persitence.repository.CoffeeRepository;
import cl.ucm.coffee.persitence.repository.TestimonialsRepository;
import cl.ucm.coffee.service.dto.CoffeeDto;
import cl.ucm.coffee.service.dto.CoffeeWithTestimonialsDto;
import cl.ucm.coffee.service.dto.TestimonialDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;
    private TestimonialsService testimonialService;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository, TestimonialsService testimonialService) {
        this.coffeeRepository = coffeeRepository;
        this.testimonialService = testimonialService;
    }

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

    @Autowired
    private TestimonialsRepository testimonialsRepository;

    public List<CoffeeWithTestimonialsDto> listCoffeesWithTestimonials() {
        List<CoffeeEntity> coffees = coffeeRepository.findAll();
        return coffees.stream()
                .map(this::mapToCoffeeWithTestimonialsDto)
                .collect(Collectors.toList());
    }

    private CoffeeWithTestimonialsDto mapToCoffeeWithTestimonialsDto(CoffeeEntity coffeeEntity) {
        CoffeeWithTestimonialsDto dto = new CoffeeWithTestimonialsDto();
        dto.setIdCoffee(coffeeEntity.getIdCoffee());
        dto.setName(coffeeEntity.getName());
        dto.setDescription(coffeeEntity.getDescription());
        dto.setPrice(coffeeEntity.getPrice());
        dto.setImage64(coffeeEntity.getImage64());

        List<TestimonialDto> testimonials = coffeeEntity.getTestimonials().stream()
                .map(this::mapToTestimonialDto)
                .collect(Collectors.toList());
        dto.setTestimonials(testimonials);

        return dto;
    }

    private TestimonialDto mapToTestimonialDto(TestimonialsEntity testimonialEntity) {
        TestimonialDto dto = new TestimonialDto();
        dto.setUsername(testimonialEntity.getUsername());
        dto.setTestimonial(testimonialEntity.getTestimonial());
        return dto;
    }

    public CoffeeEntity getCoffeeWithTestimonialsByName(String name) {
        List<CoffeeEntity> coffeeEntity = coffeeRepository.findByName(name);
        if (coffeeEntity == null) {
            throw new EntityNotFoundException("Café no encontrado con nombre: " + name);
        }

        // Aquí podrías cargar los testimonios si los necesitas
        // List<TestimonialDto> testimonials = testimonialService.findByCoffeeId(coffeeEntity.getIdCoffee());
        // coffeeEntity.setTestimonials(testimonials);

        return (CoffeeEntity) coffeeEntity;
    }

    public CoffeeDto getCoffeeByName(String name) {
        List<CoffeeEntity> coffeeEntities = coffeeRepository.findByName(name);

        if (!coffeeEntities.isEmpty()) {
            CoffeeEntity coffeeEntity = coffeeEntities.get(0); // Tomar el primer café encontrado
            return mapToCoffeeDto(coffeeEntity);
        } else {
            throw new EntityNotFoundException("Café no encontrado con nombre: " + name);
        }
    }

    private CoffeeDto mapToCoffeeDto(CoffeeEntity coffeeEntity) {
        CoffeeDto coffeeDto = new CoffeeDto();
        coffeeDto.setIdCoffee(coffeeEntity.getIdCoffee());
        coffeeDto.setName(coffeeEntity.getName());
        coffeeDto.setDescription(coffeeEntity.getDescription());
        coffeeDto.setPrice(coffeeEntity.getPrice());
        coffeeDto.setImage64(coffeeEntity.getImage64());
        // No se establecen los testimonios aquí para mantenerlos vacíos

        return coffeeDto;
    }

    public CoffeeWithTestimonialsDto getCoffeeWithTestimonialsById(Long id) {
        Optional<CoffeeEntity> coffeeEntityOptional = coffeeRepository.findById(id);
        if (coffeeEntityOptional.isPresent()) {
            CoffeeEntity coffeeEntity = coffeeEntityOptional.get();
            CoffeeWithTestimonialsDto dto = mapToCoffeeWithTestimonialsDto(coffeeEntity);
            return dto;
        } else {
            throw new EntityNotFoundException("Café no encontrado con ID: " + id);
        }
    }



}
