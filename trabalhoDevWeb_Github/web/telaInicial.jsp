<%-- 
    Document   : telaInicial
    Created on : 12 de nov. de 2023, 15:35:45
    Author     : Usuário
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>

<%
    int id_conta = (int) session.getAttribute("id_conta");
    // Recupere as informações da conta do atributo da requisição
    Map<String, Double> contaInfo = (Map<String, Double>) request.getAttribute("contaInfo");
    // Verifique se contaInfo não é nulo para evitar exceções
    if (contaInfo != null) {
%>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/telaInicial.css">
    <title>Menu Incial</title>
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
            <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
              <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasNavbarLabel">KONBANK</h5>
                <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
              </div>
              <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                  <li class="nav-item">
                    <a class="nav-link active" href="telaInicial.jsp">Informações da Conta</a>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                      Transações
                    </a>
                    <ul class="dropdown-menu">
                      <li><a class="dropdown-item" href="telaSaque.jsp">Saque</a></li>
                      <li><a class="dropdown-item" href="telaDeposito.jsp">Depósito</a></li>
                      <li><a class="dropdown-item" href="telaTransferencia.jsp">Transferência</a></li>
                      <li><a class="dropdown-item" href="telaInvestimento.jsp">Investimento</a></li>
                    </ul>
                  </li>
                   <li class="nav-item">
                    <a class="nav-link" href="index.html">Sair</a>
                  </li>
              </div>
            </div>
          </div>
        </nav>
      </header>
      
      <br><br><br>

      <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner">
          <div class="carousel-item active " data-bs-interval="9000">
            <img src="imagens/1.png" class="d-block w-50 mx-auto" width="250px" height="600px">
          </div>
          <div class="carousel-item" data-bs-interval="9000">
            <img src="imagens/2.png" class="d-block w-50 mx-auto" width="200px" height="600px">
          </div>
        </div>
      </div>

      <br><br>

    <div class="d-flex justify-content-center">
        <div class="card text-white bg-secondary mb-3" style="width: 18rem;">
            <div class="card-header text-center" id="ocultarSaldoTitulo">
                Seu Saldo
            </div>
            <ul class="list-group list-group-flush">
<li class="list-group-item text-center" id="saldo">R$ <%= contaInfo.get("saldo") %></li>

            </ul>
        </div>
    </div>
<%
    } else {
        response.sendRedirect("index.html");
    }
%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <script>
        const saldo = document.getElementById("saldo");
        const ocultarSaldoTitulo = document.getElementById("ocultarSaldoTitulo");
        let saldoVisivel = true;

        ocultarSaldoTitulo.addEventListener("click", () => {
            if (saldoVisivel) {
                saldo.style.display = "none";
            } else {
                saldo.style.display = "block";
            }
            saldoVisivel = !saldoVisivel;
        });
    </script>
</body>

</html>