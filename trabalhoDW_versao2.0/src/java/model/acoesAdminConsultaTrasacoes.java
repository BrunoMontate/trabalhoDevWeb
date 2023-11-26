/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package model;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
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
@WebServlet(name = "acoesAdminConsultaTrasacoes", urlPatterns = {"/acoesAdminConsultaTrasacoes"})
public class acoesAdminConsultaTrasacoes extends HttpServlet {

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

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtém a conexão com o banco de dados (você precisa implementar a lógica para isso)
        Connection conexao = new Conexao().getConexao();

        String idConta = request.getParameter("id_conta");

        try {
            if (idConta != null) {
                int id_conta = Integer.parseInt(idConta);
                List<Map<String, Object>> transacoes = AdminstradorDAO.consultarTransacoes(conexao, id_conta);

                // Define os resultados como atributos da requisição
                request.setAttribute("transacoes", transacoes);

                // Encaminha para o JSP de visualização
                RequestDispatcher dispatcher = request.getRequestDispatcher("telaConsultarTransacoes.jsp");
                dispatcher.forward(request, response);
            } else {
                // Lógica para lidar com o caso em que id_conta é nulo
                // Você pode redirecionar o usuário ou exibir uma mensagem de erro
                response.getWriter().println("Parâmetro 'id_conta' não encontrado.");
            }
        } catch (NumberFormatException e) {
            // Trata exceção se a conversão de String para int falhar
            // Isso imprime a exceção no console (pode ser ajustado conforme necessário)
            // Adicione lógica adicional de tratamento de erro, se necessário
            
        }
    }
}
