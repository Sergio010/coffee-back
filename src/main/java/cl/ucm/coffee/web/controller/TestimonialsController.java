package cl.ucm.coffee.web.controller;

import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.web.service.TestimonialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/testimonials")

public class TestimonialsController {

    @Autowired
    private TestimonialsService testimonialsService;

    @PostMapping("/create")
    public ResponseEntity<TestimonialsEntity> createTestimonial(@RequestBody TestimonialsEntity testimonial) {
        TestimonialsEntity createdTestimonial = testimonialsService.createTestimonial(testimonial);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTestimonial);
    }

}
