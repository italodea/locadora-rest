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
                String response = "Novo cliente cadastrado com sucesso";
                response = response.replaceAll("'", String.valueOf('"'));
                return response;
            } else {
                String response = "Já existe outro cliente cadastrado usando este CPF";
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
                    cliente_e.setEmail(cliente.get("email").getAsString());
                    run++;
                }
                if(cliente.get("categoriaCNH") != null){
                    cliente_e.setCategoriaCNH(cliente.get("categoriaCNH").getAsString());
                    run++;
                }
                if(run > 0){
                    clienteRepository.save(cliente_e);
                    return "Dados do cliente foram atualizados";
                }else{
                    return "Nada para atualizar";
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return "Cliente não encontrado";
    }

    @Transactional
    public String deleteCliente(int id) {
        if (clienteRepository.existsById(id)) {
            try {
                Cliente cliente_d = clienteRepository.findById(id).get();
                if(cliente_d.getAtivo() == true){
                    cliente_d.setAtivo(false);
                    clienteRepository.save(cliente_d);
                    return "Cliente foi desabilitado com sucesso.";
                }else{
                    return "Este cliente já está desabilitado";
                }
            } catch (Exception e) {
                throw e;
            }
        } else {
            return "Cliente não encontrado";
        }
    }
}
