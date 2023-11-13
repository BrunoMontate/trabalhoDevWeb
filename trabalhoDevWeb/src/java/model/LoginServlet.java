package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        Connection conexao = new Conexao().getConexao();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);

        Map<String, Object> credenciais = usuarioDAO.verificarCredenciais(usuario, senha);

        if (credenciais != null) {
            String tipoUsuario = (String) credenciais.get("tipo_usuario");
            if ("1".equals(tipoUsuario)) {
                response.sendRedirect("telaSaque.jsp");
                HttpSession session = request.getSession();
                session.setAttribute("id_conta", 0);
                
            } else if ("2".equals(tipoUsuario)) {
                
                int idConta = (int) credenciais.get("id_conta");

                HttpSession session = request.getSession();
                session.setAttribute("id_conta", idConta);

                response.sendRedirect("telaInicial.jsp");
            }
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
            dispatcher.forward(request, response);
        }
    }
}
