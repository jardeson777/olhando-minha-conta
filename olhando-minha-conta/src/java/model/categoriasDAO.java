package model;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import model.Conexao;
import aplicacao.Categoria;

@WebServlet(name = "categoriasDAO", urlPatterns = {"/categoriasDAO"})

public class categoriasDAO extends HttpServlet{
    
    private Connection conexao;
    
    public categoriasDAO(){
        try{
            conexao = Conexao.criarConexao();
        } catch (Exception e){
            System.out.println("conexao não realizada");
            System.out.println(e);
        }
    }
    
    public ArrayList<Categoria> getList(){
        ArrayList<Categoria> resultado = null;
        
        try{
            resultado = new ArrayList<>();
                    
            Statement sql = conexao.createStatement();
            ResultSet resultadoBusca = sql.executeQuery("select * from categorias");
            
            while(resultadoBusca.next()){
                Categoria categoria = new Categoria();
                
                categoria.setId(resultadoBusca.getInt("id"));
                categoria.setDescricao(resultadoBusca.getString("descricao"));
                
                resultado.add(categoria);
            }
            
        } catch(Exception e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public Categoria getDados(int id){
        Categoria categoria = null;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from categorias where id == ?");
            sql.setInt(1, id);
            ResultSet resultadoBusca = sql.executeQuery();
            
            categoria.setId(resultadoBusca.getInt("id"));
            categoria.setDescricao(resultadoBusca.getString("descricao"));
        } catch(Exception e){
            System.out.println(e);
        }
        
        return categoria;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from categorias where id == ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (Exception e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean insert(Categoria categoria){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("insert into categorias (descricao) values (?)");
            sql.setString(1, categoria.getDescricao());
            sql.executeUpdate();
            
            resultado = true;
        } catch (Exception e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean update(Categoria categoria){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("update categorias set descricao = ? where id == ?");
            sql.setString(1, categoria.getDescricao());
            sql.setInt(2, categoria.getId());
            sql.executeUpdate();
            
            resultado = true;
        } catch(Exception e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
}
