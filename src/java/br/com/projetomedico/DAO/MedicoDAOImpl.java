package br.com.projetomedico.DAO;

import br.com.projetomedico.model.Especialidade;
import br.com.projetomedico.model.Medico;
import br.com.projetomedico.model.Pessoa;
import br.com.projetomedico.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAOImpl implements GenericDAO {

    private Connection conn;

    public MedicoDAOImpl() throws Exception {
        try {
            this.conn = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso! medico");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object object) {

        Medico medico = (Medico) object;
        PreparedStatement stmt = null;

        String sql = "insert into medico(crm, idpessoa, idespecialidade) values (?, ?, ?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, medico.getCRM());
            stmt.setInt(2, new PessoaDAOImpl().cadastrar(medico));
            stmt.setInt(3, medico.getEspecialidade().getIdEspecialidade());
            stmt.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao cadastrar médico! Erro: "
                    + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception ex) {
                System.out.println("Problemas ao fechar a conexão! Erro: "
                        + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Object> listar() {

        List<Object> medicos = new ArrayList();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select * from pessoa p inner join medico m on p.idpessoa = m.idpessoa inner join especialidade e on m.idespecialidade = e.idespecialidade";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdMedico(rs.getInt("idmedico"));
                medico.setNome(rs.getString("nome"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setEspecialidade(new Especialidade(rs.getString("nomeespecialidade")));
                medicos.add(medico);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar produtos! Erro:" + ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão! Erro" + e.getMessage());
                e.printStackTrace();
            }
        }
        return medicos;
    }

    @Override
     public Boolean excluir(int idOject) {
        PreparedStatement stmt = null;
        String sql = "delete from pessoa where idpessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idOject);
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o produto! Erro" 
                    + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão! Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public Object carregar(int idObject) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pessoa pessoa = null;
        
        String sql = "select * from pessoa where idpessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();
            while (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("idpessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setEndereco(rs.getString("endereco"));
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar pessoa! Erro: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão! Erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return pessoa;
    }

    @Override
    public Boolean alterar(Object object) {
        Pessoa pessoa = (Pessoa) object;
        PreparedStatement stmt = null;
        String sql = "update pessoa set nome = ?, endereco = ? where idpessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, pessoa.getNome());
            stmt.setString(2, pessoa.getEndereco());
            stmt.setInt(5, pessoa.getIdPessoa());
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao alterar Pessoa! Erro: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão! Erro: "
                        + e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
