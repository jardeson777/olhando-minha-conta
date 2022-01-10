<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.*" %>
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
            
            .modalExcluir, .modalContainer {
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
                margin: 0 20px;
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
        <%
            Administrador dado = (Administrador)session.getAttribute("administrador");
            boolean erro = false;
            if(request.getAttribute("erro") != null){
                erro = (boolean)request.getAttribute("erro");
            }
            
            if(dado == null){
                String redirectURL = "FormLogin.jsp";
                response.sendRedirect(redirectURL);
            }
        %>
        <header class="d-flex justify-content-center pt-4 px-4">
            <div class="d-flex align-items-center w-100">
                <a href="indexAdm.jsp" class="btn btn-outline-primary voltar">Voltar</a>
                <a href="usuarioController?acao=incluir" class="btn btn-outline-primary incluir">+  Novo Usuário</a>
                <h1 class="text-center text-white">Usuários</h1>
            </div>
        </header>
        <div class="container mt-2">

            <main>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Suspenso</th>
                                <th scope="col"><div class="float-right">Ações</div><br></th>
                            </tr>
                        </thead> 
                        <tbody>
                            <%
                                ArrayList<Usuario> ListaUsuario = (ArrayList<Usuario>) request.getAttribute("meusUsuarios");
                                for (int i = 0; i < ListaUsuario.size(); i++) {
                                    Usuario aux = ListaUsuario.get(i);
                                    String link_editar = "usuarioController?acao=editar&id="+aux.getId();
                                    String link_excluir = "usuarioController?acao=excluir&id="+aux.getId();
                            %>
                            <tr>
                                <td><%=aux.getNome()%></td> 
                                <td><%=aux.getCpf()%></td>
                                <td><%=aux.getSuspenso()%></td> 
                                <td>
                                    <a href="#" onclick="excluir(<%= aux.getId()%>)" class="btn btn-outline-danger float-right">Excluir</a> 
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
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </main>
        </div>
                        
        <%
            if(erro){
        %>
        <div class="modalContainer">
            <div class="modalContent">
                <div class="alert alert-warning" role="alert">
                    Esse usuário não pode ser deletado. <br>
                    Verifique se ele não possui alguma conta ou lançamento vinculado.
                </div>
                <br>
                <button class="closeModal btn btn-secondary">Fechar</button>
            </div>
        </div>
        <%}%>
        <%@include file="Scripts_basicos.html" %>
        
        <script>
            $('.modalExcluir').hide();
            function excluir(id) {
                $('#'+id).show();
            }
            
            function voltar() {
                $('.modalExcluir').hide();
            }
            
            $('.closeModal').click(()=>{
                $('.modalContainer').hide();
            });
        </script>
    </body>
</html>