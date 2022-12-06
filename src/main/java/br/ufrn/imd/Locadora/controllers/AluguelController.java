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

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createAluguel(@RequestBody String json) {
        JsonObject aluguelJSON = new JsonParser().parse(json).getAsJsonObject();
        return aluguelService.createAluguel(aluguelJSON);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT)
    public String updateAluguel(@RequestBody String json, @PathVariable int id) {
        JsonObject aluguelJson = new JsonParser().parse(json).getAsJsonObject();
        return aluguelService.updateAluguel(id, aluguelJson);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Aluguel> listClientes() {
        return aluguelService.listAlugueis();
    }

    @RequestMapping(value = "search/cliente/{id}", method = RequestMethod.GET)
    public List<Aluguel> listAlugueisByClienteId(@PathVariable int id) {
        return aluguelService.listAlugueisByClienteId(id);
    }

    @RequestMapping(value = "search/carro/{id}", method = RequestMethod.GET)
    public List<Aluguel> listAlugueisByCarroId(@PathVariable int id) {
        return aluguelService.listAlugueisByVeiculoId(id);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public String requestMethodName(@PathVariable int id) {
        return aluguelService.deleteAluguel(id);
    }

}