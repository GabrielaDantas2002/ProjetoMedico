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

        String sql = "select m.idmedico, p.nome, m.crm, p.endereco, p.idpessoa, e.nomeespecialidade from pessoa p \n"
                + "inner join medico m on p.idpessoa = m.idpessoa inner join especialidade e on m.idespecialidade = e.idespecialidade";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setIdMedico(rs.getInt("idMedico"));
                medico.setNome(rs.getString("Nome"));
                medico.setCRM(rs.getInt("CRM"));
                medico.setEndereco(rs.getString("Endereco"));
                medico.setEspecialidade(new Especialidade(rs.getString("nomeespecialidade")));
                medico.setIdPessoa(rs.getInt("idPessoa"));
                medicos.add(medico);
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao listar médicos! Erro:" + ex.getMessage());
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
        String sql = "delete from medico where idpessoa = ?; delete from pessoa where idpessoa = ?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idOject);
            stmt.setInt(2, idOject);
            stmt.executeUpdate();
            return true;
        } catch (Exception ex) {
            System.out.println("Problemas ao excluir o médico! Erro"
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
        Medico medico = null;
        

        String sql = "select p.idpessoa, m.idmedico, p.nome, p.endereco, m.crm, e.idespecialidade, "
                + "e.nomeespecialidade from pessoa p inner join medico m on p.idpessoa = m.idpessoa "
                + "inner join especialidade e on m.idespecialidade = e.idespecialidade "
                + "where p.idpessoa = ?";
        try {

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idObject);
            rs = stmt.executeQuery();

            while (rs.next()) {
                medico = new Medico();
                medico.setIdPessoa(rs.getInt("idpessoa"));
                medico.setIdMedico(rs.getInt("idmedico"));
                medico.setNome(rs.getString("nome"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setCRM(rs.getInt("crm"));
                medico.setEspecialidade(new Especialidade(rs.getInt("idespecialidade"),rs.getString("nomeespecialidade")));
                System.out.println("medico setado na DAO");
            }
        } catch (SQLException ex) {
            System.out.println("Problemas ao carregar médicos na DAO! Erro:" + ex.getMessage());
            ex.printStackTrace();

        } finally {
            try {
                ConnectionFactory.closeConnection(conn, stmt, rs);
            } catch (Exception e) {
                System.out.println("Problemas ao fechar a conexão! Erro" + e.getMessage());
                e.printStackTrace();
            }
        }
        return medico;
    }

    @Override
    public Boolean alterar(Object object) {
        Medico medico = (Medico) object;
        PreparedStatement stmt = null;
        String sql = "update medico set crm = ?, idespecialidade = ? where idpessoa = ?;";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, medico.getCRM());
            stmt.setInt(2, medico.getEspecialidade().getIdEspecialidade());
            stmt.setInt(3, medico.getIdPessoa());
            if (new PessoaDAOImpl().alterar(medico)){
                stmt.executeUpdate();
                return true;
            } else {
                return false;
            }
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
