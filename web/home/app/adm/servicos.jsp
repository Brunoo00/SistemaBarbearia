<%@page import="model.Servico"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Serviços</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
    </head>
    <body>
        
        <%@ include file="/home/app/modulos.jsp" %>
        
        <% ArrayList<Servico> dados = new Servico().getAllTableEntities(); %>
        
        <h1>Serviço</h1>
        
        <table>
            
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Descrição</th>
                <th>Valor R$</th>
                <th></th>
                <th></th>
            </tr>
            
            <% for( Servico sv : dados ) { %>
            <tr>
                <td><%= sv.getId() %></td>
                <td><%= sv.getNome() %></td>
                <td><%= sv.getDescricao() %></td>
                <td><%= sv.getValor() %></td>
                
                
                <td><a href="<%= request.getContextPath()  %>/home/app/adm/servicos_form.jsp?action=update&id=<%= sv.getId()%>" >Alterar</a></td>
                
                <td><a href="<%= request.getContextPath() %>/home?action=delete&id=<%= sv.getId()%>&task=servicos" onclick="return confirm('Deseja realmente excluir Serviço <%= sv.getId()%> (<%= sv.getNome() %>) ?')">Excluir</a></td>
            </tr>
            <% } %>
            
        </table>
        
            <a href="<%= request.getContextPath()  %>/home/app/adm/servicos_form.jsp?action=create" >Adicionar</a>
    </body>
</html>