<%@page import="model.Pagamento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagamentos</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        
        <%@ include file="/home/app/modulos.jsp" %>
        
        <% ArrayList<Pagamento> dados = new Pagamento().getAllTableEntities(); %>
        
        <h1>Pagamento</h1>
        
        <table>
            
            <tr>
                <th>ID</th>
                <th>Valor R$</th>
                <th>Agendamento ID</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( Pagamento pg : dados ) { %>
            <tr>
                <td><%= pg.getId() %></td>
                <td><%= pg.getValor() %></td>
                <td><%= pg.getAgendamentosId() %></td>
                
                
                <td><a href="<%= request.getContextPath()  %>/home/app/agd/pagamentos_form.jsp?action=update&id=<%= pg.getId()%>" >Alterar</a></td>
                
                <td><a href="<%= request.getContextPath() %>/home?action=delete&id=<%= pg.getId()%>&task=pagamentos" onclick="return confirm('Deseja realmente excluir Pagamento <%= pg.getId()%> ?')">Excluir</a></td>
            </tr>
            <% } %>
            
        </table>
        
            <a href="<%= request.getContextPath()  %>/home/app/agd/pagamentos_form.jsp?action=create" >Adicionar</a>
    </body>
</html>