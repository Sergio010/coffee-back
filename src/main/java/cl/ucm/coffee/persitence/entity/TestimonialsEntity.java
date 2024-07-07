

package cl.ucm.coffee.persitence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@NoArgsConstructor
public class TestimonialsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testimonial", nullable = false)
    private int idTestimonials;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 100)
    private String testimonial;

    @Column(name = "id_coffee", nullable = false)
    private int idCoffee;

    @ManyToOne
    @JoinColumn(name = "id_coffee", referencedColumnName = "id_coffee", insertable = false, updatable = false)
    private CoffeeEntity coffee;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username",  insertable = false, updatable = false)
    private UserEntity user;

    // Getters and Setters

    public int getIdTestimonial() {
        return idTestimonials;
    }

    public void setIdTestimonial(int idTestimonials) {
        this.idTestimonials = idTestimonials;
    }

    public String getUsernameTestimonial() {
        return username;
    }

    public void setUsernameTestimonial(String username) {
        this.username = username;
    }

    public String getTestimonial() {
        return testimonial;
    }

    public void setTestimonial(String testimonial) {
        this.testimonial = testimonial;
    }

    public int getIdCoffee() {
        return idCoffee;
    }

    public void setIdCoffee(int idCoffee) {
        this.idCoffee = idCoffee;
    }

    public CoffeeEntity getCoffee() {
        return coffee;
    }

    public void setCoffee(CoffeeEntity coffee) {
        this.coffee = coffee;
    }
}

