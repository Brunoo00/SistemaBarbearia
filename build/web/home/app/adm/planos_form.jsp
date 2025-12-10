<%@page import="model.Plano"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Plano</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        <%@ include file="/home/app/modulos.jsp" %>
        <%
            Plano pl = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));
                    pl = new Plano();
                    pl.setId(id);
                    pl.load();
                }
            }
        %>
        <h1>Plano</h1>
        <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=planos" method="post">
            <label for="id">Id:</label>
            <input type="text" id="id" name ="id" pattern="\d+" title="apenas dígitos" value="<%= (pl != null) ? pl.getId() : ""%>" <%= (pl != null) ? "readonly" : ""%> required><br/>   
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name ="nome" value="<%= ((pl != null) && (pl.getNome() != null)) ? pl.getNome() : ""%>" required><br/>
            
            <label for="descricao">Descrição:</label>
            <input type="text" id="descricao" name="descricao" value="<%= ((pl != null) && (pl.getDescricao() != null)) ? pl.getDescricao() : ""%>" required><br/>            
            <label for="valor">Valor:</label>
            <input type="number" step="0.01" id="valor" name ="valor" title="apenas dígitos" value="<%= (pl != null) ? pl.getValor(): ""%>"required><br/>
            
            <input type="submit" name="Salvar" value="Salvar"> 
        </form>
    </body>
</html>