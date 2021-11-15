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
                <h4>Incluir Categoria</h4>
                <form method="POST" action="administradorController" >
                    <input hidden value="CriaCategoria" name="action"/>
                    <div class="form-group">
                        <label for="Descrição">Descrição</label>
                        <input type="text" class="form-control" name="descricao" required size="20" maxlength="100" placeholder="Descrição da categoria">
                    </div>                  
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
            </div>
        </div>

        <%@include file="Scripts_basicos.html" %>
    </body>
</html>
