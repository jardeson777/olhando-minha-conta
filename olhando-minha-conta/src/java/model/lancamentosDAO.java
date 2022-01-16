package model;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import aplicacao.Lancamento;

@WebServlet(name = "lancamentosDAO", urlPatterns = {"/lancamentosDAO"})
public class lancamentosDAO extends HttpServlet{
    private Connection conexao;
    
    public lancamentosDAO(){
        try{
            conexao = Conexao.criarConexao();
        } catch (SQLException e){
            System.out.println("conexao não realizada");
            System.out.println(e);
        }
    }
    
    public ArrayList<Lancamento> getList(){
        ArrayList<Lancamento> resultado = null;
        
        try{
            resultado = new ArrayList<>();
                    
            Statement sql = conexao.createStatement();
            ResultSet resultadoBusca = sql.executeQuery("select * from lancamentos");
            
            while(resultadoBusca.next()){
                Lancamento lancamento = new Lancamento();
                
                lancamento.setId(resultadoBusca.getInt("id"));
                lancamento.setIdConta(resultadoBusca.getString("id_conta"));
                lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
                lancamento.setValor(resultadoBusca.getString("valor"));
                lancamento.setOperacao(resultadoBusca.getString("operacao"));
                lancamento.setData(resultadoBusca.getString("data"));
                lancamento.setDescricao(resultadoBusca.getString("descricao"));
                
                resultado.add(lancamento);
            }
            
        } catch(SQLException e){
            System.out.println(e);
            resultado = null;
        }
        return resultado;
    }
    
    public ArrayList<Lancamento> getListCredito(){
        ArrayList<Lancamento> resultado = null;
        
        try{
            resultado = new ArrayList<>();
                    
            Statement sql = conexao.createStatement();
            ResultSet resultadoBusca = sql.executeQuery("select * from lancamento where operacao == 'C'");
            
            while(resultadoBusca.next()){
                Lancamento lancamento = new Lancamento();
                
                lancamento.setId(resultadoBusca.getInt("id"));
                lancamento.setIdConta(resultadoBusca.getString("id_conta"));
                lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
                lancamento.setValor(resultadoBusca.getString("valor"));
                lancamento.setOperacao(resultadoBusca.getString("operacao"));
                lancamento.setData(resultadoBusca.getString("data"));
                lancamento.setDescricao(resultadoBusca.getString("descricao"));
                
                resultado.add(lancamento);
            }
            
        } catch(SQLException e){
            System.out.println(e);
            resultado = null;
        }
        return resultado;
    }
    
    public ArrayList<Lancamento> getListDebito(){
        ArrayList<Lancamento> resultado = null;
        
        try{
            resultado = new ArrayList<>();
                    
            Statement sql = conexao.createStatement();
            ResultSet resultadoBusca = sql.executeQuery("select * from lancamento where operacao == 'D'");
            
            while(resultadoBusca.next()){
                Lancamento lancamento = new Lancamento();
                
                lancamento.setId(resultadoBusca.getInt("id"));
                lancamento.setIdConta(resultadoBusca.getString("id_conta"));
                lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
                lancamento.setValor(resultadoBusca.getString("valor"));
                lancamento.setOperacao(resultadoBusca.getString("operacao"));
                lancamento.setData(resultadoBusca.getString("data"));
                lancamento.setDescricao(resultadoBusca.getString("descricao"));
                
                resultado.add(lancamento);
            }
            
        } catch(SQLException e){
            System.out.println(e);
            resultado = null;
        }
        return resultado;
    }
    
    public Lancamento getDados(int id){
        Lancamento lancamento = new Lancamento();
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from lancamento where id == ?");
            sql.setInt(1, id);
            ResultSet resultadoBusca = sql.executeQuery();
            
            lancamento.setId(resultadoBusca.getInt("id"));
            lancamento.setIdConta(resultadoBusca.getString("id_conta"));
            lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
            lancamento.setValor(resultadoBusca.getString("valor"));
            lancamento.setOperacao(resultadoBusca.getString("operacao"));
            lancamento.setData(resultadoBusca.getString("data"));
            lancamento.setDescricao(resultadoBusca.getString("descricao"));
            
        } catch(SQLException e){
            System.out.println(e);
            lancamento = null;
        }
        
        return lancamento;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from lancamentos where id = ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean gravar(Lancamento lancamento){
        boolean resultado;
        
        try{
            String sql;
            if ( lancamento.getId() == 0 ) {
            sql = "INSERT INTO lancamentos (id_conta, id_categoria, valor, operacao, data, descricao) VALUES (?, ?, ?, ?, ?, ?)";
            } else {
            sql = "UPDATE lancamentos SET id_conta = ?, id_categoria = ?, valor = ?, operacao = ?, data = ?, descricao = ? WHERE id = ?";
            }        
        
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, lancamento.getIdConta());
            ps.setString(2, lancamento.getIdCategoria());
            ps.setString(3, lancamento.getValor());
            ps.setString(4, lancamento.getOperacao());
            ps.setString(5, lancamento.getData());
            ps.setString(6, lancamento.getDescricao());
            
            if ( lancamento.getId()> 0 )ps.setInt(7, lancamento.getId());            
            
            ps.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;  
    }
    
    public Lancamento getLancamentoPorID( int codigo ) {
        Lancamento lancamento = new Lancamento();
        try {
            String sql = "SELECT * FROM lancamentos WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, codigo);
            
            ResultSet rs = ps.executeQuery();
            
            if ( rs.next() ) {
                lancamento.setId(rs.getInt("id"));
                lancamento.setIdConta( rs.getString("id_conta") );
                lancamento.setIdCategoria( rs.getString("id_categoria") );
                lancamento.setValor( rs.getString("valor") );
                lancamento.setOperacao( rs.getString("operacao") );
                lancamento.setData( rs.getString("data") );
                lancamento.setDescricao( rs.getString("descricao") );
            }
            
        } catch( SQLException e ) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
        return lancamento;
    } 
}
