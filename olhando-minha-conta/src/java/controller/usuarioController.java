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
        usuariosDAO usuarioDAO = new usuariosDAO();
        ArrayList<Usuario> administradores = usuarioDAO.getList();
        
        request.setAttribute("teste", administradores);
        RequestDispatcher rd = request.getRequestDispatcher("/teste.jsp");
        rd.forward(request, response);
    }
}
