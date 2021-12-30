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
       // RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
       // rd.forward(request, response);
       
        administradoresDAO administradoresdao = new administradoresDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Administrador> meusAdministradores;

        Administrador administrador = new Administrador();
        switch (acao) {
            case "mostrar":
                meusAdministradores = administradoresdao.getList();
                request.setAttribute("meusAdministradores", meusAdministradores);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaAdministradorView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                administrador.setId(0);
                administrador.setNome("");
                administrador.setCpf("");
                administrador.setSenha("");

                request.setAttribute("administrador", administrador);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/FormAdministrador.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                administrador = administradoresdao.getAdministradorPorID(id);

                if (administrador.getId() > 0) {
                    request.setAttribute("administrador", administrador);
                    RequestDispatcher rs = request.getRequestDispatcher("FormAdministrador.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar Administrador!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Erro.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                administradoresdao.delete(id);

                meusAdministradores = administradoresdao.getList();
                request.setAttribute("meusAdministradores", meusAdministradores);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaAdministradorView.jsp");
                aposexcluir.forward(request, response);
                break;
        }       
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
                        categoria.setId(Integer.parseInt(request.getParameter("id")));
                        categoria.setDescricao(request.getParameter("descricao"));
                        categoriaDAO.gravar(categoria);

                        RequestDispatcher rd = request.getRequestDispatcher("/SucessoAdm.jsp");
                        rd.forward(request, response);
                    }else {
                        RequestDispatcher rd = request.getRequestDispatcher("/ErroAdm.jsp");
                        rd.forward(request, response);
                    }
                break;

                case "CriarAdministrador":
                    Administrador administrador = new Administrador();
                    administradoresDAO administradorDAO = new administradoresDAO();

                    if(!request.getParameter("nome").isEmpty() && request.getParameter("cpf").length() == 14 && !request.getParameter("senha").isEmpty()){
                        administrador.setId(Integer.parseInt(request.getParameter("id")));
                        administrador.setNome(request.getParameter("nome"));
                        administrador.setSenha(request.getParameter("senha"));
                        administrador.setCpf(request.getParameter("cpf"));

                        administradorDAO.gravar(administrador);

                        RequestDispatcher rd = request.getRequestDispatcher("/SucessoAdm.jsp");
                        rd.forward(request, response);
                    }else {
                        RequestDispatcher rd = request.getRequestDispatcher("/ErroAdm.jsp");
                        rd.forward(request, response);
                    }
                break;

                case "CriarUsuario":
                    String nome = request.getParameter("nome");
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    String suspenso = request.getParameter("suspenso");

                    if ((!nome.isEmpty()) && (cpf.length() == 14) && (!senha.isEmpty()) && (!suspenso.isEmpty())) {

                        Usuario usuario = new Usuario();

                        usuario.setId(Integer.parseInt(request.getParameter("id")));
                        usuario.setNome(nome);
                        usuario.setSenha(senha);
                        usuario.setCpf(cpf);
                        usuario.setSuspenso(suspenso);

                        usuariosDAO usuariodao = new usuariosDAO();

                        if (usuariodao.gravar(usuario)) {
                            RequestDispatcher rd = request.getRequestDispatcher("/SucessoAdm.jsp");
                            rd.forward(request, response);
                        } else {
                            RequestDispatcher rd = request.getRequestDispatcher("/ErroAdm.jsp");
                            rd.forward(request, response);
                        }

                    } 
                    else {
                        RequestDispatcher rd = request.getRequestDispatcher("/ErroAdm.jsp");
                        rd.forward(request, response);
                    }
                break;
            }
        } catch (Exception e){
            System.out.println(e);
            RequestDispatcher rd = request.getRequestDispatcher("/ErroAdm.jsp");
            rd.forward(request, response);
        }
    }
}
