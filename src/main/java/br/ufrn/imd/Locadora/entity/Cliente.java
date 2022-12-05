package br.ufrn.imd.Locadora.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    private int id;
    private String cpf;
    private String nome;
    private String categoriaCNH;
    private String email;
    private String nascimento;
    private Boolean ativo;

    public Cliente(){

    }

    public void setId(int id){
        this.id = id;
    }
    public void setCpf(String cpf) {
		this.cpf = cpf;
	}
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCategoriaCNH(String categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }
    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }

    public int getId(){
		return this.id;
	}
    public String getCpf() {
		return this.cpf;
	}
    public String getNome(){
        return this.nome;
    }
    public String getCategoriaCNH() {
        return this.categoriaCNH;
    }
    public String getEmail() {
        return this.email;
    }
    public String getNascimento() {
        return this.nascimento;
    }
    public boolean getAtivo(){
        return this.ativo;
    }
    
}
