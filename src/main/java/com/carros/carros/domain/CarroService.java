package com.carros.carros.domain;

import com.carros.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros(){
        List<Carro> carros = rep.findAll();
        List<CarroDTO> list = new ArrayList<>();

        for ( Carro c :  carros){
            list.add(new CarroDTO(c));
        }
        return list;
    }
    public Optional<CarroDTO> getCarrosById(Long id){

        Optional<Carro> carro = rep.findById(id);

        if ( carro.isPresent()){
            return Optional.of(new CarroDTO(carro.get()));

        }else{
            return null;
        }

    }
    public List<CarroDTO> getCarrosByTipo(String tipo){

        return rep.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
    }
    public List<Carro> getCarrosFake() {

        List<Carro> carros = new ArrayList<>();

        //carros.add(new Carro(1L,"Fusca"));
        //carros.add( new Carro(2L,"Teste"));

        return carros;
    }

    public CarroDTO save(Carro carro) {
        //Assert.notNull(carro.getId(),"Não foi possivel inserir");
        Carro c = rep.save(carro);
        return  CarroDTO.create(c);
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possível atualizar");
        Optional<Carro> optional = rep.findById(id);

        if (optional.isPresent() ){
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            rep.save(db);
            return db;
        }else{
            throw new RuntimeException("Não foi possível atualizar");
        }

    }

    public void delete(Long id) {

          if ( getCarrosById(id).isPresent()){
            rep.deleteById(id);
        }
    }
}
