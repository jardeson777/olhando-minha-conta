<%@page import="aplicacao.Administrador"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <ul>
            <%
                ArrayList<Administrador> listaAdministradores = (ArrayList<Administrador>) request.getAttribute("teste"); 
                for(int i = 0; i < listaAdministradores.size(); i++){
                    Administrador dado = listaAdministradores.get(i);
            %>
            
            <li>nome: <% out.println(dado.getNome());%></li>
            <li>id: <% out.println(dado.getId());%></li>
            <li>cpf: <% out.println(dado.getCpf());%></li>
            <li>senha: <% out.println(dado.getSenha());%></li>
            
            <%}%>    
        </ul>
    </body>
</html>
