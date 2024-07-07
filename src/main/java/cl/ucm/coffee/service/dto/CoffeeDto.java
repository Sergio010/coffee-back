package cl.ucm.coffee.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoffeeDto {

    private int idCoffee;
    private String name;
    private String description;
    private int price;
    private byte[] image64;

    // Getters y Setters
}