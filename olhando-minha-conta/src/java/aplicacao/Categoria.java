
package aplicacao;

public class Categoria {
    private int id;
    private String descricao;
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
}
