package br.com.projetomedico.model;

public class Especialidade {

    private Integer idEspecialidade;
    private String nomeEspecialidade;

    public Especialidade() {
    }

    public Especialidade(Integer idEspecialidade, String nomeEspecialidade) {
        this.idEspecialidade = idEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public Especialidade(String especialidade) {
        this.nomeEspecialidade = especialidade;
    }

    public Especialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }
    
    

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

}
