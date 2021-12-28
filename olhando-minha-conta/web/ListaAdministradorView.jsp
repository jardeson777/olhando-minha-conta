<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,aplicacao.*" %>
<!DOCTYPE html>
<html>
    <head>
        <%// %@include file="Cabecalho.html" %>
    </head>
    <body>

        <div class="container mt-2">

            <jsp:include page="Menu.jsp" />

                   
            <h1>Lista de Administradores</h1>     
            <p></p>
            <a href="administradorController?acao=incluir" class="btn btn-outline-primary">Incluir</a>
            <p></p>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Id</th>
                            <th scope="col">Nome</th>
                            <th scope="col">CPF</th>
                            <th scope="col"><div class="float-right">Ações</div><br></th>
                        </tr>
                    </thead> 
                    <tbody>
                        <%
                            ArrayList<Administrador> ListaAdministrador = (ArrayList<Administrador>) request.getAttribute("meusAdministradores");
                            for (int i = 0; i < ListaAdministrador.size(); i++) {
                                Administrador aux = ListaAdministrador.get(i);
                                String link_editar = "administradorController?acao=editar&id="+aux.getId();
                                String link_excluir = "administradorController?acao=excluir&id="+aux.getId();
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getNome()%></td> 
                            <td><%=aux.getCpf()%></td> 
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
