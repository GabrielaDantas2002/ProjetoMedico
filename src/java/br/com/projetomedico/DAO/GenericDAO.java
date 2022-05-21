package br.com.projetomedico.DAO;

import java.util.List;

public interface GenericDAO {
    public Boolean cadastrar(Object object);
    public List<Object> listar();
    public Boolean excluir(int idObject);
    public Object carregar(int idObject);
    public Boolean alterar(Object object);
}
