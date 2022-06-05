package br.com.projetomedico.model;

public class Pessoa {

    private Integer idPessoa;
    private String Nome;
    private String Endereco;

    public Pessoa() {
    }

    public Pessoa(Integer idPessoa, String Nome, String Endereco) {
        this.idPessoa = idPessoa;
        this.Nome = Nome;
        this.Endereco = Endereco;
    }

    public Pessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    
    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

}
