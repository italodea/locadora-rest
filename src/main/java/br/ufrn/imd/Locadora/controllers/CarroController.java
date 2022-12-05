package br.ufrn.imd.Locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.ufrn.imd.Locadora.entity.Carro;
import br.ufrn.imd.Locadora.service.CarroService;

import java.util.List;


@RestController
@RequestMapping("/car")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createCar(@RequestBody Carro carro){
        return carroService.createCar(carro);
    }
    
    @RequestMapping(value="list", method = RequestMethod.GET)
    public List<Carro> listCars(){
        return carroService.listCars();
    }

    @RequestMapping(value="search/{placa}", method=RequestMethod.GET)
    public List<Carro> listCarsByPlaca(@PathVariable String placa) {
        return carroService.listCarsByPlaca(placa);
    }
    
    @RequestMapping(value="edit/{id}", method=RequestMethod.PUT)
    public String editCar(@PathVariable int id, @RequestBody String carro) {
        try {
            JsonObject carrojson = new JsonParser().parse(carro).getAsJsonObject();
            return carroService.editCar(id, carrojson);
        } catch (Exception e) {
            return "erro";
        }
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
    public String deleteCar(@PathVariable int id) {
        return carroService.deleteCar(id);
    }
}