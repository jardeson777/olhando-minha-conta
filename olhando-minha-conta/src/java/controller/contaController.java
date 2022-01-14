package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import model.*;
import aplicacao.*;

@WebServlet(name = "contaController", urlPatterns = {"/contaController"})

public class contaController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
       // rd.forward(request, response);
       
        contasDAO contasdao = new contasDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Conta> minhasContas;

        Conta conta = new Conta();
        switch (acao) {
            case "mostrar":
                minhasContas = contasdao.getList();
                request.setAttribute("minhasContas", minhasContas);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaContaView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":                              
                conta.setId(0);
                conta.setIdUsuario("");
                conta.setNomeConta("");
                conta.setBanco("");
                conta.setAgencia("");
                conta.setContaCorrente("");                

                request.setAttribute("conta", conta);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/FormConta.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                conta = contasdao.getContaPorID(id);

                if (conta.getId() > 0) {
                    request.setAttribute("conta", conta);
                    RequestDispatcher rs = request.getRequestDispatcher("FormConta.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar Conta!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/ErroUsuario.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                contasdao.delete(id);

                minhasContas = contasdao.getList();
                request.setAttribute("minhasContas", minhasContas);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaContaView.jsp");
                aposexcluir.forward(request, response);
                break;
        }       
    }
}
