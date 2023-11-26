/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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
@WebServlet(name = "usuarioTransferencia", urlPatterns = {"/usuarioTransferencia"})
public class usuarioTransferencia extends HttpServlet {

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
        int idConta_origem = Integer.parseInt(request.getParameter("id_conta_origem"));
        int idConta_destino = Integer.parseInt(request.getParameter("id_conta_destino"));
        double valor = Double.parseDouble(request.getParameter("valor"));
        String mensagem;
        if (UsuarioDAO.verificarExistenciaConta(Integer.toString(idConta_destino))!= 0) {
            Map<String, Double> informacoesConta = UsuarioDAO.verificarSaldo(idConta_origem);
            double saldoAtual = informacoesConta.get("saldo");
                    if (saldoAtual >= valor) {
                        UsuarioDAO.realizarTransferencia(Integer.toString(idConta_origem),Integer.toString(idConta_destino), Double.toString(valor));
                        mensagem = "Transferência realizada com sucesso!";
                        HttpSession session = request.getSession();
                        double atualSessao = (double) session.getAttribute("saldo");
                        double novoSaldo = atualSessao - valor;
                        session.setAttribute("saldo", novoSaldo);
        } else {
            mensagem = "Saldo insuficiente para realizar a tranferência.";
        }
            
        } else {
            mensagem = "Conta Destino não existente. A Transferência não foi realizada!";
        }
        request.setAttribute("mensagem", mensagem);

    // Encaminha a requisição para a página JSP
        request.getRequestDispatcher("telaTransferencia.jsp").forward(request, response);
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
