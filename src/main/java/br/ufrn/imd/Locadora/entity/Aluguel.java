package br.ufrn.imd.Locadora.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluguel {

    @Id
    private Integer id;
    private Integer veiculo_id;
    private Integer locatario_id;
    private String dataIni;
    private String dataFim;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVeiculo_id() {
        return this.veiculo_id;
    }

    public void setVeiculo_id(Integer veiculo_id) {
        this.veiculo_id = veiculo_id;
    }

    public Integer getLocatario_id() {
        return this.locatario_id;
    }

    public void setLocatario_id(Integer locatario_id) {
        this.locatario_id = locatario_id;
    }

    public String getDataIni() {
        return this.dataIni;
    }

    public void setDataIni(String dataIni) {
        this.dataIni = dataIni;
    }

    public String getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }


}
