package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AdminstradorDAO;
import model.Conexao;

/**
 *
 * @author Usuário
 */
@WebServlet(urlPatterns = {"/acoesAdminConsultaSimples"})
public class acoesAdminConsultaSimples extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        Connection conexao = new Conexao().getConexao();
        String idConta = request.getParameter("id_conta");
        
        if (idConta != null) {
            try {
                int id_conta = Integer.parseInt(idConta);

                Map<String, Double> consultaSimples = AdminstradorDAO.consultaSimples(conexao, id_conta);

                // Define os resultados como atributos de solicitação
                request.setAttribute("saldo", consultaSimples.get("saldo"));
                request.setAttribute("valorInvestido", consultaSimples.get("valorInvestido"));

                // Encaminha a solicitação para o JSP
                RequestDispatcher dispatcher = request.getRequestDispatcher("telaAcessarConta.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                // Trata erros na conversão do id_conta para int
                response.getWriter().println("ID da conta inválido.");
            } catch (IOException | ServletException e) {
                // Trata outros erros
                response.getWriter().println("Erro ao consultar conta: " + e.getMessage());
            }
        } else {
            response.getWriter().println("Parâmetro 'id_conta' não fornecido.");
        }
    }
}

