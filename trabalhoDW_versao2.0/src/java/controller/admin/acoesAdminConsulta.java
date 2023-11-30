/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.Map;
import java.util.List;
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
 * @author Hug_d
 */
@WebServlet(urlPatterns = {"/acoesAdminConsulta"})
public class acoesAdminConsulta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Lógica para o método GET, se necessário
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "consultasimples" -> {
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
                break;
            }

            case "consultatransacoes" -> {
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
                    response.getWriter().println("Erro ao processar a requisição: " + e.getMessage());
                }
            }

            default -> response.getWriter().println("Ação não reconhecida: " + acao);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
