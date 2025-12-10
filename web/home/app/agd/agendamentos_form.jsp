<%@page import="model.Agendamento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendamento</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        <%@ include file="/home/app/modulos.jsp" %>
        <%
            Agendamento ag = null;
            String action = request.getParameter("action");
            if (action == null) {
                action = "create";
            } else {
                if (action.equals("update")) {
                    int id = Integer.valueOf(request.getParameter("id"));
                    ag = new Agendamento();
                    ag.setId(id);
                    ag.load();
                }
            }
        %>
        <h1>Agendamento</h1>
        <form action="<%= request.getContextPath()%>/home?action=<%= action%>&task=agendamentos" method="post">
            <label for="id">Id:</label>
            <input type="text" id="id" name ="id" pattern="\d+" title="apenas dígitos" value="<%= (ag != null) ? ag.getId() : ""%>" <%= (ag != null) ? "readonly" : ""%> required><br/>   
            
            <label for="usuarios_id">Cliente ID:</label>
            <input type="text" id="usuarios_id" name ="usuarios_id" pattern="\d+" title="apenas dígitos" value="<%= (ag != null) ? ag.getUsuariosId() : ""%>" required><br/>
            
            <label for="servicos_id">Serviço ID:</label>
            <input type="text" id="servicos_id" name ="servicos_id" pattern="\d+" title="apenas dígitos" value="<%= (ag != null) ? ag.getServicosId() : ""%>" required><br/>
            
            <label for="data">Data:</label>
            <input type="date" id="data" name ="data" value="<%= ((ag != null) && (ag.getData() != null)) ? ag.getData() : ""%>" required><br/>
            
            <label for="barbeiro_id">Barbeiro ID:</label>
            <input type="text" id="barbeiro_id" name ="barbeiro_id" pattern="\d+" title="apenas dígitos" value="<%= (ag != null) ? ag.getBarbeiroId() : ""%>" required><br/>
            
            <input type="submit" name="Salvar" value="Salvar"> 
        </form>
    </body>
</html>