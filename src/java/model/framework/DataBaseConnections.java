package model.framework;

import controller.AppConfig;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnections {
    
    private ArrayList<Connection> pool;
    
    private static DataBaseConnections singleton;
    
    private DataBaseConnections() {
        pool = new ArrayList<>();
        
        /*
         * CORREÇÃO DE BUG: Carregamento explícito do driver JDBC
         * 
         * PROBLEMA IDENTIFICADO:
         * Ao fazer login pela primeira vez, ocorria o erro:
         * "No suitable driver found for jdbc:mysql://localhost:3306/barbearia_bd"
         * Na segunda tentativa de login, funcionava normalmente.
         * 
         * CAUSA RAIZ:
         * O driver JDBC do MySQL não estava sendo carregado na memória ANTES da 
         * primeira tentativa de conexão. Quando o ContextListener inicializa esta
         * classe Singleton, o driver ainda não estava registrado no DriverManager.
         * 
         * SOLUÇÃO IMPLEMENTADA:
         * Adicionado o carregamento explícito do driver através do método 
         * Class.forName("com.mysql.cj.jdbc.Driver") no construtor da classe.
         * Isso garante que o driver seja registrado no DriverManager ASSIM QUE
         * o servidor inicializa, ANTES de qualquer tentativa de login/conexão.
         * 
         * RESULTADO:
         * Agora o driver é carregado durante a inicialização do servidor via
         * ContextListener, eliminando o erro no primeiro login.
         * 
         * OBSERVAÇÃO:
         * Em versões modernas do JDBC (4.0+), o carregamento automático deveria
         * funcionar, mas em ambientes Jakarta EE/Servlet, o carregamento explícito
         * se mostrou necessário para garantir a inicialização correta.
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✓ Driver MySQL carregado com sucesso!");
        } catch (ClassNotFoundException ex) {
            System.err.println("✗ ERRO CRÍTICO: Driver MySQL não encontrado!");
            System.err.println("  Certifique-se de que o mysql-connector-j está nas bibliotecas do projeto.");
            ex.printStackTrace();
        }
    }
    
    public static DataBaseConnections getInstance() {
        if( singleton == null ) {
            singleton = new DataBaseConnections();
        }        
        return singleton;
    }
    
    public synchronized Connection getConnection() throws SQLException {
        Connection con;        
        con = DriverManager.getConnection(
                AppConfig.getInstance().getUrl(),
                AppConfig.getInstance().getUser(),
                AppConfig.getInstance().getPassword() );
        pool.add(con);        
        return con;
    }
    
    public synchronized void closeConnection(Connection con) throws SQLException {
        if( con != null &&
                !con.isClosed() && 
                pool.contains(con) ) {
            con.close();
            pool.remove(con);
        }
    }
    
    public synchronized void closeAllConnections() throws SQLException {
        for( Connection con : pool ) {
            if( con != null &&
                    !con.isClosed() ) {
                con.close();
            }
        }
        pool.clear();
    }
}