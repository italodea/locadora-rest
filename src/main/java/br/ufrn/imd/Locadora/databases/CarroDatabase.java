package br.ufrn.imd.Locadora.databases;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarroDatabase{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String placa;

    private String marca;

    private String modelo;


    public Integer getId(){
        return this.id;
    }

    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}