package model;

import java.util.ArrayList;
import model.framework.DataAccessObject;

public class Plano extends DataAccessObject {
    
    private int id;
    private String nome;
    private String descricao;
    private float valor;
    
    public Plano() {
        super("planos");
    }
    
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public float getValor() {
        return valor;
    }
    
    public void setId(int id) {
        this.id = id;
        addChange("id", this.id);
    }
    public void setNome(String nome) {
        this.nome = nome;
        addChange("nome", this.nome);
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
        addChange("descricao", this.descricao);
    }
    public void setValor(float valor) {
        this.valor = valor;
        addChange("valor", this.valor);
    }
    
    @Override
    protected String getWhereClauseForOneEntity() {
        return " id = " + getId();
    }
    
    @Override
    protected Plano copy() {
       Plano cp = new Plano();
       
       cp.setId( getId() );
       cp.setNome( getNome() );
       cp.setDescricao( getDescricao() );
       cp.setValor( getValor() );
       
       cp.setNovelEntity(false);
       
       return cp;
    }
    
    @Override
    protected DataAccessObject fill(ArrayList<Object> data) {
        id = (int) data.get(0);
        nome = (String) data.get(1);
        descricao = (String) data.get(2);
        valor = (float) data.get(3);
        return this;
    }
    
}