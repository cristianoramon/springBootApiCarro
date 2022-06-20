package com.carros.carros.api;

import com.carros.carros.domain.Carro;
import com.carros.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @Autowired
    private CarroService service;


    @PostMapping
    public void postCarro(@RequestBody Carro carro){

        service.save(carro);
    }


}
