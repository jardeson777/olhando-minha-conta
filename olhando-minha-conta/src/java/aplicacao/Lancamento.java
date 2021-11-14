package aplicacao;

import java.util.Date;

public class Lancamento {
    private int id;
    private int id_conta;
    private int id_categoria;
    private float valor;
    private String operacao;
    private Date data;
    private String descricao;
    
    public int getId(){
        return this.id;
    }
    
    public int getIdConta(){
        return this.id_conta;
    }
    
    public int getIdCategoria(){
        return this.id_categoria;
    }
    
    public float getValor(){
        return this.valor;
    }
    
    public String getOperacao(){
        return this.operacao;
    }
    
    public Date getData(){
        return this.data;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setIdConta(int id_conta){
        this.id_conta = id_conta;
    }
    
    public void setIdCategoria(int id_categoria){
        this.id_categoria = id_categoria;
    }
    
    public void setValor(float valor){
        this.valor = valor;
    }
    
    public void setOperacao(String operacao){
        this.operacao = operacao;
    }
    
    public void setData(Date data){
        this.data = data;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
