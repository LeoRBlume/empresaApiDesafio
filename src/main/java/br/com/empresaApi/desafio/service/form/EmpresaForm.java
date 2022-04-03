package br.com.empresaApi.desafio.service.form;

import br.com.empresaApi.desafio.service.form.att.*;

import java.util.ArrayList;

public class EmpresaForm {
    private ArrayList<AtividadePrincipalForm> atividade_principal = new ArrayList<>();
    private String data_situacao;
    private String complemento;
    private String tipo;
    private String nome;
    private String uf;
    private String telefone;
    private String email;
    private ArrayList<AtividadeSecundariaForm> atividades_secundarias = new ArrayList<>();
    private ArrayList<QsaForm> qsa = new ArrayList<>();
    private String situacao;
    private String bairro;
    private String logradouro;
    private String numero;
    private String cep;
    private String municipio;
    private String porte;
    private String abertura;
    private String natureza_juridica;
    private String fantasia;
    private String cnpj;
    private String ultima_atualizacao;
    private String status;
    private String efr;
    private String motivo_situacao;
    private String situacao_especial;
    private String data_situacao_especial;
    private String capital_social;
    private Extra ExtraObject;
    private Billing BillingObject;

    public ArrayList<AtividadePrincipalForm> getAtividade_principal() {
        return atividade_principal;
    }

    public void setAtividade_principal(ArrayList<AtividadePrincipalForm> atividade_principal) {
        this.atividade_principal = atividade_principal;
    }

    public String getData_situacao() {
        return data_situacao;
    }

    public void setData_situacao(String data_situacao) {
        this.data_situacao = data_situacao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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

    public ArrayList<AtividadeSecundariaForm> getAtividades_secundarias() {
        return atividades_secundarias;
    }

    public void setAtividades_secundarias(ArrayList<AtividadeSecundariaForm> atividades_secundarias) {
        this.atividades_secundarias = atividades_secundarias;
    }

    public ArrayList<QsaForm> getQsa() {
        return qsa;
    }

    public void setQsa(ArrayList<QsaForm> qsa) {
        this.qsa = qsa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getNatureza_juridica() {
        return natureza_juridica;
    }

    public void setNatureza_juridica(String natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
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

    public String getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(String ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEfr() {
        return efr;
    }

    public void setEfr(String efr) {
        this.efr = efr;
    }

    public String getMotivo_situacao() {
        return motivo_situacao;
    }

    public void setMotivo_situacao(String motivo_situacao) {
        this.motivo_situacao = motivo_situacao;
    }

    public String getSituacao_especial() {
        return situacao_especial;
    }

    public void setSituacao_especial(String situacao_especial) {
        this.situacao_especial = situacao_especial;
    }

    public String getData_situacao_especial() {
        return data_situacao_especial;
    }

    public void setData_situacao_especial(String data_situacao_especial) {
        this.data_situacao_especial = data_situacao_especial;
    }

    public String getCapital_social() {
        return capital_social;
    }

    public void setCapital_social(String capital_social) {
        this.capital_social = capital_social;
    }

    public Extra getExtraObject() {
        return ExtraObject;
    }

    public void setExtraObject(Extra extraObject) {
        ExtraObject = extraObject;
    }

    public Billing getBillingObject() {
        return BillingObject;
    }

    public void setBillingObject(Billing billingObject) {
        BillingObject = billingObject;
    }

    @Override
    public String toString() {
        return "EmpresaForm{" +
                "atividade_principal=" + atividade_principal +
                ", data_situacao='" + data_situacao + '\'' +
                ", complemento='" + complemento + '\'' +
                ", tipo='" + tipo + '\'' +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", email='" + email + '\'' +
                ", atividades_secundarias=" + atividades_secundarias +
                ", qsa=" + qsa +
                ", situacao='" + situacao + '\'' +
                ", bairro='" + bairro + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", cep='" + cep + '\'' +
                ", municipio='" + municipio + '\'' +
                ", porte='" + porte + '\'' +
                ", abertura='" + abertura + '\'' +
                ", natureza_juridica='" + natureza_juridica + '\'' +
                ", fantasia='" + fantasia + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", ultima_atualizacao='" + ultima_atualizacao + '\'' +
                ", status='" + status + '\'' +
                ", efr='" + efr + '\'' +
                ", motivo_situacao='" + motivo_situacao + '\'' +
                ", situacao_especial='" + situacao_especial + '\'' +
                ", data_situacao_especial='" + data_situacao_especial + '\'' +
                ", capital_social='" + capital_social + '\'' +
                ", ExtraObject=" + ExtraObject +
                ", BillingObject=" + BillingObject +
                '}';
    }
}
