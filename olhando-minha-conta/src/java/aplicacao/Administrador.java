package aplicacao;


public class Administrador {
    private int id;
    private String nome;
    private String cpf;
    private String senha;

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
}
