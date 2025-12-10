<%@page import="model.Servico"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Serviço</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        <%@ include file="/home/app/modulos.jsp" %>
        <%
            Servico sv = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));
                    sv = new Servico();
                    sv.setId(id);
                    sv.load();
                }
            }
        %>
        <h1>Serviço</h1>
        <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=servicos" method="post">
            <label for="id">Id:</label>
            <input type="text" id="id" name ="id" pattern="\d+" title="apenas dígitos" value="<%= (sv != null) ? sv.getId() : ""%>" <%= (sv != null) ? "readonly" : ""%> required><br/>   
            <label for="nome">Nome:</label>
            <input type="text" id="nome" name ="nome" value="<%= ((sv != null) && (sv.getNome() != null)) ? sv.getNome() : ""%>" required><br/>
            
            <label for="descricao">Descrição:</label>
            <input type="text" id="descricao" name="descricao" value="<%= ((sv != null) && (sv.getDescricao() != null)) ? sv.getDescricao() : ""%>" required><br/>            
            <label for="valor">Valor:</label>
            <input type="number" step="0.01" id="valor" name ="valor" title="apenas dígitos" value="<%= (sv != null) ? sv.getValor(): ""%>"required><br/>
            
            <input type="submit" name="Salvar" value="Salvar"> 
        </form>
    </body>
</html>