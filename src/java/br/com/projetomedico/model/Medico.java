package br.com.projetomedico.model;

public class Medico extends Pessoa {

    private Integer idMedico;
    private Integer CRM;
    private Especialidade especialidade;

    public Medico() {
    }
    
    

    public Medico(Integer idMedico, Integer CRM, Especialidade especialidade) {
        this.idMedico = idMedico;
        this.CRM = CRM;
        this.especialidade = especialidade;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public Integer getCRM() {
        return CRM;
    }

    public void setCRM(Integer CRM) {
        this.CRM = CRM;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    

}
