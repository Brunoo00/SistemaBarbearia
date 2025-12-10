package model;

import java.util.ArrayList;
import model.framework.DataAccessObject;

public class Pagamento extends DataAccessObject {
    
    private int id;
    private float valor;
    private int agendamentosId;
    
    public Pagamento() {
        super("pagamentos");
    }
    
    public int getId() {
        return id;
    }
    public float getValor() {
        return valor;
    }
    public int getAgendamentosId() {
        return agendamentosId;
    }
    
    public void setId(int id) {
        this.id = id;
        addChange("id", this.id);
    }
    public void setValor(float valor) {
        this.valor = valor;
        addChange("valor", this.valor);
    }
    public void setAgendamentosId(int agendamentosId) {
        this.agendamentosId = agendamentosId;
        addChange("agendamentos_id", this.agendamentosId);
    }
    
    @Override
    protected String getWhereClauseForOneEntity() {
        return " id = " + getId();
    }
    
    @Override
    protected Pagamento copy() {
       Pagamento cp = new Pagamento();
       
       cp.setId( getId() );
       cp.setValor( getValor() );
       cp.setAgendamentosId( getAgendamentosId() );
       
       cp.setNovelEntity(false);
       
       return cp;
    }
    
    @Override
    protected DataAccessObject fill(ArrayList<Object> data) {
        id = (int) data.get(0);
        valor = (float) data.get(1);
        agendamentosId = (int) data.get(2);
        return this;
    }
    
}