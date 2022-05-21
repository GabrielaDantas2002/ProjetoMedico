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

@WebServlet(name = "CadastrarMedico", urlPatterns = {"/CadastrarMedico"})
public class CadastrarMedico extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Especialidade especialidade = new Especialidade();

            String nome = request.getParameter("nome");
            String endereco = request.getParameter("endereco");
            Integer crm = Integer.parseInt(request.getParameter("crm"));
            especialidade.setIdEspecialidade(Integer.parseInt(request.getParameter("especialidade")));
            
            String mensagem = null;

            Medico medico = new Medico();
            medico.setNome(nome);
            medico.setEndereco(endereco);
            medico.setCRM(crm);
            medico.setEspecialidade(especialidade);

            try {
                GenericDAO dao = new MedicoDAOImpl();
                if (dao.cadastrar(medico)) {
                    mensagem = "Médico cadastrado com sucesso!";
                } else {
                    mensagem = "Problemas ao cadastrar médico. "
                            + "Verifique os dados informados e tente novamente!";
                }
                request.setAttribute("mensagem", mensagem);
                request.getRequestDispatcher("CarregarEspecialidade").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Problemas no Servlet ao cadastrar médico! Erro: "
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
