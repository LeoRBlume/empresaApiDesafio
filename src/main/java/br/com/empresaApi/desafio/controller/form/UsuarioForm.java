package br.com.empresaApi.desafio.controller.form;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UsuarioForm {

    @NotBlank(message = "Nome é obrigatorio!!")
    @NotNull
    @Length(min = 3, max = 35, message = "Minimo de 3 letras e maximo de 35!!")
    private String nome;

    @NotNull
    @NotBlank(message = "Nome é obrigatorio!!")
    @Length(min = 5, message = "Minimo 5 caracteres!!")
    private String email;

    @NotBlank(message = "Senha é obrigatorio!!")
    @NotNull
    @Length(min = 7, message = "Senha só é valida com mais de 7 caractres")
    private String senha;

    @NotNull
    @NotBlank(message = "CNPJ é obrigatorio!!")
    @Pattern(regexp = "(^\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}$)")
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

    @Override
    public String toString() {
        return "UsuarioForm{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
