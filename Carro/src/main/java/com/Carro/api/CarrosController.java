package com.Carro.api;

import com.Carro.domain.Carro;
import com.Carro.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<Iterable<Carro>> get(){

         return ResponseEntity.ok(service.getCarros());
        //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){
        Optional<Carro> c =  service.getCarrosById(id);
        if ( c.isPresent()){
            return ResponseEntity.ok(c.get());
        }else {
            return ResponseEntity.notFound().build();

        }

    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarroByTipo(@PathVariable("tipo") String tipo){
        List<Carro> c = service.getCarrosByTipo(tipo);
        return c.isEmpty()?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(c);

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
