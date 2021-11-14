package model;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.Conexao;
import aplicacao.Administrador;

@WebServlet(name = "administradoresDAO", urlPatterns = {"/administradoresDAO"})

public class administradoresDAO extends HttpServlet{
    
    private Connection conexao;
    
    public administradoresDAO(){
        try{
            conexao = Conexao.criarConexao();
        } catch (Exception e){
            System.out.println("conexao não realizada");
            System.out.println(e);
        }
    }
    
    public ArrayList<Administrador> getList(){
        ArrayList<Administrador> resultado = new ArrayList<>();
        
        try{
            Statement sql = conexao.createStatement();
            ResultSet resultadoBusca = sql.executeQuery("select * from administradores");
            
            while(resultadoBusca.next()){
                Administrador administrador = new Administrador();
                
                administrador.setId(resultadoBusca.getInt("id"));
                administrador.setNome(resultadoBusca.getString("nome"));
                administrador.setCpf(resultadoBusca.getString("cpf"));
                administrador.setSenha(resultadoBusca.getString("senha"));
                
                resultado.add(administrador);
            }
            
        } catch(Exception e){
            System.out.println(e);
        }
        return resultado;
    }

    
}
