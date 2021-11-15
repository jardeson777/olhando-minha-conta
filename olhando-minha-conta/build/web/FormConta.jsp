<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%//@include file="Cabecalho.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <jsp:include page="Menu.jsp" />
            
            <div class="col-lg-6 mt-5">
                <h4>Incluir Conta</h4>
                <form method="POST" action="IncluirConta" >
                    <div class="form-group">
                        <input type="hidden" class="form-control" name="id_usuario" required size="11" maxlength="100" placeholder="ID do Usuário">
                    </div>
                    <div class="form-group">
                        <label for="Nome">Nome da Conta</label>
                        <input type="text" class="form-control cpf"  name="nome" required size="20" placeholder="Nome da conta">
                    </div>
                    <div class="form-group">
                        <label for="Banco">Banco</label>
                        <input type="text" class="form-control"  name="banco" required size="3" placeholder="Nº do banco">
                    </div>
                    <div class="form-group">
                        <label for="Agência">Agência</label>
                        <input type="text" class="form-control"  name="agencia" required size="6" placeholder="Nº da Agência">
                    </div>
                    <div class="form-group">
                        <label for="Conta_Corrente">Conta corrente</label>
                        <input type="text" class="form-control conta_corrente"  name="conta_corrente" required size="6" placeholder="Nº da Conta Corrente">
                    </div>                      
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
        </div>

        <%@include file="Scripts_basicos.html" %>

	<script>
		$(document).ready(function(){ 
		   $('.conta_corrente').mask('0000-0', {reverse: true});
		});
	</script>        
    </body>
</html>
