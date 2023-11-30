package filter;
/* Não funcionando
import java.io.IOException;
import java.sql.Connection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse; // Add this import

import model.Conexao;
import model.UsuarioDAO;

@WebFilter(filterName = "filtro", urlPatterns = {"/telaVerificaClientes.jsp", 
    "/telaCriarConta.jsp",
    "/telaCriarUsuario.jsp",
    "/telaConsultarTransacoes.jsp",
    "/telaAcessarConta.jsp"})

public class filtro implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        Connection conexao = new Conexao().getConexao();
        UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
        
        Map<String, Object> credenciais = usuarioDAO.verificarCredenciais(usuario, senha);
         // Check if credenciais is null
        if (credenciais == null) {
            ((HttpServletResponse) response).sendRedirect("http://localhost:8080/trabalhoDevWeb/index.html");
            return;
        }
        String tipoUsuario = (String) credenciais.get("tipo_usuario");

        if ("1".equals(tipoUsuario) || tipoUsuario != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect("http://localhost:8080/trabalhoDevWeb/index.html");
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
*/