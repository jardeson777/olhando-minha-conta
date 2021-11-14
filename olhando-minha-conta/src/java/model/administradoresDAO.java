package model;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import aplicacao.Administrador;

@WebServlet(name = "administradoresDAO", urlPatterns = {"/administradoresDAO"})

public class administradoresDAO extends HttpServlet{
    
    private Connection conexao;
    
    public administradoresDAO(){
        try{
            conexao = Conexao.criarConexao();
        } catch (SQLException e){
            System.out.println("conexao não realizada");
            System.out.println(e);
        }
    }
    
    public ArrayList<Administrador> getList(){
        ArrayList<Administrador> resultado = null;
        
        try{
            resultado = new ArrayList<>();

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
            
        } catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public Administrador getDados(int id){
        Administrador administrador = null;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from administradores where id == ?");
            sql.setInt(1, id);
            ResultSet resultadoBusca = sql.executeQuery();
            
            administrador.setId(resultadoBusca.getInt("id"));
            administrador.setNome(resultadoBusca.getString("nome"));
            administrador.setCpf(resultadoBusca.getString("cpf"));
            administrador.setSenha(resultadoBusca.getString("senha"));
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return administrador;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from administradores where id == ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean insert(Administrador administrador){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("insert into administradores (nome, cpf, senha) values (?, ?, ?)");
            sql.setString(1, administrador.getNome());
            sql.setString(2, administrador.getCpf());
            sql.setString(3, administrador.getSenha());
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean update(Administrador administrador){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("update administradores set nome = ?, cpf = ?, senha = ? where id == ?");
            sql.setString(1, administrador.getNome());
            sql.setString(2, administrador.getCpf());
            sql.setString(3, administrador.getSenha());
            sql.setInt(4, administrador.getId());
            sql.executeUpdate();
            
            resultado = true;
        } catch(SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
}
