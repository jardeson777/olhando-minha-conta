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
        usuariosDAO usuariodao = new usuariosDAO();

        ArrayList<Administrador> administradores = administradoresDAO.getList();
        
        String action = request.getParameter("action");
        
        try{
            switch(action){
                case "CriaCategoria":
                    Categoria categoria = new Categoria();
                    categoriasDAO categoriaDAO = new categoriasDAO();
                    
                    Categoria categoriaExistente = categoriaDAO.getDadosDescricao(request.getParameter("descricao"));

                    if(!request.getParameter("descricao").isEmpty() && categoriaExistente == null){
                        categoria.setId(Integer.parseInt(request.getParameter("id")));
                        categoria.setDescricao(request.getParameter("descricao"));
                        
                        
                        if(categoriaDAO.gravar(categoria)){
                            HttpSession session = request.getSession();
                            session.setAttribute("erro", "nao");
                            
                            categoriasDAO categoriasdao = new categoriasDAO();
                            ArrayList<Categoria> minhasCategorias;
                            
                            minhasCategorias = categoriasdao.getList();
                            request.setAttribute("minhasCategorias", minhasCategorias);
                
                            RequestDispatcher rd = request.getRequestDispatcher("/ListaCategoriaView.jsp");
                            rd.forward(request, response);
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("erro", "sim");
                            
                            RequestDispatcher rd = request.getRequestDispatcher("/FormCategoria.jsp");
                            rd.forward(request, response);
                        }
                    }else {
                        HttpSession session = request.getSession();
                        session.setAttribute("erro", "sim");
                            
                        RequestDispatcher rd = request.getRequestDispatcher("/FormCategoria.jsp");
                        rd.forward(request, response);
                    }
                break;

                case "CriarAdministrador":
                    Administrador administrador = new Administrador();
                    administradoresDAO administradorDAO = new administradoresDAO();
                    
                    String nome1 = request.getParameter("nome");
                    String cpf1 = request.getParameter("cpf");
                    String senha1 = request.getParameter("senha");
                    
                    
                    boolean validarNome1 = !nome1.isEmpty();
                    boolean validarCpf1 = cpf1.length() == 14;
                    boolean validarSenha1 = !senha1.isEmpty();
                    boolean valudarCpfExistente1 = usuariodao.getDadosCpf(cpf1) == null && administradoresDAO.getDadosCpf(cpf1) == null;
                    

                    if(validarNome1 && validarCpf1 && validarSenha1){
                        administrador.setId(Integer.parseInt(request.getParameter("id")));
                        administrador.setNome(request.getParameter("nome"));
                        administrador.setSenha(request.getParameter("senha"));
                        administrador.setCpf(request.getParameter("cpf"));
                        
                        Administrador admExistente = new Administrador();
                        
                        
                        if(administradorDAO.gravar(administrador)){
                            HttpSession session = request.getSession();
                            session.setAttribute("erro", "nao");
                            
                            administradoresDAO administradordao = new administradoresDAO();
                            ArrayList<Administrador> meusAdministradores;
                            
                            meusAdministradores = administradordao.getList();
                            request.setAttribute("meusAdministradores", meusAdministradores);
                            RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaAdministradorView.jsp");
                            mostrar.forward(request, response);
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("erro", "sim");
                            
                            RequestDispatcher rd = request.getRequestDispatcher("/FormAdministrador.jsp");
                            rd.forward(request, response);
                        }

                    }else {
                        HttpSession session = request.getSession();
                        session.setAttribute("erro", "sim");
                        
                        RequestDispatcher rd = request.getRequestDispatcher("/FormAdministrador.jsp");
                        rd.forward(request, response);
                    }
                break;

                case "CriarUsuario":
                    String nome = request.getParameter("nome");
                    String cpf = request.getParameter("cpf");
                    String senha = request.getParameter("senha");
                    String suspenso = request.getParameter("suspenso");
                    
                    
                    boolean validarNome = !nome.isEmpty();
                    boolean validarCpf = cpf.length() == 14;
                    boolean validarSenha = !senha.isEmpty();
                    boolean validarSuspenso = !suspenso.isEmpty();
                    boolean validarCpfExistente = usuariodao.getDadosCpf(cpf) == null && administradoresDAO.getDadosCpf(cpf) == null;

                    if (validarNome && validarCpf && validarSenha && validarSuspenso) {

                        Usuario usuario = new Usuario();
                        
                        if(Integer.parseInt(request.getParameter("id")) > 0){
                            usuario.setId(Integer.parseInt(request.getParameter("id")));
                        }
                        usuario.setNome(nome);
                        usuario.setSenha(senha);
                        usuario.setCpf(cpf);
                        usuario.setSuspenso(suspenso);


                        if (usuariodao.gravar(usuario)) {
                            HttpSession session = request.getSession();
                            session.setAttribute("erro", "nao");
                            
                            usuariosDAO usuariosdao = new usuariosDAO();
                            ArrayList<Usuario> meusUsuarios;
                            
                            meusUsuarios = usuariosdao.getList();
                            request.setAttribute("meusUsuarios", meusUsuarios);
                            RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaUsuarioView.jsp");
                            mostrar.forward(request, response);
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("erro", "sim");
                            
                            usuariosDAO usuariosdao = new usuariosDAO();
                            Usuario usuario1 = new Usuario();
                            
                            usuario1 = usuariosdao.getDadosCpf(cpf);
                            request.setAttribute("usuario", usuario1);
                            
                            RequestDispatcher rd = request.getRequestDispatcher("/FormUsuario.jsp");
                            rd.forward(request, response);
                        }

                    } 
                    else {
                        HttpSession session = request.getSession();
                        session.setAttribute("erro", "sim");
                        
                        usuariosDAO usuariosdao = new usuariosDAO();
                        Usuario usuario1 = new Usuario();

                        usuario1 = usuariosdao.getDadosCpf(cpf);
                        request.setAttribute("usuario", usuario1);
                        
                        RequestDispatcher rd = request.getRequestDispatcher("/FormUsuario.jsp");
                        rd.forward(request, response);
                    }
                break;
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
