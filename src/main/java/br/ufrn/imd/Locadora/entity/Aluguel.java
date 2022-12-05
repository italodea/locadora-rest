package br.ufrn.imd.Locadora.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aluguel {

    @Id
    private Integer id;
    private Integer veiculo_id;
    private Integer locatario_id;
    private String data_ini;
    private String data_fim;
    private float total;

    public void setId(Integer id) {
        this.id = id;
    }
    public void setVeiculo_id(Integer veiculo_id) {
        this.veiculo_id = veiculo_id;
    }
    public void setLocatario_id(Integer locatario_id) {
        this.locatario_id = locatario_id;
    }
    public void setdata_ini(String data_ini) {
        this.data_ini = data_ini;
    }
    public void setdata_fim(String data_fim) {
        this.data_fim = data_fim;
    }
    public void setTotal(float total){
        this.total = total;
    }
    
    public Integer getId(){
        return this.id;
    }
    public Integer getVeiculo_id() {
        return this.veiculo_id;
    }
    public Integer getLocatario_id() {
        return this.locatario_id;
    }
    public String getdata_ini() {
        return this.data_ini;
    }
    public String getdata_fim() {
        return this.data_fim;
    }
    public float getTotal(){
        return this.total;
    }

}
