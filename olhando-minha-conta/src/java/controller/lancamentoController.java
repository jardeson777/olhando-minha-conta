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

@WebServlet(name = "lancamentoController", urlPatterns = {"/lancamentoController"})

public class lancamentoController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
       // rd.forward(request, response);
       
        contasDAO contasdao = new contasDAO();
        lancamentosDAO lancamentosdao = new lancamentosDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Conta> minhasContas;
        ArrayList<Lancamento> meusLancamentos;

        Lancamento lancamento = new Lancamento();
        switch (acao) {
            case "mostrar":
                minhasContas = contasdao.getList();
                request.setAttribute("minhasContas", minhasContas);                
                meusLancamentos = lancamentosdao.getList();
                request.setAttribute("meusLancamentos", meusLancamentos);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaLancamentoView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":                              
                lancamento.setId(0);
                lancamento.setIdConta("");
                lancamento.setIdCategoria("");
                lancamento.setValor("");
                lancamento.setOperacao("");
                lancamento.setData("");
                lancamento.setDescricao("");

                request.setAttribute("lancamento", lancamento);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/FormLancamento.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                lancamento = lancamentosdao.getLancamentoPorID(id);

                if (lancamento.getId() > 0) {
                    request.setAttribute("lancamento", lancamento);
                    RequestDispatcher rs = request.getRequestDispatcher("FormLancamento.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar Lançamento!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/ErroUsuario.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                lancamentosdao.delete(id);

                minhasContas = contasdao.getList();
                request.setAttribute("minhasContas", minhasContas);
                meusLancamentos = lancamentosdao.getList();
                request.setAttribute("meusLancamentos", meusLancamentos);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaLancamentoView.jsp");
                aposexcluir.forward(request, response);
                break;
        }       
    }
}
