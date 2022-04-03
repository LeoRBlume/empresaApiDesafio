package br.com.empresaApi.desafio.controller.dto;

import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.model.Usuario;

public class UsuarioDto {

    private String nome;
    private String email;
    private String senha = "Senha inalterada";
    private Empresa empresa;

    public void converterAtualizacoes(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.empresa = usuario.getEmpresa();
    }

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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", empresa=" + empresa +
                '}';
    }
}
