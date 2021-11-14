<%@page import="aplicacao.Usuario"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            ArrayList<Usuario> listaAdministradores = (ArrayList<Usuario>) request.getAttribute("teste");
            for(int i = 0; i < listaAdministradores.size(); i++){
                Usuario dado = listaAdministradores.get(i);
        %>
        <ul>
            <li>nome: <% out.println(dado.getNome());%></li>
            <li>id: <% out.println(dado.getId());%></li>
            <li>cpf: <% out.println(dado.getCpf());%></li>
            <li>senha: <% out.println(dado.getSenha());%></li>
            <li>senha: <% out.println(dado.getSuspenso());%></li>
        </ul>
        <%}%>    
        
    </body>
</html>
