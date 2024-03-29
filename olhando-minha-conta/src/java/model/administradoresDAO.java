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
    
    public boolean getLogin(String cpf, String senha){
        boolean resultado = false;
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from administradores where cpf = ? and senha = ?");
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
        Administrador administrador = new Administrador();
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from administradores where id = ?");
            sql.setInt(1, id);
            ResultSet resultadoBusca = sql.executeQuery();
            
            if (resultadoBusca.next()) {
                administrador.setId(resultadoBusca.getInt("id"));
                administrador.setNome(resultadoBusca.getString("nome"));
                administrador.setCpf(resultadoBusca.getString("cpf"));
                administrador.setSenha(resultadoBusca.getString("senha"));
            }
            
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return administrador;
    }
    
    public Administrador getDadosCpf(String cpf){
        Administrador administrador = new Administrador();
        
        try{
            String query = "select * from administradores where cpf = ?";
            PreparedStatement sql = conexao.prepareStatement(query);
            sql.setString(1, cpf);
            
            ResultSet resultadoBusca = sql.executeQuery();
            
            if (resultadoBusca.next()) {
                administrador.setId(resultadoBusca.getInt("id"));
                administrador.setNome(resultadoBusca.getString("nome"));
                administrador.setCpf(resultadoBusca.getString("cpf"));
                administrador.setSenha(resultadoBusca.getString("senha"));
            } else {
                return null;
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return administrador;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from administradores where id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean gravar(Administrador administrador){
        boolean resultado;        
        
        try{
            String sql;
            if ( administrador.getId() == 0 ) {
                sql = "INSERT INTO administradores (nome, cpf, senha) values (?, ?, ?)";
            } else {
                sql = "UPDATE administradores SET nome=?, cpf=?, senha=? WHERE id=?";
            }
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, administrador.getNome());
            ps.setString(2, administrador.getCpf());
            ps.setString(3, administrador.getSenha());
            
            if ( administrador.getId()> 0 )ps.setInt(4, administrador.getId());
            
            ps.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public Administrador getAdministradorPorID( int codigo ) {
        Administrador administrador = new Administrador();
        try {
            String sql = "SELECT * FROM administradores WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                administrador.setId(rs.getInt("id"));
                administrador.setNome( rs.getString("nome") );
                administrador.setCpf(rs.getString("cpf") );
                administrador.setSenha(rs.getString("Senha") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return administrador;
    }    
}
