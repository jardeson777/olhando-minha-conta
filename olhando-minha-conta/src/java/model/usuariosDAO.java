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
    
    public boolean getLogin(String cpf, String senha){
        boolean resultado = false;
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from usuarios where cpf = ? and senha = ?");
            sql.setString(1, cpf);
            sql.setString(2, senha);
            ResultSet resultadoBusca = sql.executeQuery();

            if(resultadoBusca.next()){
                resultado = true;
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return resultado;
    }
    
    public Usuario getDados(int id){
        Usuario usuario = new Usuario();
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from usuarios where id = ?");
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
    
    public Usuario getDadosCpf(String cpf){
        Usuario usuario = new Usuario();
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from usuarios where cpf = ?");
            sql.setString(1, cpf);
            ResultSet resultadoBusca = sql.executeQuery();
            
            if(resultadoBusca.next()){
                usuario.setId(resultadoBusca.getInt("id"));
                usuario.setNome(resultadoBusca.getString("nome"));
                usuario.setCpf(resultadoBusca.getString("cpf"));
                usuario.setSenha(resultadoBusca.getString("senha"));
                usuario.setSuspenso(resultadoBusca.getString("suspenso"));
            } else {
                return null;
            }
        } catch(SQLException e){
            System.out.println(e);
            usuario = null;
        }
        
        return usuario;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from usuarios where id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean gravar(Usuario usuario){
        boolean resultado;
        
        try{
            String sql;
            if ( usuario.getId() == 0 ) {
                sql = "INSERT INTO usuarios (nome, senha, cpf, suspenso) VALUES (?,?,?,?)";
            } else {
                sql = "UPDATE usuarios SET nome=?, senha=?, cpf=?, suspenso=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getSenha());
            ps.setString(3, usuario.getCpf());
            ps.setString(4, usuario.getSuspenso());
            
            if ( usuario.getId()> 0 )
                ps.setInt(5, usuario.getId());
            
            ps.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public Usuario getUsuarioPorID( int codigo ) {
        Usuario usuario = new Usuario();
        try {
            String sql = "SELECT * FROM usuarios WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                usuario.setId(rs.getInt("id"));
                usuario.setNome( rs.getString("nome") );
                usuario.setCpf(rs.getString("cpf") );
                usuario.setSenha(rs.getString("Senha") );
                usuario.setSuspenso(rs.getString("suspenso") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return usuario;
    }    
}
