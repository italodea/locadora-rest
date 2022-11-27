package br.ufrn.imd.Locadora.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro {
    
    @Id
    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private String combustivel;
    private Float diaria;
    private Integer potencia;
    private String status;
    private Integer locatario;
    private String categoriaCNH;
    private Boolean ativo;


    public Carro(){
    }

    public int getId(){
        return this.id;
    }
    public String getMarca(){
        return this.marca;
    }
    public String getModelo(){
        return this.modelo;
    }
    public String getPlaca(){
        return this.placa;
    }
    public String getCor() {
        return this.cor;
    }
    public String getCategoriaCNH(){
        return this.categoriaCNH;
    }
    public String getCombustivel() {
        return this.combustivel;
    }
    public Float getDiaria() {
        return this.diaria;
    }
    public Integer getPotencia() {
        return this.potencia;
    }
    public String getStatus() {
        return this.status;
    }
    public Integer getLocatario() {
        return this.locatario;
    }
    public Boolean getAtivo(){
        return this.ativo;
    }
    

    public void setId(int id){
        this.id = id;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public void setPlaca(String placa){
        this.placa = placa;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }
    public void setDiaria(Float diaria) {
        this.diaria = diaria;
    }
    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setLocatario(Integer locatario) {
        this.locatario = locatario;
    }
    public void setCategoriaCNH(String cnh){
        this.categoriaCNH = cnh;
    }
    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }


    @Override
    public String toString(){
        return "Car{"+
            "id=" + this.id +
            ",placa="+ this.placa +
            ",marca="+ this.marca +
            ",ativo="+ this.ativo
        +"}";
    }
}