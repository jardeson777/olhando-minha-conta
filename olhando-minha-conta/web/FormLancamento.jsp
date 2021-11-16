<%@page import="model.categoriasDAO"%>
<%@page import="aplicacao.Categoria"%>
<%@page import="model.contasDAO"%>
<%@page import="aplicacao.Conta"%>
<%@page import="java.util.ArrayList"%>
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
                        <label for="ID_Conta">Conta</label>
                        
                        <select class="form-control" name="id_conta">
                            <option value="" selected>Selecione a conta</option>
                            <%
                                ArrayList<Conta> contas = new ArrayList();
                                contasDAO contaDAO = new contasDAO();
                                
                                contas = contaDAO.getList();
                                
                                for(int i = 0; i < contas.size(); i++){
                                    Conta conta = contas.get(i);
                            %>
                            <option value="<%out.println(conta.getId());%>"><%out.println(conta.getId());%> | <%out.println(conta.getNomeConta());%> | <%out.println(conta.getContaCorrente());%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ID_Categoria">Categoria</label>
                        <select class="form-control" name="id_categoria">
                            <option value="" selected>Selecione a categoria</option>
                            <%
                                ArrayList<Categoria> categorias = new ArrayList();
                                categoriasDAO categoriaDAO = new categoriasDAO();
                                
                                categorias = categoriaDAO.getList();
                                
                                for(int i = 0; i < categorias.size(); i++){
                                    Categoria categoria = categorias.get(i);
                            %>
                            <option value="<%out.println(categoria.getId());%>"><%out.println(categoria.getId() + " | " + categoria.getDescricao());%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Valor">Valor</label>
                        <input type="text" class="form-control"  name="valor" required size="10" placeholder="Valor decimal">
                    </div>
                    <div class="form-group">
                        <label for="Operacao">Operação</label>
                        <select class="form-control" name="operacao">
                            <option value="" selected>Selecione uma operação</option>
                            <option value="C">Crédito</option>
                            <option value="D">Débito</option>
                        </select>
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
