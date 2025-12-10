<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuário</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        <%@ include file="/home/app/modulos.jsp" %>
        <%
            Usuario us = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));
                    us = new Usuario();
                    us.setId(id);
                    us.load();
                }
            }
        %>
        <h1>Usuário</h1>
        <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=usuarios" method="post">
            <label for="id">Id:</label>
            <input type="text" id="id" name ="id" pattern="\d+" title="apenas dígitos" value="<%= (us != null) ? us.getId() : ""%>" <%= (us != null) ? "readonly" : ""%> required><br/>   
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name ="nome" value="<%= ((us != null) && (us.getNome() != null)) ? us.getNome() : ""%>" ><br/>
            
            <label for="telefone">Telefone:</label>
            <input type="text" id="telefone" name ="telefone" pattern="\(\d{2}\)\s?\d{4,5}-\d{4}" title="(DD) DDDDD-DDDD ou (DD) DDDD-DDDD" value="<%= ((us != null) && (us.getTelefone() != null)) ? us.getTelefone() : ""%>"><br/> 
            
            <label for="senha">Senha:</label>
            <input type="password" id="senha" name ="senha" value="<%= ((us != null)) ? us.getSenha() : ""%>" required><br/>
            
            <label for="tipo_usuario_id">Tipo Usuário ID:</label>
            <input type="text" id="tipo_usuario_id" name ="tipo_usuario_id" pattern="\d+" title="apenas dígitos" value="<%= ((us != null)) ? us.getTipoUsuarioId() : ""%>" required><br/>
            
            <input type="submit" name="Salvar" value="Salvar"> 
        </form>
        
    </body>
</html>