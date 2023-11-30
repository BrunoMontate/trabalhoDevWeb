/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import model.UsuarioDAO;
import java.util.Map;

/**
 *
 * @author Hug_d
 */
@WebServlet(name = "acoesusuario", urlPatterns = {"/acoesusuario"})
public class acoesusuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // O doGet está vazio, mas você pode adicionar lógica conforme necessário
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "depositar" -> {
                String id_conta = request.getParameter("id_conta");
                double valor = Double.parseDouble(request.getParameter("valor"));
                
                // Verifica se o valor é maior que 0 antes de realizar o depósito
                if (valor > 0) {
                    UsuarioDAO.realizarDeposito(id_conta, valor);
                    request.setAttribute("mensagem", "Depósito realizado com sucesso.");
                    HttpSession session = request.getSession();
                    double atualSessao = (double) session.getAttribute("saldo");
                    double novoSaldo = atualSessao + valor;
                    session.setAttribute("saldo", novoSaldo);
                } else {
                    request.setAttribute("mensagem", "O valor deve ser maior que R$ 0,00.");
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("telaDeposito.jsp");
                dispatcher.forward(request, response);
            }

            case "investir" -> {
                int idConta = Integer.parseInt(request.getParameter("id_conta"));
                double valor = Double.parseDouble(request.getParameter("valor"));
                String mensagem;

            // Obtém o saldo da conta
                Map<String, Double> informacoesConta = UsuarioDAO.verificarSaldo(idConta);
                double saldoAtual = informacoesConta.get("saldo");

            // Verifica se há saldo suficiente para o investimento
                if (saldoAtual >= valor) {
                // Realiza o investimento
                    UsuarioDAO.realizarInvestimento(Integer.toString(idConta), Double.toString(valor));

                    mensagem = "Investimento realizado com sucesso!";

                // Atualiza saldo e valor investido na sessão de forma atômica
                    HttpSession session = request.getSession();
                    synchronized (session) {
                        double saldoAtualNaSessao = (double) session.getAttribute("saldo");
                        double valInvestido = (double) session.getAttribute("valInvestido");

                        double novoSaldo = saldoAtualNaSessao - valor;
                        double novoInvestido = valInvestido + valor;

                        session.setAttribute("saldo", novoSaldo);
                        session.setAttribute("valInvestido", novoInvestido);
                    }
                } else {
                    mensagem = "Saldo insuficiente para realizar investimento.";
            }

            // Responder de volta à página
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("telaInvestimento.jsp");
            dispatcher.forward(request, response);
            }

            case "sacar" -> {
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

                    // Atualiza saldo na sessão de forma atômica
                    HttpSession session = request.getSession();
                    synchronized (session) {
                        double atualSessao = (double) session.getAttribute("saldo");
                        double novoSaldo = atualSessao - valorSaque;
                        session.setAttribute("saldo", novoSaldo);
                    }
                } else {
                    mensagem = "Saldo insuficiente para realizar o saque.";
                }

                // Armazena a mensagem no objeto request
                request.setAttribute("mensagem", mensagem);

                // Encaminha a requisição para a página JSP
                request.getRequestDispatcher("telaSaque.jsp").forward(request, response);
            }

            case "transferir" -> {
                int idConta_origem = Integer.parseInt(request.getParameter("id_conta_origem"));
                int idConta_destino = Integer.parseInt(request.getParameter("id_conta_destino"));
                double valor = Double.parseDouble(request.getParameter("valor"));
                String mensagem;

                if (UsuarioDAO.verificarExistenciaConta(Integer.toString(idConta_destino)) != 0) {
                    Map<String, Double> informacoesConta = UsuarioDAO.verificarSaldo(idConta_origem);
                    double saldoAtual = informacoesConta.get("saldo");

                    if (saldoAtual >= valor) {
                        UsuarioDAO.realizarTransferencia(Integer.toString(idConta_origem), Integer.toString(idConta_destino), Double.toString(valor));
                        mensagem = "Transferência realizada com sucesso!";

                        // Atualiza saldo na sessão de forma atômica
                        HttpSession session = request.getSession();
                        synchronized (session) {
                            double atualSessao = (double) session.getAttribute("saldo");
                            double novoSaldo = atualSessao - valor;
                            session.setAttribute("saldo", novoSaldo);
                        }
                    } else {
                        mensagem = "Saldo insuficiente para realizar a transferência.";
                    }

                } else {
                    mensagem = "Conta Destino não existente. A Transferência não foi realizada!";
                }

                // Armazena a mensagem no objeto request
                request.setAttribute("mensagem", mensagem);

                // Encaminha a requisição para a página JSP
                request.getRequestDispatcher("telaTransferencia.jsp").forward(request, response);
            }

            default -> // Caso a ação não seja reconhecida
                response.getWriter().println("Ação não reconhecida: " + acao);
    }
        // Adicione lógica para a ação investir
        // Adicione lógica para a ação sacar
        // Adicione lógica para a ação transferir
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
