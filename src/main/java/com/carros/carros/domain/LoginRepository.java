package com.carros.carros.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoginRepository extends CrudRepository<Carro,Long> {
    List<Carro> findByTipo(String tipo);
}
