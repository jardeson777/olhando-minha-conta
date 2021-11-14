package aplicacao;

public class Conta {
    private int id;
    private int id_usuario;
    private String nome_conta;
    private String banco;
    private String agencia;
    private String conta_corrente;
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setIdUsuario(int id_usuario){
        this.id_usuario = id_usuario;
    }
    
    public void setNomeConta(String nome_conta){
        this.nome_conta = nome_conta;
    }
    
    public void setBanco(String banco){
        this.banco = banco;
    }
    
    public void setAgencia(String agencia){
        this.banco = agencia;
    }
    
    public void setContaCorrente(String conta_corrente){
        this.conta_corrente = conta_corrente;
    }
    
    public int getId(){
        return this.id;
    }
    
    public int getIdUsuario(){
        return this.id_usuario;
    }
    
    public String getNomeConta(){
        return this.nome_conta;
    }
    
    public String getBanco(){
        return this.banco;
    }
    
    public String getAgencia(){
        return this.agencia;
    }
    
    public String getContaCorrente(){
        return this.conta_corrente;
    }
}
