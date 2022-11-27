package br.ufrn.imd.Locadora.entity;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro {
    
    private @Id @GeneratedValue Long id;
    private String placa;
    private String marca;
    private String modelo;
    private String cor;
    private String combustivel;
    private Float diaria;
    private Integer potencia;
    private String status;
    private Integer locatario;


    Carro(){}

    Carro(String marca, String modelo, String placa){
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
    }


    public Long getId(){
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }

    public String getCor() {
        return this.cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getCombustivel() {
        return this.combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public Float getDiaria() {
        return this.diaria;
    }

    public void setDiaria(Float diaria) {
        this.diaria = diaria;
    }

    public Integer getPotencia() {
        return this.potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLocatario() {
        return this.locatario;
    }

    public void setLocatario(Integer locatario) {
        this.locatario = locatario;
    }


    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Carro)){
            return false;
        }

        Carro carro = (Carro) o;
        return carro == o;
    }

}