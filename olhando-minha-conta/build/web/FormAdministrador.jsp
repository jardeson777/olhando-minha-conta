<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aplicacao.Administrador"%>
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
                display: flex;
                justify-content: center;
                align-items: center;
            }
            
            div.content {
                width: 100%;
                max-width: 500px;
                margin: auto;
                background: #fff;
                border-radius: 8px;
                height: fit-content;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <%
            Administrador dado = (Administrador)session.getAttribute("administrador");
            
            if(dado == null){
                String redirectURL = "FormLogin.jsp";
                response.sendRedirect(redirectURL);
            }
        %>
        <div class="container">

            <div class="content">
                <%
                   Administrador aux = (Administrador)request.getAttribute("administrador");
                %>            

                <h4><% if(!(aux.getId() > 0)){out.println("Incluir Administrador");} else {out.println("Editar Administrador");}%></h4>
                <form method="POST" action="administradorController" >
                    <% 
                        Object erro = session.getAttribute("erro");
                    %>
                    <%if (erro == "sim"){%>
                    <div class="col-lg-10 ml-auto mr-auto mt-3 mb-3">
                        <div class="alert alert-danger" role="alert">
                            <h5>Dados inválidos!</h5>
                        </div>
                    </div>
                    <%}%>
                    
                    <input hidden value="CriarAdministrador" name="action"/>
                    <input type="hidden" class="form-control" name="<%if(aux != null){out.print("id");}%>" value="<%if(aux != null){out.print(aux.getId());} %>">
                    <div class="form-group">
                        <label for="Nome">Nome</label>
                        <input type="text" class="form-control" name="nome" value="<%if(aux == null){out.print("");}else{ out.print(aux.getNome());} %>" required size="20" maxlength="100" placeholder="Nome do administrador">
                    </div>
                    <div class="form-group">
                        <label for="CPF">CPF</label>
                        <input type="text" class="form-control cpf"  name="cpf" value="<%if(aux == null){out.print("");}else{ out.print(aux.getCpf());} %>" required size="14" placeholder="CPF do administrador">
                    </div>
                    <div class="form-group">
                        <label for="Senha">Senha</label>
                        <input type="password" class="form-control"  name="senha" value="<%if(aux == null){out.print("");}else{ out.print(aux.getSenha());} %>" required size="5" placeholder="Senha do administrador">
                    </div>       
                    <button type="button" class="btn btn-secondary">Voltar</button>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                    <%
                        session.setAttribute("erro", "nao");
                    %>
                </form>
            </div>
        </div>

        <%@include file="Scripts_basicos.html" %>

	<script>
            $(document).ready(function(){ 
               $('.cpf').mask('000.000.000-00', {reverse: true});
            });
                
            $('.btn-secondary').click(() => {
                window.location.href = "administradorController?acao=mostrar";
            });
	</script>        
    </body>
</html>