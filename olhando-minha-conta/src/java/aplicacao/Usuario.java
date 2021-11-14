package aplicacao;

public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private String senha;
    private String suspenso;
    
    public int getId(){
        return this.id;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public String getCpf(){
        return this.cpf;
    }
    
    public String getSenha(){
        return this.senha;
    }
    
    public String getSuspenso(){
        return this.suspenso;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setCpf(String cpf){
        this.cpf = cpf;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public void setSuspenso(String suspenso){
        this.suspenso = suspenso;
    }
}
