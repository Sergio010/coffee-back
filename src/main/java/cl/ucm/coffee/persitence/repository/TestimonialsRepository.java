package cl.ucm.coffee.persitence.repository;

import cl.ucm.coffee.persitence.entity.TestimonialsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestimonialsRepository extends JpaRepository<TestimonialsEntity, Long> {
    List<TestimonialsEntity> findByIdTestimonials(int idTestimonials);
}
