package br.ufrn.imd.Locadora.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import br.ufrn.imd.Locadora.entity.Carro;
import br.ufrn.imd.Locadora.repositories.CarroRepository;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    @Transactional
    public String createCarro(Carro carro) {
        try {
            carroRepository.save(carro);
            return "Student record created successfully.";
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Carro> readCarros() {
        return carroRepository.findAll();
    }

    @Transactional
    public String updateCarro(Carro carro) {
        if(carroRepository.existsByPlaca(carro.getPlaca())){
            try {
                Carro updatedCarro = carroRepository.findByPlaca(carro.getPlaca()).map(
                    Carro -> {
                        carro.setCor(carro.getCor());
                        carro.setCategoriaCNH(carro.categoriaCNH());
                        carro.setDiaria(carro.getDiaria());
                        carro.setStatus(carro.getStatus());
                        carro.setPotencia(carro.getPotencia());
                        carro.setLocatario(carro.getLocatario());
                        return CarroRepository.save(carro);
                    }
                );
                List<Carro> carros = carroRepository.findByPlaca(carro.getPlaca());
                // carro.stream().forEach(c -> {
                //     Carro carroToBeUpdate = carroRepository.findById(c.getId()).get();
                //     carroToBeUpdate.setMarca(carro.getMarca());
                //     carroToBeUpdate.setModelo(carro.getEmail());
                //     carroToBeUpdate.save(carroToBeUpdate);
                // });
                return "Car record updated.";
            } catch (Exception e) {
                throw e;
            }
        }else{
            return "Car not found!";
        }
    }

    @Transactional
    public String deleteCarro(Carro carro) {
        if (carroRepository.existsByPlaca(carro.getPlaca())) {
            try {
                List<Carro> carros = carroRepository.findByPlaca(carro.getPlaca());
                carros.stream().forEach(s -> {
                    carroRepository.delete(s);
                });
                return "Car record deleted successfully.";
            } catch (Exception e) {
                throw e;
            }

        } else {
            return "Student does not exist";
        }
    }
}