<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="Scripts_basicos.html"%>
    </head>
    <body>
        <header>
            <img src="assets/img/logo.png" alt="logo" />
            <a href="FormLogin.jsp">Logar</a>
        </header>
            
        <main>
            
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="item">
                            <h2>
                                Bem vindo ao Olhando Minha Conta.
                            </h2>
                            <img src="assets/img/imgHome.png" alt="imagem home"/>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="item">
                            <h2>
                                Aqui você consegue gerenciar sua conta bancária registrando e acompanhando seus lançamentos.
                            </h2>
                            <img src="assets/img/imgHome2.png" alt="imagem home"/>
                        </div>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </main>
        
        <footer>
            <p>
                Desenvolvido por Jardeson Nogueira e Daniel Neves
            </p>
        </footer>
    </body>
    
    <style type="text/css">
        body{
            height: 100vh;
            background: #0f0e17;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        
        header{
            display: flex;
            align-items: center;
            padding: 20px 20px;
            max-width: 1000px;
            width: 100%;
            margin: 0 auto;
        }
        
        header img{
            width: 150px;
        }
        
        header a{
            margin-left: auto;
            font-size: 20px;
            color: #ff8906;
            opacity: .9;
        }
        
        header a:hover{
            color: #ff8906;
            opacity: 1;
        }
        
        main{
            padding: 0 20px;
            max-width: 900px;
            margin: 0 auto;
            margin-top: -10%;
        }
        
        .item{
            display: flex;
            align-items: center;
            flex-direction: column;
        }
        
        img{
            width: 100%;
            max-width: 400px;
        }
        
        h2{
            color: #fff;
            width: 100%;
            max-width: 400px;
            margin: 0 auto;
            font-size: max(150%, 25px);
        }
        
        footer{
            height: 40px;
            color: #a7a9be;
            font-size: 14px;
            text-align: center;
            
        }
        
        
    </style>
</html>
