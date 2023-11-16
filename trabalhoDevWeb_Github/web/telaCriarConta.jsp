<%-- 
    Document   : telaCriarConta
    Created on : 14 de nov. de 2023, 22:49:09
    Author     : Usuário
--%>

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
    <title>Criação de Conta </title>
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
                            <a class="nav-link active" href="telaCriarConta.jsp">Criar Conta</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="telaAcessarConta.jsp">Consulta Simples</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="telaConsultarTransacoes.jsp">Consulta Detalhada</a>
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
            <legend class="titleInfo text-center">Criar Conta</legend>
            <div class="col-md-12 justify-content-center d-flex">
                <form class="col-md-8" action="acoesAdminCriarConta" method="post">
                    <div class="form-group my-5">
                        <label class="text-white" for="exampleInputName">ID Usuário</label>
                        <input type="text" class="form-control" name="id_user" id="exampleInputName" >
                        <label class="text-white" for="exampleInputName">Valor Inicial</label>
                        <input type="text" class="form-control" name="valInicial" id="exampleInputName" >
                        <label class="text-white" for="exampleInputName">Valor de Investimento Inicial</label>
                        <input type="text" class="form-control" name="valInicialInvestido" id="exampleInputName" >
                    </div>
                    <button type="submit" class="btn btn-primary">Criar</button>
                  </form>

            </div>
        </fieldset>
                        
         <% 
            Object id_contaNova = request.getAttribute("id_contaNova");
            if (id_contaNova != null) { 
         %>
         <p class="text-white text-center">
            Nova conta criada com sucesso.<br>Salve o ID da conta caso seja necessário alguma verificação: <%= id_contaNova %>
        </p>
    <% } %>
    </div>
    <br><br>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

</html>


telaCriarConta