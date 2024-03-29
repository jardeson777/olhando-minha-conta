package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import model.administradoresDAO;
import model.usuariosDAO;
import aplicacao.Administrador;
import aplicacao.Usuario;
import javax.servlet.http.HttpSession;

@WebServlet(name = "loginController", urlPatterns = {"/loginController"})

public class loginController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = (String) request.getParameter("acao");
        if("sair".equals(acao)){
            HttpSession session = request.getSession();
            
            if(session.getAttribute("administrador") != null){
                session.removeAttribute("administrador");
            }            
            else{session.removeAttribute("usuario");}         
            
            RequestDispatcher rd = request.getRequestDispatcher("/FormLogin.jsp");
            rd.forward(request, response);
            
        }
    }

        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");

            
            if(!cpf.isEmpty() && cpf.length() == 14 & !senha.isEmpty()){
                administradoresDAO administradorDAO = new administradoresDAO();
                boolean loginAdministrador = administradorDAO.getLogin(cpf, senha);
                
                usuariosDAO usuarioDAO = new usuariosDAO();
                boolean loginUsuario = usuarioDAO.getLogin(cpf, senha);
                
                if(loginAdministrador){
                    Administrador administrador = new Administrador();
                    
                    administrador = administradorDAO.getDadosCpf(cpf);
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("administrador", administrador);
                    
                    RequestDispatcher rd = request.getRequestDispatcher("/indexAdm.jsp");
                    rd.forward(request, response);
                } 
                else if(loginUsuario){
                    Usuario usuario = new Usuario();
                    
                    usuario = usuarioDAO.getDadosCpf(cpf);
                    
                    HttpSession session = request.getSession();
                    session.setAttribute("usuario", usuario);
                
                    RequestDispatcher rd = request.getRequestDispatcher("/indexUsuario.jsp");
                    rd.forward(request, response);
                }
                else {
                    HttpSession session = request.getSession();
                    session.setAttribute("erro", "sim");
                    
                    RequestDispatcher rd = request.getRequestDispatcher("/FormLogin.jsp");
                    rd.forward(request, response);
                }
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
        //request.setAttribute("teste", administradores);
        //RequestDispatcher rd = request.getRequestDispatcher("/teste.jsp");
        //rd.forward(request, response);
    }
}
