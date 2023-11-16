<%-- 
    Document   : telaAdministrador
    Created on : 14 de nov. de 2023, 22:48:27
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
    <link rel="stylesheet" href="css/telaInicial.css">
    <title>Administrador Menu</title>
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
                    <a class="nav-link active" aria-current="page" href="telaAdministrador.html">Admin Menu</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="telaCriarUsuario.html">Criar Usuario</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="telaCriarConta.html">Criar Conta</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="telaAcessarConta.html">Acessar Conta</a>
                  </li>
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
            <img src="1.png" class="d-block w-50 mx-auto" width="250px" height="600px">
          </div>
          <div class="carousel-item" data-bs-interval="9000">
            <img src="2.png" class="d-block w-50 mx-auto" width="200px" height="600px">
          </div>
        </div>
      </div>

      <br><br>



</body>

</html>