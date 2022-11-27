package br.ufrn.imd.Locadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.ufrn.imd.Locadora.entity.Cliente;
import br.ufrn.imd.Locadora.service.ClienteService;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String createCliente(@RequestBody Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    @RequestMapping(value="list", method=RequestMethod.GET)
    public List<Cliente> listClientes() {
        return clienteService.listClientes();
    }

    @RequestMapping(value="search/{cpf}", method=RequestMethod.GET)
    public List<Cliente> listClientesByCpf(@PathVariable String cpf) {
        return clienteService.listClientesByCpf(cpf);
    }
    
    @RequestMapping(value="edit/{id}", method=RequestMethod.PUT)
    public String editCliente(@PathVariable int id, @RequestBody String cliente) {
        try {
            JsonObject json = new JsonParser().parse(cliente).getAsJsonObject();
            return clienteService.editCliente(id, json);
        } catch (Exception e) {
            return "erro";
        }
    }

    @RequestMapping(value="delete/{id}", method=RequestMethod.DELETE)
    public String deleteCliente(@PathVariable int id) {
        return clienteService.deleteCliente(id);
    }
}
