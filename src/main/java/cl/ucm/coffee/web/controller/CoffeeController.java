package cl.ucm.coffee.web.controller;


import cl.ucm.coffee.persitence.entity.CoffeeEntity;
import cl.ucm.coffee.persitence.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import cl.ucm.coffee.web.service.CoffeeService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeRepository coffeeRepository;
    private CoffeeService coffeeService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, String>> coffes(){
        Map map = new HashMap();
        map.put("coffee", "Coffees :Get)");
        return ResponseEntity.ok(map);
    }
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> coffe(){
        Map map = new HashMap();
        map.put("coffee", "Coffees Post:)");
        return ResponseEntity.ok(map);
    }

    //metodo para obtener todos los cafes
    @GetMapping("/listCoffees")
    public List<CoffeeEntity> listCoffees() {
        return coffeeRepository.findAll();
    }

    //metodo para buscar un cafe a traves de su nombre, se encuentran todas las coincidencias.
    @GetMapping("/search")
    public ResponseEntity<List<CoffeeEntity>> searchCoffeesByName(@RequestParam("name") String name) {
        List<CoffeeEntity> coffees = coffeeRepository.findByName(name);
        return ResponseEntity.ok().body(coffees);
    }

    //metodo para crear un cafe
    @PostMapping("/createCoffee")
    public ResponseEntity<?> createCoffee(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price") int price,
            @RequestParam(name = "desc") String description,
            @RequestParam(name = "foto") MultipartFile foto) {

        try {
            // Convertir la imagen a un arreglo de bytes si es necesario
            byte[] imageBytes = foto.getBytes();

            // Crear una nueva instancia de Coffee
            CoffeeEntity coffee = new CoffeeEntity();
            coffee.setName(name);
            coffee.setPrice(price);
            coffee.setDescription(description);
            coffee.setImage64(imageBytes); // Guardar la imagen como un byte array

            // Guardar el café en la base de datos
            CoffeeEntity savedCoffee = coffeeRepository.save(coffee);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedCoffee);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la imagen: " + e.getMessage());
        }
    }

    @PutMapping("/updateCoffee/{id}")
    public String updateCoffee(@PathVariable Long id, @RequestBody CoffeeEntity coffeeEntity) {
            CoffeeEntity updateCoffee = coffeeRepository.findById(id).get();
            updateCoffee.setName(coffeeEntity.getName());
            updateCoffee.setPrice(coffeeEntity.getPrice());
            updateCoffee.setDescription(coffeeEntity.getDescription());
            updateCoffee.setImage64(coffeeEntity.getImage64());
            coffeeRepository.save(coffeeEntity);
            return "Café Actualizado";

    }


    //metodo para eliminar un cafe
    @DeleteMapping("/deleteCoffee/{id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable("id") Long id) {
        try {
            // Verificar si el café existe
            if (!coffeeRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            // Eliminar el café por ID
            coffeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el café: " + e.getMessage());
        }
    }

    /* Endpoint para obtener un café por su ID
    @GetMapping("/{id}")
    public CoffeeEntity getCoffeeById(@PathVariable("id") Long id) {
        Optional<CoffeeEntity> coffeeOptional;
        coffeeOptional = coffeeRepository.findById((long) id);
        return coffeeOptional.orElse(null); // Devuelve null si no se encuentra el café
    }
    */
}
