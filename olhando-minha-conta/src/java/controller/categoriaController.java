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

@WebServlet(name = "categoriaController", urlPatterns = {"/categoriaController"})

public class categoriaController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
       // rd.forward(request, response);
       
        categoriasDAO categoriasdao = new categoriasDAO();
        String acao = (String) request.getParameter("acao");
        int id;
        ArrayList<Categoria> minhasCategorias;

        Categoria categoria = new Categoria();
        switch (acao) {
            case "mostrar":
                minhasCategorias = categoriasdao.getList();
                request.setAttribute("minhasCategorias", minhasCategorias);
                RequestDispatcher mostrar = getServletContext().getRequestDispatcher("/ListaCategoriaView.jsp");
                mostrar.forward(request, response);
                break;

            case "incluir":
                categoria.setId(0);
                categoria.setDescricao("");

                request.setAttribute("categoria", categoria);
                RequestDispatcher incluir = getServletContext().getRequestDispatcher("/FormCategoria.jsp");
                incluir.forward(request, response);
                break;

            case "editar":

                id = Integer.parseInt(request.getParameter("id"));
                categoria = categoriasdao.getCategoriaPorID(id);

                if (categoria.getId() > 0) {
                    request.setAttribute("categoria", categoria);
                    RequestDispatcher rs = request.getRequestDispatcher("FormCategoria.jsp");
                    rs.forward(request, response);
                } else {
                    String mensagem = "Erro ao gravar Categoria!";
                    request.setAttribute("mensagem", mensagem);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/ErroAdm.jsp");
                    rd.forward(request, response);
                }
                break;

            case "excluir":

                id = Integer.parseInt(request.getParameter("id"));
                categoriasdao.delete(id);

                minhasCategorias = categoriasdao.getList();
                request.setAttribute("minhasCategorias", minhasCategorias);
                RequestDispatcher aposexcluir = getServletContext().getRequestDispatcher("/ListaCategoriaView.jsp");
                aposexcluir.forward(request, response);
                break;
        }       
    }
}

