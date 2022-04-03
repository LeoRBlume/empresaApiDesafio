package br.com.empresaApi.desafio.controller.dto;

import br.com.empresaApi.desafio.model.Empresa;
import br.com.empresaApi.desafio.model.Usuario;

public class UsuarioDto {

    private String nome;
    private String email;
    private String senha;
    private Empresa empresa;

    public static UsuarioDto converter(Usuario usuario) {
        return new UsuarioDto(usuario.getNome(), usuario.getEmail(), "Senha não pode ser retornada", usuario.getEmpresa());
    }

    public UsuarioDto() {
    }

    public UsuarioDto(String nome, String email, String senha, Empresa empresa) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.empresa = empresa;
    }

    public void converterAtualizacoes(Usuario usuario) {
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.empresa = usuario.getEmpresa();
        if (this.senha == null) this.setSenha("Senha inalterada");
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
