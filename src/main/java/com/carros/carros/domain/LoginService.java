package com.carros.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private CarroRepository rep;

    public Iterable<Carro> getCarros(){
        return rep.findAll();
    }
    public Optional<Carro> getCarrosById(Long id){
        return rep.findById(id);
    }
    public List<Carro> getCarrosByTipo(String tipo){
        return rep.findByTipo(tipo);
    }
    public List<Carro> getCarrosFake() {

        List<Carro> carros = new ArrayList<>();

        //carros.add(new Carro(1L,"Fusca"));
        //carros.add( new Carro(2L,"Teste"));

        return carros;

    }

    public String save(Carro carro) {

        Carro c = rep.save(carro);
        return "Carro salvo :" + c.getId() ;
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id,"Não foi possível atualizar");
        Optional<Carro> optional = getCarrosById(id);
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

        Optional<Carro> c = getCarrosById(id);

        if ( c.isPresent()){
            rep.deleteById(id);
        }
    }
}
