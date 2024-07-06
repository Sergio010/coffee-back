package cl.ucm.coffee.persitence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "coffee")
@Getter
@Setter
@NoArgsConstructor


public class CoffeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coffee", nullable = false)
    private int idCoffee;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 30)
    private String description;

    @Column(nullable = false)
    private int price;

    @Lob
    private  byte[] image64;

    @OneToMany(mappedBy = "coffee", fetch = FetchType.EAGER)
    private List<TestimonialsEntity> testimonials;

    public long getId(){
        return idCoffee;
    }

    public void setIdCoffee(int idCoffee) {
        this.idCoffee = idCoffee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getImage64() {
        return image64;
    }

    public void setImage64(byte[] image64) {
        this.image64 = image64;
    }
}
