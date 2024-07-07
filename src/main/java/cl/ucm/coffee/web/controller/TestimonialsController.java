/*


package cl.ucm.coffee.web.controller;

import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.persitence.repository.CoffeeRepository;
import cl.ucm.coffee.persitence.repository.TestimonialsRepository;
import cl.ucm.coffee.web.service.TestimonialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/testimonials")

public class TestimonialsController {

    private final TestimonialsRepository testimonialsRepository;
    private final CoffeeRepository coffeeRepository;
    private final TestimonialsService testimonialsService;

    @Autowired
    public TestimonialsController(TestimonialsRepository testimonialsRepository, CoffeeRepository coffeeRepository, TestimonialsService testimonialsService) {
        this.testimonialsRepository = testimonialsRepository;
        this.coffeeRepository = coffeeRepository;
        this.testimonialsService = testimonialsService;
    }

    @PostMapping("/createTestimonial")
    public ResponseEntity<?> createTestimonial(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "testimonial") String testimonial,
            @RequestParam(name = "idCoffee") int idCoffee) {

        try {
            // Crear una nueva instancia de TestimonialsEntity
            TestimonialsEntity newTestimonial = new TestimonialsEntity();
            newTestimonial.setUsernameTestimonial(username);
            newTestimonial.setTestimonial(testimonial);
            newTestimonial.setIdCoffee(idCoffee);

            // Guardar el testimonio en la base de datos
            TestimonialsEntity createdTestimonial = testimonialsRepository.save(newTestimonial);

            return ResponseEntity.status(HttpStatus.CREATED).body(createdTestimonial);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de acceso a datos al guardar el testimonio: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado al guardar el testimonio: " + e.getMessage());
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TestimonialsEntity> findById(@PathVariable("id") int idTestimonials) {
        Optional<TestimonialsEntity> testimonialOptional = testimonialsService.findById(idTestimonials);
        return testimonialOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/search")
    public ResponseEntity<List<TestimonialsEntity>> searchTestimonialById(@RequestParam("id_testimonial") int idTestimonials) {
        List<TestimonialsEntity> testimonialsEntities = testimonialsRepository.findByIdTestimonials(idTestimonials);

        if (testimonialsEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(testimonialsEntities);
    }

}

*/