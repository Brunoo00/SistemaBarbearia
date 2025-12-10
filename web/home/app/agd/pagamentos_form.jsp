<%@page import="model.Pagamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagamento</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        <%@ include file="/home/app/modulos.jsp" %>
        <%
            Pagamento pg = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));
                    pg = new Pagamento();
                    pg.setId(id);
                    pg.load();
                }
            }
        %>
        <h1>Pagamento</h1>
        <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=pagamentos" method="post">
            <label for="id">Id:</label>
            <input type="text" id="id" name ="id" pattern="\d+" title="apenas dígitos" value="<%= (pg != null) ? pg.getId() : ""%>" <%= (pg != null) ? "readonly" : ""%> required><br/>   
            
            <label for="valor">Valor:</label>
            <input type="number" step="0.01" id="valor" name ="valor" title="apenas dígitos" value="<%= (pg != null) ? pg.getValor(): ""%>" required><br/>
            
            <label for="agendamentos_id">Agendamento ID:</label>
            <input type="text" id="agendamentos_id" name ="agendamentos_id" pattern="\d+" title="apenas dígitos" value="<%= (pg != null) ? pg.getAgendamentosId() : ""%>" required><br/>
            
            <input type="submit" name="Salvar" value="Salvar"> 
        </form>
    </body>
</html>