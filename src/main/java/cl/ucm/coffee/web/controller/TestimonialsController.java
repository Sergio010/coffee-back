package cl.ucm.coffee.web.controller;

import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.persitence.repository.CoffeeRepository;
import cl.ucm.coffee.persitence.repository.TestimonialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")

public class TestimonialsController {

    @Autowired
    private TestimonialsRepository testimonialsRepository;
    private CoffeeRepository coffeeRepository;
    @GetMapping("/search")
    public ResponseEntity<List<TestimonialsEntity>> searchTestimonialById(@RequestParam("id_testimonial") int idTestimonials) {
        List<TestimonialsEntity> testimonialsEntities = testimonialsRepository.findByIdTestimonials(idTestimonials);

        if (testimonialsEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(testimonialsEntities);
    }


}
