package cl.ucm.coffee.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeWithTestimonialsDto {

    private int idCoffee;
    private String name;
    private String description;
    private int price;
    private byte[] image64;
    private List<TestimonialDto> testimonials;

    // Constructor con parámetros relevantes
    public CoffeeWithTestimonialsDto(int idCoffee, String name, String description, int price, byte[] image64, List<TestimonialDto> testimonials) {
        this.idCoffee = idCoffee;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image64 = image64;
        this.testimonials = testimonials;
    }

    // Getters y setters (si es necesario)

}