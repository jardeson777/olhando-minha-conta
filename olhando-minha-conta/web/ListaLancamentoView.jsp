<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.*" %>
<%@page import="model.categoriasDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <%// %@include file="Cabecalho.html" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <style>
            header {
                background-image: linear-gradient(#f25f4c, #fff);
                height: 130px;
                width: 100vw;
            }
            
            header div {
                margin: auto;
                max-width: 1110px;
            }
            
            header a.incluir {
                background: #fff !important;
                color: #f25f4c !important;
                border: none !important;
                font-weight: bold;
            }
            
            header a.incluir:hover {
                box-shadow: 2px 2px 6px 0px #191919;
                transition: box-shadow 0.3s;
            }
            
            header a.voltar {
                background: #fff !important;
                color: #f25f4c !important;
                border: none !important;
                font-weight: bold;
                margin-right: 10px;
            }
            
            header a.voltar:hover {
                box-shadow: 2px 2px 6px 0px #191919;
                transition: box-shadow 0.3s;
            }
            
            header h1 {
                position: absolute;
                top: 10px;
                left: 50%;
                margin-left: -78px;
            }
            
            .modalExcluir {
                width: 100vw;
                height: 100vh;
                background: rgba(0, 0, 0, 0.5);
                position: fixed;
                top: 0;
                left: 0;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            
            .modalContent{
                background: #fff;
                border-radius: 8px;
                padding: 20px;
            }

            .modalContent .botoes{
                display: flex;
                justify-content: space-between;
                margin-top: 30px;
            }
            
            .modalContent .botoes .excluir{
                padding: 7px;
                background: red;
                border-radius: 8px;
                color: #fff;
            }
        </style>
    </head>
    <body>
        <header class="d-flex justify-content-center pt-4 px-4">
            <div class="d-flex align-items-center w-100">
                <a href="indexUsuario.jsp" class="btn btn-outline-primary voltar">Voltar</a>
                <a href="lancamentoController?acao=incluir" class="btn btn-outline-primary incluir">+  Novo Lançamento</a>
                <h1 class="text-center text-white">Lançamentos</h1>
            </div>
        </header>

        <div class="container mt-2">
            <%
                Usuario dado = (Usuario)session.getAttribute("usuario");

                if(dado == null){
                    String redirectURL = "FormLogin.jsp";
                    response.sendRedirect(redirectURL);
                }
            %>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Conta</th>
                            <th scope="col">Categoria</th>
                            <th scope="col">Valor</th>
                            <th scope="col">Operação</th>
                            <th scope="col">Data</th>
                            <th scope="col">Descrição</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            /*ArrayList<Lancamento> ListaLancamento = (ArrayList<Lancamento>) request.getAttribute("meusLancamentos");
                            for (int i = 0; i < ListaLancamento.size(); i++) {
                                Lancamento aux = ListaLancamento.get(i);
                                String link_editar = "lancamentoController?acao=editar&id="+aux.getId();
                                String link_excluir = "lancamentoController?acao=excluir&id="+aux.getId();                              
                            */
                            ArrayList<Conta> ListaConta = (ArrayList<Conta>) request.getAttribute("minhasContas");
                            ArrayList<Lancamento> ListaLancamento = (ArrayList<Lancamento>) request.getAttribute("meusLancamentos");
                            for (int i = 0; i < ListaConta.size(); i++) {
                                if(Integer.parseInt(ListaConta.get(i).getIdUsuario()) == dado.getId()){
                                    Conta aux_conta = ListaConta.get(i);
                                    for (int j = 0; j < ListaLancamento.size(); j++){
                                        if(Integer.parseInt(ListaLancamento.get(j).getIdConta()) == aux_conta.getId()){
                                            Lancamento aux= ListaLancamento.get(j);
                                            String link_editar = "lancamentoController?acao=editar&id="+aux.getId();
                                            String link_excluir = "lancamentoController?acao=excluir&id="+aux.getId();
                                            
                                                        
                            ArrayList<Categoria> ListaCategoria = new ArrayList();
                            categoriasDAO categoriaDAO = new categoriasDAO();
                            ListaCategoria = categoriaDAO.getList();
                            for (int k = 0; k < ListaCategoria.size(); k++) {
                                if(ListaCategoria.get(k).getId() == Integer.parseInt(aux.getIdCategoria())){
                                    Categoria aux_categoria = ListaCategoria.get(k);                                            
                        %>
                        <tr>
                            <td><%=aux_conta.getNomeConta()%></td>
                            <td><%=aux_categoria.getDescricao()%></td>
                            <td><%=aux.getValor()%></td> 
                            <td><%=aux.getOperacao()%></td>
                            <td><%=aux.getData()%></td>
                            <td><%=aux.getDescricao()%></td>
                            <td><a href="#" onclick="excluir(<%= aux.getId()%>)" class="btn btn-outline-danger float-right">Excluir</a> 
                                <a href="<%=link_editar%>" class="btn btn-outline-secondary float-right">Editar</a> 
                            </td> 
                           
                        </tr>
                        
                        <div class="modalExcluir" id="<%= aux.getId()%>">
                            <div class="modalContent">
                                <h2>Realmente deseja excluir?</h2>
                                <div class="botoes">
                                    <a class="voltar" onclick="voltar()" href="#">Voltar</a>
                                    <a class="excluir" href="<%=link_excluir%>">Excluir</a>
                                </div>
                            </div>
                        </div>
                        
                        <%
                           } } } } } }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="Scripts_basicos.html" %>
        <script>
            $('.modalExcluir').hide();
            function excluir(id) {
                $('#'+id).show();
            }
            
            function voltar() {
                $('.modalExcluir').hide();
            }
        </script>
    </body>
</html>
