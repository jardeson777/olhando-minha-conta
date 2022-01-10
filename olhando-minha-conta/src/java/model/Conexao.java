package model;

import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Conexao", urlPatterns = {"/Conexao"})

public class Conexao extends HttpServlet{
    private static Connection conexao = null;
    
    public static Connection criarConexao() throws SQLException {
        if(conexao == null){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/financeiro", "root", "");
                System.out.println("Driver encontrado");
            } 
            catch( ClassNotFoundException e ){
                e.printStackTrace();
                System.out.println("Driver não encontrado");
                System.out.println(e);
            }
        }
        
        return conexao;
    }
}
