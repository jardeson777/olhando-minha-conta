<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%// %@include file="Cabecalho.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <jsp:include page="Menu.jsp" />

            <div class="col-8 mt-5">

                <div class="alert alert-success" role="alert">
                    <h5>Administrador logado com sucesso</h5>
                </div>

                <p></p>
                <div><a href="index.jsp">Retorna</a></div>
            </div>
        </div>

        <%@include file="Scripts_basicos.html" %>
    </body>
</html>




