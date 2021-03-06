package com.carros.carros.domain.dto;

import com.carros.carros.domain.Carro;
import lombok.Data;

@Data
public class CarroDTO {

    private Long id;
    private String nome;
    private String tipo;

    public CarroDTO(Carro c ) {

        this.id = c.getId();
        this.nome = c.getNome();
        this.tipo = c.getTipo();
    }
    public CarroDTO() {

    };

    public static CarroDTO create(Carro carro) {

        CarroDTO carDto = new CarroDTO();
        carDto.setId(carro.getId());
        carDto.setNome(carro.getNome());
        carDto.setTipo(carro.getTipo());
        return carDto;

    }

}
