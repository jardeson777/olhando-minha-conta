<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aplicacao.Usuario"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Menu usuario</title>
        
        <style>
            body {
                background: #f25f4c !important;
                min-height: 100vh;
                margin: 0;
            }
            div.container {
                min-height: 100vh;
            }
            
            div.content {
                max-width: 500px;
                margin: auto;
                background: #fff;
                border-radius: 8px;
                height: fit-content;
            }
            
            header img {
                max-width: 100px;
            }
            
            h3 {
                font-size: 14px !important;
            }
            
            h3 span {
                font-size: 20px !important;
                color: #ff8906;
            }
            
            main {
                min-height: 60vh;
            }
            
            nav{
                width: 100%;
                max-width: 200px;
            }
            
            main img {
                max-width: 400px;
            }
            
            .sair{
                border: none;
                background: none;
                position: absolute;
                top: 40px;
                right: 80px;
                outline: none !important;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <%
            Usuario dado = (Usuario)session.getAttribute("usuario");
            
            if(dado == null){
                String redirectURL = "FormLogin.jsp";
                response.sendRedirect(redirectURL);
            }
        %> 
        <div class="container d-flex justify-content-center align-items-center">
            <div class="content mx-1">
                <header class="d-flex align-items-center my-2 mx-5">
                    <a href="loginController?acao=sair" class="sair text-white">Sair da conta</a>
                    <a href="index.jsp">
                        <img src="assets/img/logo.png" alt="logo" class="w-100" />
                    </a>
                    <h3 class="text-center w-100">Seja bem-vindo, <span class="text-capitalize"><%= dado.getNome() %></span></h3>
                </header>

                <main class="w-100 d-flex justify-content-center align-items-center flex-column">
                    <nav class="flex-column d-flex">
                        <a href="contaController?acao=mostrar"><button class="btn btn-primary w-100 m-2">Contas</button></a>
                        <a href="lancamentoController?acao=mostrar"><button class="btn btn-primary w-100 m-2">Lançamentos</button></a>
                    </nav>
                    <img src="./assets/img/backgroundMenuMain.png" class="w-100 ml-4" />
                </main>
            </div>
        </div>
                
        <%@include file="Scripts_basicos.html" %>
    </body>
</html>

