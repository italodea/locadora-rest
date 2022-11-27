package br.ufrn.imd.Locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.Locadora.entity.Carro;
import br.ufrn.imd.Locadora.service.CarroService;

import java.util.List;
@RestController
public class CarroController {

    @Autowired
    private CarroService carroService;

    
    @RequestMapping(value="info", method = RequestMethod.GET)
    public String info(){
        return "this application is up!";
    }

    @RequestMapping(value = "createcar", method = RequestMethod.POST)
    public String createCar(@RequestBody Carro carro){
        return carroService.createCarro(carro);
    }

    @RequestMapping(value="viewcars", method = RequestMethod.GET)
    public List<Carro> viewCars(){
        return carroService.readCarros();
    }
}
