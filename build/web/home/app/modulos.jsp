<%@page import="model.TipoUsuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    String usuarioLogado = (String) session.getAttribute("usuario");
    TipoUsuario tipoUsuarioLogado = (TipoUsuario) session.getAttribute("tipo_usuario");
    
    if( ( usuarioLogado == null ) || ( tipoUsuarioLogado == null ) ) {
        
        response.sendRedirect( request.getContextPath() + "/home/login.jsp" );
        
    }
    
%>
<h1>Menu - Sistema de Barbearia</h1>
<menu>
    
    <li><a href="<%= request.getContextPath() %>/home/app/menu.jsp">Menu</a></li>
    
    <%if( tipoUsuarioLogado.getModuloAdmin().equals("S") ) {%>  
    
        <li><a href="<%= request.getContextPath() %>/home/app/adm/tipousuario.jsp">Tipo Usuário</a></li>
        <li><a href="<%= request.getContextPath() %>/home/app/adm/usuarios.jsp">Usuários</a></li>
        <li><a href="<%= request.getContextPath() %>/home/app/adm/servicos.jsp">Serviços</a></li>
        <li><a href="<%= request.getContextPath() %>/home/app/adm/planos.jsp">Planos</a></li>
    
    <%}%>
    
    <%if( tipoUsuarioLogado.getModuloAgendamento().equals("S") ) {%>  
    
        <li><a href="<%= request.getContextPath() %>/home/app/agd/agendamentos.jsp">Agendamentos</a></li>
        <li><a href="<%= request.getContextPath() %>/home/app/agd/pagamentos.jsp">Pagamentos</a></li>
   
    <%}%>
    
    <li><a href="<%= request.getContextPath() %>/home?task=logout">Logout <%= usuarioLogado %></a></li>
    
</menu>