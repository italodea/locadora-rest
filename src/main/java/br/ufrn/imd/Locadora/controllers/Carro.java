package br.ufrn.imd.Locadora.controllers;

import javax.persistence.Entity;

//import java.util.Objects;

//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Carro {
    
    private @Id @GeneratedValue Long id;
    private String marca;
    private String modelo;
    private String placa;

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
        //return Object.equals(this.placa, carro.placa);
    }

    @Override
    public int hashCode(){
        //Object o = new Object();
        return 1;
        //return o.hash(this.placa);
    }

    @Override
    public String toString() {
        return "Carro{" + "id=" + this.id + ", Marca='" + this.marca + '\'' + ", Modelo='" + this.modelo + '\'' +", Placa='" + this.placa + "'}";
    }
}