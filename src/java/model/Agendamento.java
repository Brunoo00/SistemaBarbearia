package model;

import java.util.ArrayList;
import model.framework.DataAccessObject;

public class Agendamento extends DataAccessObject {
    
    private int id;
    private int usuariosId;
    private int servicosId;
    private String data;
    private int barbeiroId;
    
    public Agendamento() {
        super("agendamentos");
    }
    
    public int getId() {
        return id;
    }
    public int getUsuariosId() {
        return usuariosId;
    }
    public int getServicosId() {
        return servicosId;
    }
    public String getData() {
        return data;
    }
    public int getBarbeiroId() {
        return barbeiroId;
    }
    
    public void setId(int id) {
        this.id = id;
        addChange("id", this.id);
    }
    public void setUsuariosId(int usuariosId) {
        this.usuariosId = usuariosId;
        addChange("usuarios_id", this.usuariosId);
    }
    public void setServicosId(int servicosId) {
        this.servicosId = servicosId;
        addChange("servicos_id", this.servicosId);
    }
    public void setData(String data) {
        this.data = data;
        addChange("data", this.data);
    }
    public void setBarbeiroId(int barbeiroId) {
        this.barbeiroId = barbeiroId;
        addChange("barbeiro_id", this.barbeiroId);
    }
    
    @Override
    protected String getWhereClauseForOneEntity() {
        return " id = " + getId();
    }
    
    @Override
    protected Agendamento copy() {
       Agendamento cp = new Agendamento();
       
       cp.setId( getId() );
       cp.setUsuariosId( getUsuariosId() );
       cp.setServicosId( getServicosId() );
       cp.setData( getData() );
       cp.setBarbeiroId( getBarbeiroId() );
       
       cp.setNovelEntity(false);
       
       return cp;
    }
    
    @Override
    protected DataAccessObject fill(ArrayList<Object> data) {
        id = (int) data.get(0);
        usuariosId = (int) data.get(1);
        servicosId = (int) data.get(2);
        
        if( data.get(3) != null ) {
            this.data = data.get(3).toString();
        } else {
            this.data = null;
        }
        
        barbeiroId = (int) data.get(4);
        return this;
    }
    
}