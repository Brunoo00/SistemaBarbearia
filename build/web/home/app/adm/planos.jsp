<%@page import="model.Plano"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Planos</title>
    </head>
    <body>
        
        <%@ include file="/home/app/modulos.jsp" %>
        
        <% ArrayList<Plano> dados = new Plano().getAllTableEntities(); %>
        
        <h1>Plano</h1>
        
        <table>
            
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Valor R$</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( Plano pl : dados ) { %>
            <tr>
                <td><%= pl.getId() %></td>
                <td><%= pl.getNome() %></td>
                <td><%= pl.getDescricao() %></td>
                <td><%= pl.getValor() %></td>
                
                
                <td><a href="<%= request.getContextPath()  %>/home/app/adm/planos_form.jsp?action=update&id=<%= pl.getId()%>" >Alterar</a></td>
                
                <td><a href="<%= request.getContextPath() %>/home?action=delete&id=<%= pl.getId()%>&task=planos" onclick="return confirm('Deseja realmente excluir Plano <%= pl.getId()%> (<%= pl.getNome() %>) ?')">Excluir</a></td>
            </tr>
            <% } %>
            
        </table>
        
            <a href="<%= request.getContextPath()  %>/home/app/adm/planos_form.jsp?action=create" >Adicionar</a>
    </body>
</html>