package br.ufrn.imd.Locadora.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.web.bind.annotation.RequestMethod;


@RestController 
@RequestMapping("/aluguel")
public class AluguelController {
    
    

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String createAluguel(@RequestBody String json) {
        try {
            JsonObject aluguelJSON = new JsonParser().parse(json).getAsJsonObject();
            if(aluguelJSON.get("veiculo_id") == NULL){
                return "veiculo_id não encontrado!";
            }

            if(aluguelJSON.get("veiculo_id") == NULL){
                return "veiculo_id não encontrado!";
            }

            if(aluguelJSON.get("cliente_id") == NULL){
                return "cliente_id não encontrado!";
            }

            return 

            //return carroService.locateCar(id,carrojson);
        } catch (Exception e) {
            return "erro";
        }
        return "wi";
        //return new Aluguel();
    }
    
}