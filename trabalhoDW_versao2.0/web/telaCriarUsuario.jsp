<%-- 
    Document   : telaCriarUsuario
    Created on : 14 de nov. de 2023, 22:49:58
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
    <title>Criação de Usuario </title>
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
                            <a class="nav-link active" aria-current="page" href="telaCriarUsuario.jsp">Criar Usuario</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" href="telaCriarConta.jsp">Criar Conta</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="telaAcessarConta.jsp">Consulta Simples</a>
                          </li>
                          <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="telaConsultarTransacoes.jsp">Consulta Detalhada</a>
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
        <fieldset class="d-flex flex-column align-items-center justify-content-center">
            <legend class="titleInfo text-center">Criar Usuario</legend>
            <br>
            <div class="col-md-12 justify-content-center d-flex">
                <form class="col-md-8" action="acoesAdmin" method="post">
                    <div class="form-group my-2">
                        <label class="text-white" for="exampleInputName">CPF</label>
                        <input type="text" class="form-control" name="cpf" id="exampleInputName"  required>
                    </div>
                    <div class="form-group my-2">
                      <label class="text-white" for="exampleInputName">Nome</label>
                      <input type="text" class="form-control" name="nome" id="exampleInputName"  required>
                    </div>
                    <div class="form-group my-2">
                        <label class="text-white" for="exampleInputPassword1">Senha</label>
                        <input type="password" class="form-control" name="senha" id="exampleInputPassword1"  required>
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" type="radio" name="tipo" id="flexRadioDefault1" value="1" required>
                        <label class="text-white" class="form-check-label" name="tipo" for="flexRadioDefault1">
                          Administrador
                        </label>
                      </div>
                      <div class="form-check">
                        <input class="form-check-input" type="radio" name="tipo" id="flexRadioDefault2" value="2" checked required>
                        <label class="text-white" class="form-check-label" for="flexRadioDefault2" v>
                          Cliente
                        </label>
                      </div>
                    <br>
                    <button type="submit" class="btn btn-primary" >Criar</button>
                  </form>
            </div>
        </fieldset>
    
        <% 
            Object idUsuarioNovo = request.getAttribute("id_usuarioNovo");
            if (idUsuarioNovo != null) { 
         %>
         <p class="text-white">
            Novo usuário criado com sucesso.<br>Salve o ID do usuário caso seja necessário a criação de uma conta: <%= idUsuarioNovo %><br> Caso seja um novo cliente, vá para aba "Criar Conta"
        </p>
    <% } %>

    </div>
    <br><br>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>

</html>