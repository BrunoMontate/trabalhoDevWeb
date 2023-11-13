/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Usuário
 */
@WebServlet(name = "acoesCliente", urlPatterns = {"/acoesCliente"})
public class acoesCliente extends HttpServlet {


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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Supondo que você já configurou a conexão com o banco de dados e tem o ID da conta disponível
        // Recupera o valor do idConta da sessão
        HttpSession session = request.getSession();
        int id_conta = (int) session.getAttribute("id_conta");

        // Obtendo a conexão do pool ou de onde você configurou
        Connection conexao = new Conexao().getConexao();

        // Criando uma instância do ContaDAO
        ContaDAO contaDAO = new ContaDAO(conexao);

        // Chamando o método verificarSaldo para obter as informações da conta
        Map<String, Double> contaInfo = contaDAO.verificarSaldo(id_conta);

        // Se contaInfo não for nulo, você pode passar as informações para o JSP
        if (contaInfo != null) {
           // Após obter as informações da conta
            request.setAttribute("saldo", contaInfo.get("saldo"));
            request.setAttribute("valInvestido", contaInfo.get("valInvestido"));
        } else {
            // Se conta não for encontrada, você pode configurar uma mensagem de erro, por exemplo
            request.setAttribute("mensagem", "Conta não encontrada");
        }

        // Encaminhando para o JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
