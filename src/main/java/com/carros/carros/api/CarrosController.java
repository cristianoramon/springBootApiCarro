package com.carros.carros.api;

import com.carros.carros.domain.Carro;
import com.carros.carros.domain.CarroService;
import com.carros.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping
    public ResponseEntity<List<CarroDTO>> get(){

         return ResponseEntity.ok(service.getCarros());
        //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> get(@PathVariable("id") Long id){
        Optional<CarroDTO> carro =  service.getCarrosById(id);

        return carro
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> getCarroByTipo(@PathVariable("tipo") String tipo){
        List<CarroDTO> c = service.getCarrosByTipo(tipo);
        return c.isEmpty()?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(c);

    }

    @PostMapping
    public ResponseEntity postCarro(@RequestBody Carro carro){

      try{
          CarroDTO carDto = service.save(carro);
          URI location = getUri(carro.getId());
          return ResponseEntity.created(location).build();

      } catch ( Exception ex){
          Assert.notNull(carro.getId(),"Não foi possivel inserir");
          //pr("erro : ");
          return ResponseEntity.badRequest().build();
        }
    }


    private URI getUri(Long id ){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
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
