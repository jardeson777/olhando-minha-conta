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

@WebServlet(name = "administradorController", urlPatterns = {"/administradorController"})

public class administradorController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        administradoresDAO administradoresDAO = new administradoresDAO();
        ArrayList<Administrador> administradores = administradoresDAO.getList();
        
        String action = request.getParameter("action");
        
        try{
            switch(action){
                case "CriaCategoria":
                    Categoria categoria = new Categoria();
                    categoriasDAO categoriaDAO = new categoriasDAO();

                    if(!request.getParameter("descricao").isEmpty()){
                        categoria.setDescricao(request.getParameter("descricao"));
                        categoriaDAO.insert(categoria);

                        RequestDispatcher rd = request.getRequestDispatcher("/Sucesso.jsp");
                        rd.forward(request, response);
                    }else {
                        RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
                        rd.forward(request, response);
                    }
                break;

                case "CriarAdministrador":
                    Administrador administrador = new Administrador();
                    administradoresDAO administradorDAO = new administradoresDAO();

                    if(!request.getParameter("nome").isEmpty() && request.getParameter("cpf").length() == 14 && !request.getParameter("senha").isEmpty()){
                        administrador.setNome(request.getParameter("nome"));
                        administrador.setSenha(request.getParameter("senha"));
                        administrador.setCpf(request.getParameter("cpf"));

                        administradorDAO.insert(administrador);

                        RequestDispatcher rd = request.getRequestDispatcher("/Sucesso.jsp");
                        rd.forward(request, response);
                    }else {
                        RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
                        rd.forward(request, response);
                    }
                break;

                case "CriarUsuario":
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
                break;
            }
        } catch (Exception e){
            System.out.println(e);
            RequestDispatcher rd = request.getRequestDispatcher("/Erro.jsp");
            rd.forward(request, response);
        }
    }
}
