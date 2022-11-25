package br.ufrn.imd.Locadora.entity;

import javax.persistence.Entity;

//import java.util.Objects;

//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPlaca(String placa){
        this.placa = placa;
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

    @Override
    public int hashCode(){
        return 1;
    }

    @Override
    public String toString() {
        return "Carro{" + "id=" + this.id + ", Marca='" + this.marca + '\'' + ", Modelo='" + this.modelo + '\'' +", Placa='" + this.placa + "'}";
    }
}