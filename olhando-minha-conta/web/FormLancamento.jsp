<%@page import="model.categoriasDAO"%>
<%@page import="aplicacao.Categoria"%>
<%@page import="model.contasDAO"%>
<%@page import="aplicacao.Usuario"%>
<%@page import="aplicacao.Conta"%>
<%@page import="aplicacao.Lancamento"%>
<%@page import="java.util.ArrayList"%>
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
                    Lancamento aux = null;
                    if(request.getAttribute("lancamento") != null){
                        aux = (Lancamento)request.getAttribute("lancamento");
                    }
                %>                  
                
                <h4><% if(aux.getId() > 0){out.println("Editar Lancamento");} else {out.println("Incluir Lancamento");}%></h4>
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
                    
                    <input hidden value="CriarLancamento" name="action"/>
                    <input type="hidden" class="form-control" name="<%if(aux != null){out.print("id");}%>" value="<%if(aux != null){out.print(aux.getId());} %>">                    
                    <div class="form-group">
                        <label for="ID_Conta">Conta</label> 
                        <select class="form-control" required name="id_conta">
                            <option value="" selected>Selecione a conta</option>
                            <%
                                ArrayList<Conta> ListaConta = new ArrayList();
                                contasDAO contaDAO = new contasDAO();
                                ListaConta = contaDAO.getList();
                                for (int i = 0; i < ListaConta.size(); i++) {
                                    if(Integer.parseInt(ListaConta.get(i).getIdUsuario()) == dado.getId()){
                                        Conta aux_conta = ListaConta.get(i);                               
                            %>                            
                            <option value="<%out.println(aux_conta.getId());%>"><%out.println(aux_conta.getId());%> | <%out.println(aux_conta.getNomeConta());%> | <%out.println(aux_conta.getContaCorrente());%></option>
                            <%} }%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="ID_Categoria">Categoria</label>
                        <select class="form-control" required name="id_categoria">
                            <option value="" selected>Selecione a categoria</option>
                            <%
                                ArrayList<Categoria> categorias = new ArrayList();
                                categoriasDAO categoriaDAO = new categoriasDAO();
                                
                                categorias = categoriaDAO.getList();
                                
                                for(int j = 0; j < categorias.size(); j++){
                                    Categoria categoria = categorias.get(j);
                            %>
                            <option value="<%out.println(categoria.getId());%>"><%out.println(categoria.getId() + " | " + categoria.getDescricao());%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Valor">Valor</label>
                        <input type="text" required class="form-control" id="valor" onkeypress="$(this).mask('###0.00', {reverse: true});"  name="valor" value="<%if(aux == null){out.print("");}else{ out.print(aux.getValor());} %>" required size="10" placeholder="Valor decimal">
                    </div>
                    <div class="form-group">
                        <label for="Operacao">Operação</label>
                        <select class="form-control" required name="operacao">
                            <option value="">Selecione uma operação</option>
                            <option value="C" >Crédito</option>
                            <option value="D" >Débito</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="Data">Data</label>
                        <input type="text" class="form-control data"  name="data" value="<%if(aux == null){out.print("");}else{ out.print(aux.getData());} %>" required size="8" placeholder="Data">
                    </div>
                    <div class="form-group">
                        <label for="Descrição">Descrição</label>
                        <input type="text" class="form-control" name="descricao" value="<%if(aux == null){out.print("");}else{ out.print(aux.getDescricao());} %>" size="20" maxlength="100" placeholder="Descrição da categoria">
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
                $('.data').mask('0000-00-00', {reverse: true});
            });
                
            $('.btn-secondary').click(() => {
                window.location.href = "lancamentoController?acao=mostrar";
            });    
            
            
	</script>        
    </body>
</html>
