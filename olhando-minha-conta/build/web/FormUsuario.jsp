<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="aplicacao.Usuario"%>
<!DOCTYPE html>
<html>
    <head>
        <%//@include file="Cabecalho.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <jsp:include page="MenuAdm.jsp" />
            
            <%
               Usuario aux = (Usuario)request.getAttribute("usuario");
            %>             
            
            <div class="col-lg-6 mt-5">
                <h4>Incluir usuário</h4>
                <form method="POST" action="administradorController" >
                    <input name="action" hidden value="CriarUsuario"/>
                    <input type="hidden" class="form-control" name="id" value="<%= aux.getId() %>">
                    <div class="form-group">
                        <label for="Nome">Nome</label>
                        <input type="text" class="form-control" name="nome" value="<%= aux.getNome() %>" required size="20" maxlength="100" placeholder="Nome do usuário">
                    </div>
                    <div class="form-group">
                        <label for="CPF">CPF</label>
                        <input type="text" class="form-control cpf"  name="cpf" value="<%= aux.getCpf() %>" required size="14" placeholder="CPF do usuário">
                    </div>
                    <div class="form-group">
                        <label for="Senha">Senha</label>
                        <input type="password" class="form-control"  name="senha" value="<%= aux.getSenha() %>" required size="5" placeholder="Senha do usuário">
                    </div>
                    <div class="form-group">
                        <label for="Suspenso">Suspenso</label>
                        <select class="form-control" name="suspenso" value="<%= aux.getSuspenso() %>">
                            <option value="" selected>Status do usuário</option>
                            <option value="S">Sim</option>
                            <option value="N">Não</option>
                        </select>
                    </div>                      
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
        </div>

        <%@include file="Scripts_basicos.html" %>

	<script>
		$(document).ready(function(){ 
		   $('.cpf').mask('000.000.000-00', {reverse: true});
		});
	</script>        
    </body>
</html>
