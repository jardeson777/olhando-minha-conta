package model;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import aplicacao.Categoria;

@WebServlet(name = "categoriasDAO", urlPatterns = {"/categoriasDAO"})

public class categoriasDAO extends HttpServlet{
    
    private Connection conexao;
    
    public categoriasDAO(){
        try{
            conexao = Conexao.criarConexao();
        } catch (SQLException e){
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
            
        } catch(SQLException e){
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
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return categoria;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from categorias where id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean gravar(Categoria categoria){
        boolean resultado;
        
        try{
            String sql;
            if ( categoria.getId() == 0 ) {
            sql = "INSERT INTO categorias (descricao) values (?)";
            } else {
            sql = "UPDATE categorias SET descricao=? WHERE id=?";
            }            
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, categoria.getDescricao());
            
            if ( categoria.getId()> 0 )ps.setInt(2, categoria.getId());

            ps.executeUpdate();
            
            resultado = true;            
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public Categoria getCategoriaPorID( int codigo ) {
        Categoria categoria = new Categoria();
        try {
            String sql = "SELECT * FROM categorias WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                categoria.setId(rs.getInt("id"));
                categoria.setDescricao( rs.getString("descricao") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return categoria;
    } 
}
