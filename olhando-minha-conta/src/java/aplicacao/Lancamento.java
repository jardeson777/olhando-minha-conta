package aplicacao;

import java.util.Date;

public class Lancamento {
    private int id;
    private String id_conta;
    private String id_categoria;
    private String valor;
    private String operacao;
    private String data;
    private String descricao;
    
    public int getId(){
        return this.id;
    }
    
    public String getIdConta(){
        return this.id_conta;
    }
    
    public String getIdCategoria(){
        return this.id_categoria;
    }
    
    public String getValor(){
        return this.valor;
    }
    
    public String getOperacao(){
        return this.operacao;
    }
    
    public String getData(){
        return this.data;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setIdConta(String id_conta){
        this.id_conta = id_conta;
    }
    
    public void setIdCategoria(String id_categoria){
        this.id_categoria = id_categoria;
    }
    
    public void setValor(String valor){
        this.valor = valor;
    }
    
    public void setOperacao(String operacao){
        this.operacao = operacao;
    }
    
    public void setData(String data){
        this.data = data;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
