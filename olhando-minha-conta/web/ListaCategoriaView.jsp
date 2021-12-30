<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.*" %>
<!DOCTYPE html>
<html>
    <head>
        <%// %@include file="Cabecalho.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="MenuAdm.jsp" />

                   
            <h1>Lista de Categorias</h1>     
            <p></p>
            <a href="categoriaController?acao=incluir" class="btn btn-outline-primary">Incluir</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Descrição</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Categoria> ListaCategoria = (ArrayList<Categoria>) request.getAttribute("minhasCategorias");
                            for (int i = 0; i < ListaCategoria.size(); i++) {
                                Categoria aux = ListaCategoria.get(i);
                                String link_editar = "categoriaController?acao=editar&id="+aux.getId();
                                String link_excluir = "categoriaController?acao=excluir&id="+aux.getId();
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getDescricao()%></td>
                            <td><a href="<%=link_excluir%>" class="btn btn-outline-danger float-right">Excluir</a> <a href="<%=link_editar%>" class="btn btn-outline-secondary float-right">Editar</a> 
                            </td> 
                           
                        </tr>
                        <%
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
        <%@include file="Scripts_basicos.html" %>
    </body>
</html>