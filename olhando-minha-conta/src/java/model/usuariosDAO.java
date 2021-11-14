package model;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import aplicacao.Usuario;

@WebServlet(name = "usuariosDAO", urlPatterns = {"/usuariosDAO"})

public class usuariosDAO extends HttpServlet{
    private Connection conexao;
    
    public usuariosDAO(){
        try{
            conexao = Conexao.criarConexao();
        } catch (SQLException e){
            System.out.println("conexao não realizada");
            System.out.println(e);
        }
    }
    
    public ArrayList<Usuario> getList(){
        ArrayList<Usuario> resultado = null;
        
        try{
            resultado = new ArrayList<>();
                    
            Statement sql = conexao.createStatement();
            ResultSet resultadoBusca = sql.executeQuery("select * from usuarios");
            
            while(resultadoBusca.next()){
                Usuario usuario = new Usuario();
                
                usuario.setId(resultadoBusca.getInt("id"));
                usuario.setNome(resultadoBusca.getString("nome"));
                usuario.setCpf(resultadoBusca.getString("cpf"));
                usuario.setSenha(resultadoBusca.getString("senha"));
                usuario.setSuspenso(resultadoBusca.getString("suspenso"));
                
                resultado.add(usuario);
            }
            
        } catch(SQLException e){
            System.out.println(e);
            resultado = null;
        }
        return resultado;
    }
    
    public Usuario getDados(int id){
        Usuario usuario = new Usuario();
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from usuarios where id == ?");
            sql.setInt(1, id);
            ResultSet resultadoBusca = sql.executeQuery();
            
            usuario.setId(resultadoBusca.getInt("id"));
            usuario.setNome(resultadoBusca.getString("nome"));
            usuario.setCpf(resultadoBusca.getString("cpf"));
            usuario.setSenha(resultadoBusca.getString("senha"));
            usuario.setSuspenso(resultadoBusca.getString("suspenso"));
            
        } catch(SQLException e){
            System.out.println(e);
            usuario = null;
        }
        
        return usuario;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from usuarios where id == ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean insert(Usuario usuario){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("insert into usuarios (id, nome, cpf, senha, suspenso) values (?, ?, ?, ?, ?)");
            sql.setInt(1, usuario.getId());
            sql.setString(2, usuario.getNome());
            sql.setString(3, usuario.getCpf());
            sql.setString(4, usuario.getSenha());
            sql.setString(5, usuario.getSuspenso());
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean update(Usuario usuario){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("update conta set id = ?, nome = ?, cpf = ?, senha = ?, suspenso = ? where id == ?");
            sql.setInt(1, usuario.getId());
            sql.setString(2, usuario.getNome());
            sql.setString(3, usuario.getCpf());
            sql.setString(4, usuario.getSenha());
            sql.setString(5, usuario.getSuspenso());
            sql.executeUpdate();
            
            resultado = true;
        } catch(SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
}
