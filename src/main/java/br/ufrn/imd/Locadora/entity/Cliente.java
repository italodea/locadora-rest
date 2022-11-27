package br.ufrn.imd.Locadora.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "cliente")
public class Cliente {
    private @Id @GeneratedValue Long id;
    private String cpf;
    private String categoriaCNH;
    private String email;
    private String nascimento;

    public Long getId()
    {
		return this.id;
	}

    public String getCpf() {
		return this.cpf;
	}

    public void setCpf(String cpf) {
		this.cpf = cpf;
	}

    public String getCategoriaCNH() {
        return this.categoriaCNH;
    }

    public void setCategoriaCNH(String categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNascimento() {
        return this.nascimento;
    }

    public String getNascimentoDMY() {
        return this.nascimento;
    }

    public String getNascimentoYMD() {
        return this.nascimento;
    }

    public Date getNascimentoDateFormat() {
        return new Date();
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

}
