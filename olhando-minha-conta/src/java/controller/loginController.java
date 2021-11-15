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
        RequestDispatcher rd = request.getRequestDispatcher("/FormLogin.jsp");
        rd.forward(request, response);
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
                    RequestDispatcher rd = request.getRequestDispatcher("/administradorLogado.jsp");
                    rd.forward(request, response);
                } else if(loginUsuario){
                    RequestDispatcher rd = request.getRequestDispatcher("/usuarioLogado.jsp");
                    rd.forward(request, response);
                } else {
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
