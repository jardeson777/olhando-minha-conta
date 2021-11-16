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
            ResultSet resultadoBusca = sql.executeQuery("select * from lancamento");
            
            while(resultadoBusca.next()){
                Lancamento lancamento = new Lancamento();
                
                lancamento.setId(resultadoBusca.getString("id"));
                lancamento.setIdConta(resultadoBusca.getString("id_conta"));
                lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
                lancamento.setValor(resultadoBusca.getFloat("valor"));
                lancamento.setOperacao(resultadoBusca.getString("operacao"));
                lancamento.setData(resultadoBusca.getDate("data"));
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
                
                lancamento.setId(resultadoBusca.getString("id"));
                lancamento.setIdConta(resultadoBusca.getString("id_conta"));
                lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
                lancamento.setValor(resultadoBusca.getFloat("valor"));
                lancamento.setOperacao(resultadoBusca.getString("operacao"));
                lancamento.setData(resultadoBusca.getDate("data"));
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
                
                lancamento.setId(resultadoBusca.getString("id"));
                lancamento.setIdConta(resultadoBusca.getString("id_conta"));
                lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
                lancamento.setValor(resultadoBusca.getFloat("valor"));
                lancamento.setOperacao(resultadoBusca.getString("operacao"));
                lancamento.setData(resultadoBusca.getDate("data"));
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
            
            lancamento.setId(resultadoBusca.getString("id"));
            lancamento.setIdConta(resultadoBusca.getString("id_conta"));
            lancamento.setIdCategoria(resultadoBusca.getString("id_categoria"));
            lancamento.setValor(resultadoBusca.getFloat("valor"));
            lancamento.setOperacao(resultadoBusca.getString("operacao"));
            lancamento.setData(resultadoBusca.getDate("data"));
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
            PreparedStatement sql = conexao.prepareStatement("delete from lancamento where id == ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean insert(Lancamento lancamento){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("insert into lancamento (id, id_conta, id_categoria, valor, operacao, data, descricao) values (?, ?, ?, ?, ?, ?, ?)");
            
            sql.setString(1, lancamento.getId());
            sql.setString(2, lancamento.getIdConta());
            sql.setString(3, lancamento.getIdCategoria());
            sql.setFloat(4, lancamento.getValor());
            sql.setString(5, lancamento.getOperacao());
            sql.setDate(6, (Date) lancamento.getData());
            sql.setString(7, lancamento.getDescricao());
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean update(Lancamento lancamento){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("update conta set id = ?, id_conta = ?, id_categoria = ?, valor = ?, operacao = ?, data = ?, descricao = ? where id == ?");
            sql.setString(1, lancamento.getId());
            sql.setString(2, lancamento.getIdConta());
            sql.setString(3, lancamento.getIdCategoria());
            sql.setFloat(4, lancamento.getValor());
            sql.setString(5, lancamento.getOperacao());
            sql.setDate(6, (Date) lancamento.getData());
            sql.setString(7, lancamento.getDescricao());
            sql.setString(8, lancamento.getId());
            sql.executeUpdate();
            
            resultado = true;
        } catch(SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
}
