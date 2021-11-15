package model;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import aplicacao.Conta;

@WebServlet(name = "contaDAO", urlPatterns = {"/contaDAO"})

public class contasDAO extends HttpServlet{
    private Connection conexao;
    
    public contasDAO(){
        try{
            conexao = Conexao.criarConexao();
        } catch (SQLException e){
            System.out.println("conexao não realizada");
            System.out.println(e);
        }
    }
    
    public ArrayList<Conta> getList(){
        ArrayList<Conta> resultado = null;
        
        try{
            resultado = new ArrayList<>();
                    
            Statement sql = conexao.createStatement();
            ResultSet resultadoBusca = sql.executeQuery("select * from conta");
            
            while(resultadoBusca.next()){
                Conta conta = new Conta();
                
                conta.setId(resultadoBusca.getInt("id"));
                conta.setIdUsuario(resultadoBusca.getString("id_usuario"));
                conta.setNomeConta(resultadoBusca.getString("nome_conta"));
                conta.setBanco(resultadoBusca.getString("busca"));
                conta.setAgencia(resultadoBusca.getString("agencia"));
                conta.setContaCorrente(resultadoBusca.getString("conta_corrente"));
                
                resultado.add(conta);
            }
            
        } catch(SQLException e){
            System.out.println(e);
        }
        return resultado;
    }
    
    public boolean searchData(Conta conta){
        boolean resultado = false;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from conta where id_usuario = ? and nome_conta = ? and banco = ? and agencia = ? and conta_corrente = ?");
            sql.setString(1, conta.getIdUsuario());
            sql.setString(2, conta.getNomeConta());
            sql.setString(3, conta.getBanco());
            sql.setString(4, conta.getAgencia());
            sql.setString(5, conta.getContaCorrente());
            ResultSet resultadoBusca = sql.executeQuery();
            
            if(resultadoBusca.next()){
                resultado = true;
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        
        return resultado;
    }
    
    public Conta getDados(int id){
        Conta conta = new Conta();
        
        try{
            PreparedStatement sql = conexao.prepareStatement("select * from conta where id == ?");
            sql.setInt(1, id);
            ResultSet resultadoBusca = sql.executeQuery();
            
            conta.setId(resultadoBusca.getInt("id"));
            conta.setIdUsuario(resultadoBusca.getString("id_usuario"));
            conta.setNomeConta(resultadoBusca.getString("nome_conta"));
            conta.setBanco(resultadoBusca.getString("banco"));
            conta.setAgencia(resultadoBusca.getString("agencia"));
            conta.setContaCorrente(resultadoBusca.getString("conta_corrente"));
            
        } catch(SQLException e){
            System.out.println(e);
            conta = null;
        }
        
        return conta;
    }

    public boolean delete(int id){
        boolean resultado;
        try{
            PreparedStatement sql = conexao.prepareStatement("delete from conta where id == ?");
            sql.setInt(1, id);
            sql.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
    
    public boolean insert(Conta conta){
        boolean resultado;
        
        try{
            String sql = "INSERT INTO contas (id_usuario, nome_conta, banco, agencia, conta_corrente) VALUES (?,?,?,?,?)";
            
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, conta.getIdUsuario());
            ps.setString(2, conta.getNomeConta());
            ps.setString(3, conta.getBanco());
            ps.setString(4, conta.getAgencia());
            ps.setString(5, conta.getContaCorrente());
            
            ps.executeUpdate();
            
            resultado = true;
        } catch (SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;        
    }
    
    public boolean update(Conta conta){
        boolean resultado;
        
        try{
            PreparedStatement sql = conexao.prepareStatement("update conta set id = ?, id_usuario = ?, nome_conta = ?, banco = ?, agencia = ?, conta_corrente = ? where id == ?");
            sql.setInt(1, conta.getId());
            sql.setString(2, conta.getIdUsuario());
            sql.setString(3, conta.getNomeConta());
            sql.setString(4, conta.getBanco());
            sql.setString(5, conta.getAgencia());
            sql.setString(6, conta.getContaCorrente());
            sql.setInt(7, conta.getId());
            sql.executeUpdate();
            
            resultado = true;
        } catch(SQLException e){
            System.out.println(e);
            
            resultado = false;
        }
        
        return resultado;
    }
}
