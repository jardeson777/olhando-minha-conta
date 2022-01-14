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
import javax.servlet.http.HttpSession;

@WebServlet(name = "usuarioController", urlPatterns = {"/usuarioController"})

public class usuarioController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        // rd.forward(request, response);
        
        usuariosDAO usuariosdao = new usuariosDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Usuario> meusUsuarios;

        Usuario usuario = new Usuario();
        switch (acao) {
            case "mostrar":
                meusUsuarios = usuariosdao.getList();
                request.setAttribute("meusUsuarios", meusUsuarios);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaUsuarioView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                usuario.setId(0);
                usuario.setNome("");
                usuario.setCpf("");
                usuario.setSenha("");
                usuario.setSuspenso("");

                request.setAttribute("usuario", usuario);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/FormUsuario.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                usuario = usuariosdao.getUsuarioPorID(id);

                if (usuario.getId() > 0) {
                    request.setAttribute("usuario", usuario);
                    RequestDispatcher rs = request.getRequestDispatcher("FormUsuario.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar Usuario!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/ErroAdm.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                boolean deleteUser = usuariosdao.delete(id);
                
                if(!deleteUser){
                    request.setAttribute("erro", !deleteUser);
                }

                meusUsuarios = usuariosdao.getList();
                request.setAttribute("meusUsuarios", meusUsuarios);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaUsuarioView.jsp");
                aposexcluir.forward(request, response);
                break;
        }        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        switch(action){
            case "CriarConta":
                Conta conta = new Conta();
                contasDAO contaDAO = new contasDAO();

                if(!request.getParameter("id_usuario").isEmpty() && !request.getParameter("nome_conta").isEmpty() && request.getParameter("banco").length() == 3 && request.getParameter("agencia").length() == 6  && request.getParameter("conta_corrente").length() == 6){
                    String idUsuario = request.getParameter("id_usuario");

                    conta.setId(Integer.parseInt(request.getParameter("id")));
                    conta.setIdUsuario(idUsuario);
                    conta.setNomeConta(request.getParameter("nome_conta"));
                    conta.setBanco(request.getParameter("banco"));
                    conta.setAgencia(request.getParameter("agencia"));
                    conta.setContaCorrente(request.getParameter("conta_corrente"));

                    boolean existeConta = contaDAO.searchData(conta);

                    if(contaDAO.gravar(conta)){
                        HttpSession session = request.getSession();
                        session.setAttribute("erro", "nao");
                            
                        contasDAO contadao = new contasDAO();
                        ArrayList<Conta> minhasContas;
                            
                        minhasContas = contaDAO.getList();
                        request.setAttribute("minhasContas", minhasContas);
                        RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaContaView.jsp");
                        mostrar.forward(request, response);
                    } else {
                        HttpSession session = request.getSession();
                        session.setAttribute("erro", "sim");
                            
                        RequestDispatcher rd = request.getRequestDispatcher("/FormConta.jsp");
                        rd.forward(request, response);
                        }

                }else {
                    HttpSession session = request.getSession();
                    session.setAttribute("erro", "sim");
                        
                    RequestDispatcher rd = request.getRequestDispatcher("/FormConta.jsp");
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
