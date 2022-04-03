package br.com.empresaApi.desafio.controller.form;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AtualizacaoEmpresaForm {

    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @NotNull
    private String email;
    @NotNull
    private String situacao;
    @NotNull
    private String numero;
    @NotNull
    private String cep;
    @NotNull
    private String fantasia;
    @NotNull
    private Double capital_social;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public Double getCapital_social() {
        return capital_social;
    }

    public void setCapital_social(Double capital_social) {
        this.capital_social = capital_social;
    }
}
