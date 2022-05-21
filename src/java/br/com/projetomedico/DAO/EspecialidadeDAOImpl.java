package br.com.projetomedico.DAO;

import br.com.projetomedico.model.Especialidade;
import br.com.projetomedico.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EspecialidadeDAOImpl implements GenericDAO{
    
    private Connection conn;
    
    public EspecialidadeDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso!");
        }catch (Exception e){
            throw new Exception (e.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> listar() {
        List<Object> especialidades = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select idespecialidade, nomeespecialidade from especialidade order by nomeespecialidade";
        
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Especialidade especialidade = new Especialidade();
                
                especialidade.setIdEspecialidade(rs.getInt("idespecialidade"));
                especialidade.setNomeEspecialidade(rs.getString("nomeespecialidade"));
                
                especialidades.add(especialidade);
            }
        } catch (Exception e) {
            System.out.println("Problemas ao listar especialidade! Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception e){
                System.out.println("Problemas ao fechar conexão! Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return especialidades;
    }

    @Override
    public Boolean excluir(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object carregar(int idObject) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Boolean alterar(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
