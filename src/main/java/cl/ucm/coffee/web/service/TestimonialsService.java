package cl.ucm.coffee.web.service;

import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import cl.ucm.coffee.persitence.repository.TestimonialsRepository;
import cl.ucm.coffee.service.dto.TestimonialDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialsService {
    @Autowired
    private TestimonialsRepository testimonialsRepository;

    public TestimonialsEntity createTestimonial(TestimonialsEntity testimonial) {
        return testimonialsRepository.save(testimonial);
    }

    public List<TestimonialDto> findByCoffeeId(int idCoffee) {
        return null;
    }

}
