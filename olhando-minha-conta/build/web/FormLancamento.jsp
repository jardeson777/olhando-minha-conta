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
                <h4>Incluir Lançamento</h4>
                <form method="POST" action="usuarioController" >
                    <input hidden value="CriarLancamento" name="action"/>
                    <div class="form-group">
                        <label for="ID_Conta">ID da Conta</label>
                        <input type="text" class="form-control" name="id_conta" required size="11" maxlength="100" placeholder="ID da conta">
                    </div>
                    <div class="form-group">
                        <label for="ID_Categoria">ID da Categoria</label>
                        <input type="text" class="form-control" name="id_categoria" required size="11" maxlength="100" placeholder="ID da categoria">
                    </div>
                    <div class="form-group">
                        <label for="Valor">Valor</label>
                        <input type="text" class="form-control"  name="valor" required size="10" placeholder="Valor decimal">
                    </div>
                    <div class="form-group">
                        <label for="Operacao">Operação</label>
                        <input type="text" class="form-control"  name="operacao" required size="1" maxlength="1" placeholder="Operação(D/C)">
                    </div>
                    <div class="form-group">
                        <label for="Data">Data</label>
                        <input type="text" class="form-control data"  name="Data" required size="10" placeholder="Data">
                    </div>
                    <div class="form-group">
                        <label for="Descrição">Descrição</label>
                        <input type="text" class="form-control" name="descricao" required size="20" maxlength="100" placeholder="Descrição da categoria">
                    </div> 
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
        </div>

        <%@include file="Scripts_basicos.html" %>

	<script>
		$(document).ready(function(){ 
		   $('.conta_corrente').mask('0000-0', {reverse: true});
                   $('.data').mask('00/00/0000', {reverse: true});
		});
	</script>        
    </body>
</html>
