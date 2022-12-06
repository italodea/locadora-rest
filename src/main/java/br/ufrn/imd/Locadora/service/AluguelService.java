package br.ufrn.imd.Locadora.service;

import javax.transaction.Transactional;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;

import br.ufrn.imd.Locadora.entity.Aluguel;
import br.ufrn.imd.Locadora.entity.Carro;
import br.ufrn.imd.Locadora.entity.Cliente;
import br.ufrn.imd.Locadora.repositories.AluguelRepository;
import br.ufrn.imd.Locadora.repositories.CarroRepository;
import br.ufrn.imd.Locadora.repositories.ClienteRepository;

@Service
public class AluguelService {

    @Autowired
    AluguelRepository aluguelRepository;
    @Autowired
    CarroRepository carroRepository;
    @Autowired
    ClienteRepository clienteRepository;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Transactional
    public String createAluguel(JsonObject json) {
        try {
            if (json.get("placa") != null && json.get("cpf") != null && json.get("data_ini") != null
                    && json.get("data_fim") != null) {
                if (carroRepository.existsByPlaca(json.get("placa").getAsString())) {
                    if (clienteRepository.existsByCpf(json.get("cpf").getAsString())) {
                        Carro carro = carroRepository.findByPlaca(json.get("placa").getAsString()).get(0);
                        Cliente cliente = clienteRepository.findByCpf(json.get("cpf").getAsString()).get(0);
                        if (carro.getStatus().equals("Livre") && carro.getAtivo() == true) {
                            if (cliente.getAtivo() == true) {
                                Date data_ini = UtilsService.strToDate(json.get("data_ini").getAsString());
                                Date data_fim = UtilsService.strToDate(json.get("data_fim").getAsString());
                                
                                if (data_ini.compareTo(data_fim) < 0) {
                                    
                                    df.setRoundingMode(RoundingMode.UP);
                                    Aluguel aluguel = new Aluguel();
                                    aluguel.setId(null == aluguelRepository.findMaxId() ? 1 : aluguelRepository.findMaxId() + 1);
                                    aluguel.setLocatario_id(cliente.getId());
                                    aluguel.setVeiculo_id(carro.getId());
                                    aluguel.setdata_ini(json.get("data_ini").getAsString());
                                    aluguel.setdata_fim(json.get("data_fim").getAsString());
                                    aluguel.setTotal(UtilsService.roundValue(carro.getDiaria() * UtilsService.getDateDiff(data_ini, data_fim, TimeUnit.DAYS)));
                                    carro.setStatus("Alugado");
                                    aluguelRepository.save(aluguel);
                                    carroRepository.save(carro);
                                    return "Novo aluguel criado";
                                } else {
                                    return "Intervalo inválido";
                                }
                            }else{
                                return "Impossível prosseguir, cliente desativado";
                            }
                        } else {
                            return "Este carro não está disponível";
                        }
                    } else {
                        return "Cliente não encontrado";
                    }
                } else {
                    return "Carro não encontrado";
                }
            } else {
                return "Verifique se todos os campos foram preechidos";
            }
        } catch (Exception e) {
            return "erro" + e;
        }
    }

    @Transactional
    public String updateAluguel(int id, JsonObject aluguel) {
        try {
            if (aluguelRepository.existsById(id)) {
                int updates = 0;
                Aluguel aluguel2 = aluguelRepository.findById(id).get();
                if (aluguel.get("total") != null) {
                    aluguel2.setTotal(aluguel.get("total").getAsFloat());
                    updates++;
                }
                if (aluguel.get("data_fim") != null) {
                    Carro carro = carroRepository.findById(aluguel2.getVeiculo_id()).get();
                    Date data_ini = UtilsService.strToDate(aluguel2.getdata_ini());
                    Date data_fim = UtilsService.strToDate(aluguel.get("data_fim").getAsString());
                    if (data_ini.compareTo(data_fim) < 0) {
                        return "Impossível utilizar a nova data de final";
                    }
                    df.setRoundingMode(RoundingMode.UP);
                    aluguel2.setTotal(UtilsService.roundValue(
                            carro.getDiaria() * UtilsService.getDateDiff(data_ini, data_fim, TimeUnit.DAYS)));
                    updates++;
                }
                if (updates > 0) {
                    return "Aluguel atualizado com sucesso";
                }
                return "Nada para atualizar";
            } else {
                return "Aluguel não encontrado";
            }
        } catch (Exception e) {
            return "Error " + e.getMessage();
        }
    }

    public List<Aluguel> listAlugueis() {
        return aluguelRepository.findAll();
    }

    public List<Aluguel> listAlugueisByVeiculoId(int veiculo_id) {
        return aluguelRepository.findAllByVeiculoId(veiculo_id);
    }

    public List<Aluguel> listAlugueisByClienteId(int locatario_id) {
        return aluguelRepository.findALlByLocatarioId(locatario_id);
    }

    @Transactional
    public String deleteAluguel(int id) {
        if (aluguelRepository.existsById(id)) {
            Aluguel aluguel = aluguelRepository.findById(id).get();
            if (carroRepository.existsById(aluguel.getVeiculo_id())) {
                Carro carro = carroRepository.findById(aluguel.getVeiculo_id()).get();
                if(!carro.getStatus().equals("Livre")){
                    carro.setStatus("Livre");
                    return "Aluguel encerrado com sucesso";
                }else {
                    return "Este aluguel já estava finalizado";
                }
            } else {
                return "Este aluguel possuia um veículo não encontrado";
            }
        }
        return "Aluguel não encontrado";
    }
}
