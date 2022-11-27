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
                carro.setId(null == carroRepository.findMaxId() ? 0 : carroRepository.findMaxId() + 1);
                carro.setStatus("Livre");
                carro.setLocatario(0);
                carro.setAtivo(true);
                carroRepository.save(carro);
                String response = "{'error':false, 'info': 'new car registred successfully!','id': " + carro.getId()
                        + "}";
                response = response.replaceAll("'", String.valueOf('"'));
                return response;
            } else {
                String response = "{'error':true, 'info': 'another car with same plate was found!'}";
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
                    run++;
                    carro_e.setStatus(carro.get("status").getAsString());
                    carro_e.setLocatario(carro.get("locatario").getAsInt());
                }
                if (run > 0) {
                    carroRepository.save(carro_e);
                    return "car was updated";
                } else {
                    return "nothing to update";
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return "car not found";
    }

    @Transactional
    public String locateCar(int id, JsonObject json) {
        try {
            if (carroRepository.existsById(id)) {
                Carro carro = carroRepository.findById(id).get();
                if (carro.getStatus() == "Livre") {
                    carro.setStatus("Alugado");
                    carroRepository.save(carro);
                } else {
                    return "{\"error\":true,\"info\":\"this car is unavaliable\"}";
                }
                return "{\"error\":false,\"info\":\"car located with success\"}";
            } else {
                return "{\"error\":true,\"info\":\"car not found\"}";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Transactional
    public String deleteCar(int id) {
        if (carroRepository.existsById(id)) {
            try {
                Carro carro_d = carroRepository.findById(id).get();
                if (carro_d.getStatus().equals("Livre")) {
                    carro_d.setStatus("Vendido");
                    carroRepository.save(carro_d);
                } else {
                    return "this car is currently busy or was sold";
                }
                return "car sold successfully.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "this car does not exist";
        }
    }
}