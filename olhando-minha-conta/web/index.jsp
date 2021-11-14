<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="Scripts_basicos.html"%>
    </head>
    <body>
        <form method="get" action="administradorController">
            <button type="submit" class="btn btn-primary">Enviar</button>
        </form>
        <form method="post" action="administradorController">
            <button type="submit" class="btn btn-danger">Enviar</button>
        </form>
    </body>
</html>
