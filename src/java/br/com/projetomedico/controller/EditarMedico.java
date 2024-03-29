package br.com.projetomedico.controller;

import br.com.projetomedico.DAO.GenericDAO;
import br.com.projetomedico.DAO.MedicoDAOImpl;
import br.com.projetomedico.model.Especialidade;
import br.com.projetomedico.model.Medico;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarMedico", urlPatterns = {"/EditarMedico"})
public class EditarMedico extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            Integer idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
            Integer idMedico = Integer.parseInt(request.getParameter("idMedico"));
            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            Integer crm = Integer.parseInt(request.getParameter("CRM"));
            Integer especialidade = Integer.parseInt(request.getParameter("especialidade"));

            String mensagem = null;

            Medico medico = new Medico();
            medico.setIdPessoa(idPessoa);
            medico.setIdMedico(idMedico);
            medico.setNome(nome);
            medico.setEndereco(endereco);
            medico.setCRM(crm);
            medico.setEspecialidade(new Especialidade(especialidade));

            try {
                GenericDAO dao = new MedicoDAOImpl();
                if (dao.alterar(medico)) {
                    mensagem = "Cadastro de médico alterado com sucesso!";
                } else {
                    mensagem = "Problemas ao editar cadastro de médico. "
                            + "Verifique os dados informados e tente novamente!";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("ListarMedico").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Problemas no Servlet ao editar médico! Erro: "
                        + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

