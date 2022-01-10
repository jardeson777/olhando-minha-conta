<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%//@include file="Cabecalho.html" %>
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
                width: 100%;
                max-width: 500px;
                margin: auto;
                background: #fff;
                border-radius: 8px;
                height: fit-content;
            }
            
            header img {
                max-width: 100px;
            }
            
            img {
                max-width: 200px;
                margin: 0 auto;
            }
            
            h4 {
                color: #FF8906 !important;
            }
            
            .btn-primary {
                font-weight: bold !important;
            }
        </style>
    </head>
    <body>
        <%
            if(session.getAttribute("administrador") != null){
                String redirectURL = "indexAdm.jsp";
                response.sendRedirect(redirectURL);
            }
        %>
        <div class="container d-flex justify-content-center align-items-center">
            <div class="content">
                <header class="d-flex justify-content-center mt-4 w-100">
                    <a href="index.jsp">
                        <img src="assets/img/logo.png" alt="logo" class="w-100" />
                    </a>
                </header>
                <div class="mt-2">
                    <% 
                        Object erro = session.getAttribute("erro");
                    %>
                    <%if (erro == "sim"){%>
                    <div class="col-lg-8 ml-auto mr-auto mt-3 mb-3">
                        <div class="alert alert-danger" role="alert">
                            <h5>Não foi possível realizar o login. Tente novamente!</h5>
                        </div>
                    </div>
                    <%}%>

                    <div class="col-lg-6 mt-5 m-auto">
                        <h4>Login</h4>
                        <form method="POST" action="loginController" >
                            <div class="form-group">
                                <label for="CPF">CPF</label>
                                <input type="text" class="form-control cpf" name="cpf" required size="14" placeholder="CPF do usuário">
                            </div>
                            <div class="form-group">
                                <label for="Senha">Senha</label>
                                <input type="password" class="form-control"  name="senha" required size="5" placeholder="Senha do usuário">
                            </div>                    
                            <button type="submit" class="btn btn-primary d-block border-0 ml-auto">Logar</button>
                        </form>

                        <div class="m-auto">
                            <img src="assets/img/imageLogin.png" class="d-block" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
            
        <%
            session.setAttribute("erro", "nao");
        %>

        <%@include file="Scripts_basicos.html" %>
        
        <script>
		$(document).ready(function(){ 
		   $('.cpf').mask('000.000.000-00', {reverse: true});
		});
	</script>
        
    </body>
</html>