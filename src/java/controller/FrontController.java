package controller;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logtrack.ExceptionLogTrack;
import model.Agendamento;
import model.Pagamento;
import model.Plano;
import model.Servico;
import model.TipoUsuario;
import model.Usuario;

public class FrontController extends HttpServlet {  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        
        String task = request.getParameter("task");
        if( task == null ) {
            task = "";
        }
        
        try {
        
            switch (task) {
                case "tipousuario": doGetTipoUsuario(request, response); break;
                case "usuarios": doGetUsuarios(request, response); break;
                case "servicos": doGetServicos(request, response); break;
                case "planos": doGetPlanos(request, response); break;
                case "agendamentos": doGetAgendamentos(request, response); break;
                case "pagamentos": doGetPagamentos(request, response); break;
                case "logout": doGetLogout(request, response); break;
                default: doDefault(request, response);
            }
        
        } catch(SQLIntegrityConstraintViolationException ex) {
            tratarErroIntegridade(request, response, ex);
        } catch(Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            tratarErroGenerico(request, response, ex);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String task = request.getParameter("task");
        if( task == null ) {
            task = "";
        }
        
        try {
        
            switch (task) {
                case "tipousuario": doPostTipoUsuario(request, response); break;
                case "usuarios": doPostUsuarios(request, response); break;
                case "servicos": doPostServicos(request, response); break;
                case "planos": doPostPlanos(request, response); break;
                case "agendamentos": doPostAgendamentos(request, response); break;
                case "pagamentos": doPostPagamentos(request, response); break;
                case "login": doPostLogin(request, response); break;
                default: doDefault(request, response);
            }
        
        } catch(SQLIntegrityConstraintViolationException ex) {
            tratarErroIntegridade(request, response, ex);
        } catch(Exception ex) {
            ExceptionLogTrack.getInstance().addLog(ex);
            tratarErroGenerico(request, response, ex);
        }
        
    }
    
    
    
    private void doGetTipoUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        if( ( action != null ) &&
                action.equals("delete") ) {
            
            int id = Integer.valueOf( request.getParameter("id") );
            
            TipoUsuario tp = new TipoUsuario();
            tp.setId(id);
            
            tp.delete();
        }
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/tipousuario.jsp" );
        
    }
    
    private void doGetUsuarios(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        if( ( action != null ) &&
                action.equals("delete") ) {
            
            int id = Integer.valueOf( request.getParameter("id") );
            
            Usuario us = new Usuario();
            us.setId(id);
            
            us.delete();
            
        }
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/usuarios.jsp");
        
    }
    
    private void doGetServicos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        if( ( action != null ) &&
                action.equals("delete") ) {
            
            int id = Integer.valueOf( request.getParameter("id") );
            
            Servico sv = new Servico();
            sv.setId(id);
            
            sv.delete();
            
        }
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/servicos.jsp");
        
    }
    
    private void doGetPlanos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        if( ( action != null ) &&
                action.equals("delete") ) {
            
            int id = Integer.valueOf( request.getParameter("id") );
            
            Plano pl = new Plano();
            pl.setId(id);
            
            pl.delete();
            
        }
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/planos.jsp");
        
    }
    
    private void doGetAgendamentos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        if( ( action != null ) &&
                action.equals("delete") ) {
            
            int id = Integer.valueOf( request.getParameter("id") );
            
            Agendamento ag = new Agendamento();
            ag.setId(id);
            
            ag.delete();
            
        }
        
        response.sendRedirect( request.getContextPath() + "/home/app/agd/agendamentos.jsp");
        
    }
    
    private void doGetPagamentos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        if( ( action != null ) &&
                action.equals("delete") ) {
            
            int id = Integer.valueOf( request.getParameter("id") );
            
            Pagamento pg = new Pagamento();
            pg.setId(id);
            
            pg.delete();
            
        }
        
        response.sendRedirect( request.getContextPath() + "/home/app/agd/pagamentos.jsp");
        
    }
    
    private void doGetLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
       HttpSession sessao = request.getSession(false);
       if( sessao != null ) {
           sessao.removeAttribute("usuario");
           sessao.removeAttribute("tipo_usuario");
           
           sessao.invalidate();
       }
       
       response.sendRedirect( request.getContextPath() +  "/home/login.jsp" );
        
    }
    
    
    
    private void doPostTipoUsuario(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        String action = request.getParameter("action");
        int id = Integer.valueOf( request.getParameter("id") );
        
        String nome = request.getParameter("nome");
        
        String moduloAdmin = request.getParameter("modulo_admin");
        if( moduloAdmin == null ) {
            moduloAdmin = "N";
        }
        
        String moduloAgendamento = request.getParameter("modulo_agendamento");
        if( moduloAgendamento == null ) {
            moduloAgendamento = "N";
        }

        
        TipoUsuario tp = new TipoUsuario();
        tp.setId(id);
        if( action.equals("update") ) tp.load();
        tp.setNome(nome);
        tp.setModuloAdmin(moduloAdmin);
        tp.setModuloAgendamento(moduloAgendamento);
        tp.save();
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/tipousuario.jsp");
        
    }
    
    private void doPostUsuarios(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        String action = request.getParameter("action");
        int id = Integer.valueOf( request.getParameter("id") );
        
        String nome = request.getParameter("nome");
        
        String telefone = request.getParameter("telefone");
        
        String senha = request.getParameter("senha");
        
        int tipoUsuarioId = Integer.valueOf( request.getParameter("tipo_usuario_id") );
        
        Usuario us = new Usuario();
        us.setId(id);
        if( action.equals("update") ) us.load();
        us.setNome(nome);
        us.setTelefone(telefone);
        us.setSenha(senha);
        us.setTipoUsuarioId(tipoUsuarioId);
        
        us.save();
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/usuarios.jsp");
        
    } 
    
    private void doPostServicos(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        String action = request.getParameter("action");
        int id = Integer.parseInt( request.getParameter("id") );
        
        String nome = request.getParameter("nome");
        
        String descricao = request.getParameter("descricao");
        
        float valor = Float.parseFloat( request.getParameter("valor") );
        
        Servico sv  = new Servico();
        sv.setId(id);
        if( action.equals("update") ) sv.load();
        sv.setNome(nome);
        sv.setDescricao(descricao);
        sv.setValor(valor);
        
        sv.save();
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/servicos.jsp");
        
    } 

    private void doPostPlanos(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        String action = request.getParameter("action");
        int id = Integer.parseInt( request.getParameter("id") );
        
        String nome = request.getParameter("nome");
        
        String descricao = request.getParameter("descricao");
        
        float valor = Float.parseFloat( request.getParameter("valor") );
        
        Plano pl  = new Plano();
        pl.setId(id);
        if( action.equals("update") ) pl.load();
        pl.setNome(nome);
        pl.setDescricao(descricao);
        pl.setValor(valor);
        
        pl.save();
        
        response.sendRedirect( request.getContextPath() + "/home/app/adm/planos.jsp");
        
    }
    
    private void doPostAgendamentos(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        String action = request.getParameter("action");
        int id = Integer.parseInt( request.getParameter("id") );
        
        int usuariosId = Integer.parseInt( request.getParameter("usuarios_id") );
        
        int servicosId = Integer.parseInt( request.getParameter("servicos_id") );
        
        String data = request.getParameter("data");
        
        int barbeiroId = Integer.parseInt( request.getParameter("barbeiro_id") );
        
        Agendamento ag  = new Agendamento();
        ag.setId(id);
        if( action.equals("update") ) ag.load();
        ag.setUsuariosId(usuariosId);
        ag.setServicosId(servicosId);
        ag.setData(data);
        ag.setBarbeiroId(barbeiroId);
        
        ag.save();
        
        response.sendRedirect( request.getContextPath() + "/home/app/agd/agendamentos.jsp");
        
    }
    
    private void doPostPagamentos(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        String action = request.getParameter("action");
        int id = Integer.parseInt( request.getParameter("id") );
        
        float valor = Float.parseFloat( request.getParameter("valor") );
        
        int agendamentosId = Integer.parseInt( request.getParameter("agendamentos_id") );
        
        Pagamento pg  = new Pagamento();
        pg.setId(id);
        if( action.equals("update") ) pg.load();
        pg.setValor(valor);
        pg.setAgendamentosId(agendamentosId);
        
        pg.save();
        
        response.sendRedirect( request.getContextPath() + "/home/app/agd/pagamentos.jsp");
        
    }
    
    private void doPostLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        
        int id = Integer.valueOf( request.getParameter("id") );        
        String senha = request.getParameter("senha");
        
        Usuario usuarioTry = new Usuario();
        usuarioTry.setId(id);
        usuarioTry.setSenha(senha);
        
        Usuario usuario = new Usuario();
        usuario.setId(id);        
        boolean status = usuario.load();
        
        if( ( status == true ) &&
              ( usuario.getSenha().equals( usuarioTry.getSenha() ) ) ) {
            
            HttpSession sessao = request.getSession(false);
            if( sessao != null ) {
                sessao.removeAttribute("usuario");
                sessao.removeAttribute("tipo_usuario");
           
                sessao.invalidate();
            }
            
            sessao = request.getSession(true);
            
            sessao.setAttribute( "usuario", "(" + usuario.getNome() + ", " + usuario.getId() + ")" );
            
            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setId( usuario.getTipoUsuarioId() );
            tipoUsuario.load();
            
            sessao.setAttribute( "tipo_usuario", tipoUsuario );
            
            sessao.setMaxInactiveInterval( 60 * 60 );
            
            Cookie cookie = new Cookie( "id", String.valueOf(id) );
            cookie.setMaxAge( 60 * 10 );
            response.addCookie(cookie);
            
            response.sendRedirect( request.getContextPath() +  "/home/app/menu.jsp" );
            
        } else {
            
            request.setAttribute("msg", "id e/ou senha incorreto(s)");
            request.getRequestDispatcher( "/home/login.jsp" ).forward(request, response);
            
        }
        
        
    } 
    
    
    private void tratarErroIntegridade(HttpServletRequest request, HttpServletResponse response, Exception ex) throws ServletException, IOException {
        
        ExceptionLogTrack.getInstance().addLog(ex);
        
        String mensagem = "Não é possível excluir este registro porque existem outros registros relacionados a ele. ";
        mensagem += "Por exemplo: não é possível excluir um usuário que tem agendamentos vinculados.";
        
        request.setAttribute("mensagem_erro", mensagem);
        request.getRequestDispatcher("/home/erro.jsp").forward(request, response);
    }
    
    private void tratarErroGenerico(HttpServletRequest request, HttpServletResponse response, Exception ex) throws ServletException, IOException {
        
        String mensagem = "Ocorreu um erro inesperado: " + ex.getMessage();
        
        request.setAttribute("mensagem_erro", mensagem);
        request.getRequestDispatcher("/home/erro.jsp").forward(request, response);
    }
    
        
    private void doDefault(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        response.sendRedirect( request.getContextPath() + "/home/login.jsp" );
        
    }
    
    
}