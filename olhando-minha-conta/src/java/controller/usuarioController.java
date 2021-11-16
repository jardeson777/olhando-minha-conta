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

@WebServlet(name = "usuarioController", urlPatterns = {"/usuarioController"})

public class usuarioController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        switch(action){
            case "CriarConta":
                Conta conta = new Conta();
                contasDAO contaDAO = new contasDAO();

                if(!request.getParameter("id_usuario").isEmpty() && !request.getParameter("nome").isEmpty() && request.getParameter("banco").length() == 3 && request.getParameter("agencia").length() == 6  && request.getParameter("conta_corrente").length() == 6){
                    String idUsuario = request.getParameter("id_usuario");

                    conta.setIdUsuario(idUsuario);
                    conta.setNomeConta(request.getParameter("nome"));
                    conta.setBanco(request.getParameter("banco"));
                    conta.setAgencia(request.getParameter("agencia"));
                    conta.setContaCorrente(request.getParameter("conta_corrente"));

                    boolean existeConta = contaDAO.searchData(conta);

                    if(!existeConta){
                        contaDAO.insert(conta);

                        RequestDispatcher rd = request.getRequestDispatcher("/Sucesso.jsp");
                        rd.forward(request, response);
                    }

                }else {
                    RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
                    rd.forward(request, response);
                }
            break;
            
            case "CriarLancamento":
                    Lancamento lancamento = new Lancamento();
                    lancamentosDAO lancamentodao = new lancamentosDAO();

                    if(!request.getParameter("id_conta").isEmpty() && !request.getParameter("id_categoria").isEmpty() && !request.getParameter("valor").isEmpty() && request.getParameter("operacao").length() == 1 && request.getParameter("data").length() == 10  && !request.getParameter("descricao").isEmpty()){                     
                        
                        lancamento.setIdConta(request.getParameter("id_conta"));
                        lancamento.setIdCategoria(request.getParameter("id_categoria"));
                        lancamento.setValor(request.getParameter("valor"));
                        lancamento.setOperacao(request.getParameter("operacao"));
                        lancamento.setData(request.getParameter("data"));
                        lancamento.setDescricao(request.getParameter("descricao"));
                        
                        if (lancamentodao.insert(lancamento)) {
                            RequestDispatcher rd = request.getRequestDispatcher("/Sucesso.jsp");
                            rd.forward(request, response);
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
                            rd.forward(request, response);
                        }

                    }else {
                        RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
                        rd.forward(request, response);
                    }
            break;
        }

    }
}
