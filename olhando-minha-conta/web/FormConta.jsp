<%@page import="aplicacao.Usuario"%>
<%@page import="aplicacao.Conta"%>
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
            Usuario dado = (Usuario)session.getAttribute("usuario");
            
            if(dado == null){
                String redirectURL = "FormLogin.jsp";
                response.sendRedirect(redirectURL);
            }
        %>
        <div class="container">

            <div class="content">
                <%
                   Conta aux = (Conta)request.getAttribute("conta");
                %>            

                <h4><% if(aux == null){out.println("Incluir Conta");} else {out.println("Editar Conta");}%></h4>
                <form method="POST" action="usuarioController" >
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
                    
                    <input hidden value="CriarConta" name="action"/>
                    <input type="hidden" class="form-control" name="<%if(aux != null){out.print("id");}%>" value="<%if(aux != null){out.print(aux.getId());} %>">
                    <input type="hidden" class="form-control" name="id_usuario" value="<%if(dado != null){out.print(dado.getId());} %>" required size="20" maxlength="100" placeholder="Id do Usuário">
                    <div class="form-group">
                        <label for="Nome_da_Conta">Nome da Conta</label>
                        <input type="text" class="form-control"  name="nome_conta" value="<%if(aux == null){out.print("");}else{ out.print(aux.getNomeConta());} %>" required size="20" maxlength="100" placeholder="Nome da conta">
                    </div>
                    <div class="form-group">
                        <label for="Banco">Banco</label>
                        <input type="text" class="form-control"  name="banco" value="<%if(aux == null){out.print("");}else{ out.print(aux.getBanco());} %>" required size="3" placeholder="Banco">
                    </div>
                    <div class="form-group">
                        <label for="Agencia">Agencia</label>
                        <input type="text" class="form-control"  name="agencia" value="<%if(aux == null){out.print("");}else{ out.print(aux.getAgencia());} %>" required size="6" placeholder="Agencia">
                    </div>
                    <div class="form-group">
                        <label for="Conta Corrente">Conta Corrente</label>
                        <input type="text" class="form-control conta_corrente"  name="conta_corrente" value="<%if(aux == null){out.print("");}else{ out.print(aux.getContaCorrente());} %>" required size="6" placeholder="Conta Corrente">
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
		$('.conta_corrente').mask('0000-0', {reverse: true});
            });
                
            $('.btn-secondary').click(() => {
                window.location.href = "contaController?acao=mostrar";
            });                
	</script>        
    </body>
</html>
