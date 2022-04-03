package br.com.empresaApi.desafio.model;

import br.com.empresaApi.desafio.service.form.EmpresaForm;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Empresa {
    @Id
    @NotNull
    @NotBlank
    private String cnpj;
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

    public Empresa() {
    }

    public Empresa(String cnpj, String nome, String telefone, String email, String situacao, String numero, String cep, String fantasia, Double capital_social) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.situacao = situacao;
        this.numero = numero;
        this.cep = cep;
        this.fantasia = fantasia;
        this.capital_social = capital_social;
    }

    public Empresa(EmpresaForm empresaForm) {
        this.nome = empresaForm.getNome();
        this.telefone = empresaForm.getTelefone();
        this.email = empresaForm.getEmail();
        this.situacao = empresaForm.getSituacao();
        this.numero = empresaForm.getNumero();
        this.cep = empresaForm.getCep();
        this.fantasia = empresaForm.getFantasia();
        this.cnpj = empresaForm.getCnpj();
        this.capital_social = (empresaForm.getCapital_social() != null && !(empresaForm.getCapital_social().equals(""))) ? Double.parseDouble(empresaForm.getCapital_social()) : 0.0;
    }

    public static Empresa init(String cnpj) {
        return new Empresa(cnpj, "", "", "", "", "", "", "", 0.0);
    }

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Double getCapital_social() {
        return capital_social;
    }

    public void setCapital_social(Double capital_social) {
        this.capital_social = capital_social;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", situacao='" + situacao + '\'' +
                ", numero='" + numero + '\'' +
                ", cep='" + cep + '\'' +
                ", fantasia='" + fantasia + '\'' +
                ", capital_social=" + capital_social +
                '}';
    }
}
