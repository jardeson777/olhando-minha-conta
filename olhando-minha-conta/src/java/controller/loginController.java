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
                administradoresDAO administradoresDAO = new administradoresDAO();
                boolean loginAdministrador = administradoresDAO.getLogin(cpf);
                
                if(loginAdministrador){
                    RequestDispatcher rd = request.getRequestDispatcher("/FormUsuario.jsp");
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
