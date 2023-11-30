<%-- 
    Document   : telaAcessoDeConta
    Created on : 14 de nov. de 2023, 22:47:34
    Author     : Usuário
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/telaInfo.css">
    <title>Acessar Conta </title>
</head>

<body style="background-color: rgb(22, 22, 22);">

    <header>
        <nav class="navbar-expand-lg navbar bg-body-tertiary fixed-top" data-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">KONBANK</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar"
                    aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar"
                    aria-labelledby="offcanvasNavbarLabel">
                    <div class="offcanvas-header">
                        <h5 class="offcanvas-title" id="offcanvasNavbarLabel">KONBANK</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="offcanvas"
                            aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="telaCriarUsuario.jsp">Criar Usuario</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="telaCriarConta.jsp">Criar Conta</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="telaAcessarConta.jsp">Consulta Simples</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="telaConsultarTransacoes.jsp">Consulta Detalhada</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="telaVerificaClientes.jsp">Consulta Clientes</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="index.html">Sair</a>
                          </li>
                          </li>
                      </div>
                </div>
            </div>
        </nav>
    </header>

    <br><br><br>

    <div class="container-fluid TI">
        <fieldset class="d-flex flex-column justify-content-center">
            <legend class="titleInfo text-center">Consulta Detalhada</legend>
            <div class="col-md-12 justify-content-center d-flex">
                <form class="col-md-8" action="acoesAdminConsultaTrasacoes" method="post">
                    <div class="form-group my-5">
                        <label class="text-white" for="exampleInputName">Id da Conta</label>
                        <input type="text" class="form-control" name="id_conta" id="exampleInputName" required>
                    </div>
                    <button type="submit" class="btn btn-primary" >Acessar</button>
                  </form>


            </div>
        </fieldset>
       
        <br>         
<%
    Object transacoes = request.getAttribute("transacoes");
    if (transacoes != null) {
        List<Map<String, Object>> listaTransacoes = (List<Map<String, Object>>) transacoes;
%>
    <div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Transação</th>
                        <th>Valor</th>
                        <th>Data e Hora</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Map<String, Object> transacao : listaTransacoes) { %>
                        <tr>
                            <td>
                                <% 
                                    String tipoTransacao = transacao.get("tipo_transacao").toString();
                                    if (tipoTransacao.equals("1")) {
                                        out.print("Saque");
                                    } else if (tipoTransacao.equals("2")) {
                                        out.print("Depósito");
                                    } else if (tipoTransacao.equals("3")) {
                                        out.print("Transferência Feita");
                                    } else if (tipoTransacao.equals("4")) {
                                        out.print("Investimento");
                                    }
                                %>
                            </td>
                            <td>R$<%= transacao.get("valor_transacao") %></td>
                            <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(transacao.get("data_transacao")) %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<% } %>

        
    </div>
    <br><br>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

</html>