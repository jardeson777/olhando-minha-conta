<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%//@include file="Cabecalho.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <jsp:include page="Menu.jsp" />
                
            <% 
                Object erro = session.getAttribute("erro");
            %>
            <%if (erro == "sim"){%>
            <div class="col-lg-8 mt-5">
                <div class="alert alert-danger" role="alert">
                    <h5>Não foi possível realizar o login. Tente novamente!</h5>
                </div>
            </div>
            <%}%>

            <div class="col-lg-6 mt-5">
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
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
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