package br.com.empresaApi.desafio.controller.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AtualizacaoCadastroForm {

    @NotBlank(message = "Nome é obrigatorio!!")
    @Length(min = 3, max = 35, message = "Minimo de 3 letras e maximo de 35!!")
    private String nome;

    @NotBlank(message = "Nome é obrigatorio!!")
    @Length(min = 5, message = "Minimo 5 caracteres!!")
    private String email;

    @NotBlank(message = "Senha é obrigatorio!!")
    @Length(min = 7, message = "Senha só é valida com mais de 7 caractres")
    private String senha;

    private String cnpj;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
