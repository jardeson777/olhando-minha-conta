<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aplicacao.Categoria"%>
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
        <div class="container mt-2">

            <div class="content">
                <%
                   Categoria aux = (Categoria)request.getAttribute("categoria");
                %>             

                <h4>Incluir Categoria</h4>
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
                    <input hidden value="CriaCategoria" name="action"/>
                    <input type="hidden" class="form-control" name="<%if(aux != null){out.print("id");}%>" value="<%if(aux != null){out.print(aux.getId());} %>">
                    <div class="form-group">
                        <label for="Descrição">Descrição</label>
                        <input type="text" class="form-control" name="descricao" value="<%if(aux != null){out.print(aux.getDescricao());} %>" required size="20" maxlength="100" placeholder="Descrição da categoria">
                    </div>
                    <button type="button" class="btn btn-secondary">Voltar</button>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
        </div>

        <%@include file="Scripts_basicos.html" %>
        
	<script>  
            $('.btn-secondary').click(() => {
                window.location.href = "categoriaController?acao=mostrar";
            });                 
	</script>         
    </body>
</html>
