<%@page import="model.Agendamento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agendamentos</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        
        <%@ include file="/home/app/modulos.jsp" %>
        
        <% ArrayList<Agendamento> dados = new Agendamento().getAllTableEntities(); %>
        
        <h1>Agendamento</h1>
        
        <table>
            
            <tr>
                <th>ID</th>
                <th>Cliente ID</th>
                <th>Serviço ID</th>
                <th>Data</th>
                <th>Barbeiro ID</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( Agendamento ag : dados ) { %>
            <tr>
                <td><%= ag.getId() %></td>
                <td><%= ag.getUsuariosId() %></td>
                <td><%= ag.getServicosId() %></td>
                <td><%= ag.getData() %></td>
                <td><%= ag.getBarbeiroId() %></td>
                
                
                <td><a href="<%= request.getContextPath()  %>/home/app/agd/agendamentos_form.jsp?action=update&id=<%= ag.getId()%>" >Alterar</a></td>
                
                <td><a href="<%= request.getContextPath() %>/home?action=delete&id=<%= ag.getId()%>&task=agendamentos" onclick="return confirm('Deseja realmente excluir Agendamento <%= ag.getId()%> ?')">Excluir</a></td>
            </tr>
            <% } %>
            
        </table>
        
            <a href="<%= request.getContextPath()  %>/home/app/agd/agendamentos_form.jsp?action=create" >Adicionar</a>
    </body>
</html>