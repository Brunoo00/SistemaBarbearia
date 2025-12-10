package model;

import java.util.ArrayList;
import model.framework.DataAccessObject;

public class TipoUsuario extends DataAccessObject {
    
    private int id;
    private String nome;
    private String moduloAdmin;
    private String moduloAgendamento;
    
    public TipoUsuario() {
        super("tipo_usuario");
    }
    
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getModuloAdmin() {
        return moduloAdmin;
    }
    public String getModuloAgendamento() {
        return moduloAgendamento;
    }
    
    public void setId(int id) {
        this.id = id;
        addChange("id", this.id);
    }
    public void setNome(String nome) {
        this.nome = nome;
        addChange("nome", this.nome);
    }
    public void setModuloAdmin(String moduloAdmin) {
        this.moduloAdmin = moduloAdmin;
        addChange("modulo_admin", this.moduloAdmin);
    }
    public void setModuloAgendamento(String moduloAgendamento) {
        this.moduloAgendamento = moduloAgendamento;
        addChange("modulo_agendamento", this.moduloAgendamento);
    }
    
    @Override
    protected String getWhereClauseForOneEntity() {
        return " id = " + getId();
    }
    
    @Override
    protected DataAccessObject fill(ArrayList<Object> data) {
        id = (int) data.get(0);
        nome = (String) data.get(1);
        moduloAdmin = (String) data.get(2);
        moduloAgendamento = (String) data.get(3);
        return this;
    }
    
    @Override
    protected TipoUsuario copy() {        
        TipoUsuario cp = new TipoUsuario();
        
        cp.setId( getId() );
        cp.setNome( getNome() );
        cp.setModuloAdmin( getModuloAdmin() );
        cp.setModuloAgendamento( getModuloAgendamento() );
        
        cp.setNovelEntity(false);
        
        return cp;
    }
    
    @Override
    public String toString() {
        return "(" + getId() + "," + getNome() + "," + getModuloAdmin() + "," + getModuloAgendamento() + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if( obj instanceof TipoUsuario ) {
            TipoUsuario aux = (TipoUsuario) obj;
            if( getId() == aux.getId() ) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
}