package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import model.usuariosDAO;
import aplicacao.Usuario;

@WebServlet(name = "usuarioController", urlPatterns = {"/usuarioController"})

public class usuarioController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        String suspenso = request.getParameter("suspenso");

        if ((!nome.isEmpty()) && (!cpf.isEmpty()) && (!senha.isEmpty()) && (!suspenso.isEmpty())) {

            Usuario usuario = new Usuario();

            usuario.setId(0);
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuario.setCpf(cpf);
            usuario.setSuspenso(suspenso);

            usuariosDAO usuariodao = new usuariosDAO();
            
            if (usuariodao.gravar(usuario)) {
                RequestDispatcher rd = request.getRequestDispatcher("/Sucesso.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
                rd.forward(request, response);
            }

        } 
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }

    }
}
