package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuário
 */
@WebServlet(urlPatterns = {"/usuarioSaque"})
public class usuarioSaque extends HttpServlet {

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
        int idConta = Integer.parseInt(request.getParameter("id_conta"));
        double valorSaque = Double.parseDouble(request.getParameter("valor"));
        String mensagem;
        // Obtém o saldo da conta
        Map<String, Double> informacoesConta = UsuarioDAO.verificarSaldo(idConta);
        double saldoAtual = informacoesConta.get("saldo");

        // Verifica se há saldo suficiente para o saque
        if (saldoAtual >= valorSaque) {
        // Realiza o saque
            UsuarioDAO.realizarSaque(Integer.toString(idConta), Double.toString(valorSaque));
            mensagem = "Saque realizado com sucesso!";
            HttpSession session = request.getSession();
            double atualSessao = (double) session.getAttribute("saldo");
            double novoSaldo = atualSessao - valorSaque;
            session.setAttribute("saldo", novoSaldo);
        } else {
            mensagem = "Saldo insuficiente para realizar o saque.";
        }

    // Armazena a mensagem no objeto request
    request.setAttribute("mensagem", mensagem);

    // Encaminha a requisição para a página JSP
    request.getRequestDispatcher("telaSaque.jsp").forward(request, response);
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
