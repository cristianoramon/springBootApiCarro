package com.Carro.api;

import com.Carro.domain.Carro;
import com.Carro.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping
    public Iterable<Carro> get(){

        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> get(@PathVariable("id") Long id){
        return service.getCarrosById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarroByTipo(@PathVariable("tipo") String tipo){
        return service.getCarrosByTipo(tipo);
    }

    @PostMapping
    public void postCarro(@RequestBody Carro carro){

        service.save(carro);
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Carro carro){
        Carro c = service.update(carro,id);
        return  "Carro atualizado " + c.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){

        service.delete(id);
        return "Carro deletado";

    }
}
