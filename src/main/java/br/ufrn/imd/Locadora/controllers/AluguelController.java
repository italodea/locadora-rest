package br.ufrn.imd.Locadora.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.ufrn.imd.Locadora.entity.Aluguel;
import br.ufrn.imd.Locadora.service.AluguelService;

import org.springframework.web.bind.annotation.RequestMethod;





@RestController 
@RequestMapping("/aluguel")
public class AluguelController {
    
    @Autowired
    AluguelService aluguelService;

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String createAluguel(@RequestBody String json) {
        try {
            JsonObject aluguelJSON = new JsonParser().parse(json).getAsJsonObject();
            if(aluguelJSON.get("veiculo_id") == null){
                return "veiculo_id not found!";
            }

            if(aluguelJSON.get("locatario_id") == null){
                return "locatario_id not found!";
            }

            if(aluguelJSON.get("data_ini") == null){
                return "data_ini not found!";
            }

            if(aluguelJSON.get("data_fim") == null){
                return "data_fim not found!";
            }

            Aluguel aluguel = new Aluguel();
            aluguel.setVeiculo_id(aluguelJSON.get("veiculo_id").getAsInt());
            aluguel.setLocatario_id(aluguelJSON.get("locatario_id").getAsInt());
            aluguel.setdata_ini(aluguelJSON.get("data_ini").getAsString());
            aluguel.setdata_fim(aluguelJSON.get("data_fim").getAsString());
            return aluguelService.createAluguel(aluguel);
        } catch (Exception e) {
            return "erro";
        }
    }

    @RequestMapping(value="update/{id}", method=RequestMethod.PUT)
    public String updateAluguel(@RequestBody String json, @PathVariable int id) {
        JsonObject aluguelJson = new JsonParser().parse(json).getAsJsonObject();
        return aluguelService.updateAluguel(id, aluguelJson);
    }
    
    @RequestMapping(value="list", method=RequestMethod.GET)
    public List<Aluguel> listClientes() {
        return aluguelService.listAlugueis();
    }
    
    @RequestMapping(value="search/locatario/{id}", method=RequestMethod.GET)
    public List<Aluguel> listAlugueisByLocatarioId(@PathVariable int id) {
        return aluguelService.listAlugueisByLoacatarioId(id);
    }

    @RequestMapping(value="search/carro/{id}", method=RequestMethod.GET)
    public List<Aluguel> listAlugueisByCarroId(@PathVariable int id) {
        return aluguelService.listAlugueisByLoacatarioId(id);
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.GET)
    public String requestMethodName(@PathVariable int id) {
        return aluguelService.deleteAluguel(0);
    }
    


}