package aplicacao;

import java.util.Date;

public class Lancamento {
    private String id;
    private String id_conta;
    private String id_categoria;
    private float valor;
    private String operacao;
    private Date data;
    private String descricao;
    
    public String getId(){
        return this.id;
    }
    
    public String getIdConta(){
        return this.id_conta;
    }
    
    public String getIdCategoria(){
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
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setIdConta(String id_conta){
        this.id_conta = id_conta;
    }
    
    public void setIdCategoria(String id_categoria){
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
