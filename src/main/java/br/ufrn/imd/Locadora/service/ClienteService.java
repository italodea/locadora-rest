package br.ufrn.imd.Locadora.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import br.ufrn.imd.Locadora.entity.Cliente;
import br.ufrn.imd.Locadora.repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    @ResponseBody
    public String createCliente(Cliente cliente) {
        try {
            if (!clienteRepository.existsByCpf(cliente.getCpf())) {
                cliente.setId(null == clienteRepository.findMaxId() ? 0 : clienteRepository.findMaxId() + 1);
                cliente.setAtivo(true);
                clienteRepository.save(cliente);
                String response = "{'error':false, 'info': 'new cliente registred successfully!','id': "
                        + cliente.getId() + "}";
                response = response.replaceAll("'", String.valueOf('"'));
                return response;
            } else {
                String response = "{'error':true, 'info': 'another cliente with same cpf was found!'}";
                response = response.replaceAll("'", String.valueOf('"'));

                return response;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Cliente> listClientes() {
        return clienteRepository.findAll();
    }

    public List<Cliente> listClientesByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf);
    }

    @Transactional
    public String editCliente(int id, JsonObject cliente) {
        if (clienteRepository.existsById(id)) {
            try {
                Cliente cliente_e = clienteRepository.findById(id).get();
                int run = 0;
                if(cliente.get("email") != null){
                    run++;
                    cliente_e.setEmail(cliente.get("email").getAsString());
                }
                if(cliente.get("categoriaCNH") != null){
                    run++;
                    cliente_e.setCategoriaCNH(cliente.get("categoriaCNH").getAsString());
                }
                if(run > 0){
                    clienteRepository.save(cliente_e);
                    return "cliente was updated";
                }else{
                    return "nothing to update";
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return "cliente not found";
    }

    @Transactional
    public String deleteCliente(int id) {
        if (clienteRepository.existsById(id)) {
            try {
                Cliente cliente_d = clienteRepository.findById(id).get();
                if(cliente_d.getAtivo() == true){
                    cliente_d.setAtivo(false);
                    clienteRepository.save(cliente_d);
                    return "cliente record deleted successfully.";
                }else{
                    return "client already disabled";
                }
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "Cliente does not exist";
        }
    }
}
