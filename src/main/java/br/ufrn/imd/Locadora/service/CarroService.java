package br.ufrn.imd.Locadora.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;

import java.util.List;

import br.ufrn.imd.Locadora.entity.Carro;
import br.ufrn.imd.Locadora.repositories.CarroRepository;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Transactional
    @ResponseBody
    public String createCar(Carro carro) {
        try {
            if (!carroRepository.existsByPlaca(carro.getPlaca())) {
                carro.setId(null == carroRepository.findMaxId() ? 1 : carroRepository.findMaxId() + 1);
                carro.setStatus("Livre");
                carro.setLocatario(0);
                carro.setAtivo(true);
                carroRepository.save(carro);
                String response = "Novo carro cadastrado com sucesso";
                response = response.replaceAll("'", String.valueOf('"'));
                return response;
            } else {
                String response = "Já existe outro carro com essa mesma placa";
                response = response.replaceAll("'", String.valueOf('"'));
                return response;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Carro> listCars() {
        return carroRepository.findAll();
    }

    public List<Carro> listCarsByPlaca(String placa) {
        return carroRepository.findByPlaca(placa);
    }

    @Transactional
    public String editCar(int id, JsonObject carro) {
        if (carroRepository.existsById(id)) {
            try {
                Carro carro_e = carroRepository.findById(id).get();
                int run = 0;
                if (carro.get("diaria") != null) {
                    run++;
                    carro_e.setDiaria(carro.get("diaria").getAsFloat());
                }
                if (carro.get("status") != null && carro.get("locatario") != null) {
                    if(carro_e.getAtivo() == true){
                        carro_e.setStatus(carro.get("status").getAsString());
                        carro_e.setLocatario(carro.get("locatario").getAsInt());
                        run++;
                    }else{
                        return "Este carro não está mais disponível";    
                    }
                }
                if (run > 0) {
                    carroRepository.save(carro_e);
                    return "Dados do carro foram atualizados";
                } else {
                    return "Nada para atualizar";
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return "car not found";
    }

    @Transactional
    public String deleteCar(int id) {
        if (carroRepository.existsById(id)) {
            try {
                Carro carro_d = carroRepository.findById(id).get();
                if (carro_d.getStatus().equals("Livre")) {
                    carro_d.setStatus("Vendido");
                    carro_d.setAtivo(false);
                    carroRepository.save(carro_d);
                } else {
                    return "Este carro está ocupado ou já foi vendido";
                }
                return "Carro vendido com sucesso.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "Este carro não existe";
        }
    }
}