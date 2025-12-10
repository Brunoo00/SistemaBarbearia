<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro - Sistema de Barbearia</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                padding: 20px;
            }
            .erro-container {
                max-width: 600px;
                margin: 50px auto;
                background: white;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0,0,0,0.1);
                border-left: 5px solid #dc3545;
            }
            h1 {
                color: #dc3545;
                margin-top: 0;
            }
            .erro-mensagem {
                background-color: #f8d7da;
                color: #721c24;
                padding: 15px;
                border-radius: 4px;
                margin: 20px 0;
                border: 1px solid #f5c6cb;
            }
            .botao-voltar {
                display: inline-block;
                background-color: #007bff;
                color: white;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 4px;
                margin-top: 20px;
            }
            .botao-voltar:hover {
                background-color: #0056b3;
            }
        </style>
    </head>
    <body>
        <div class="erro-container">
            <h1>⚠️ Erro na Operação</h1>
            
            <% 
                String mensagemErro = (String) request.getAttribute("mensagem_erro");
                if (mensagemErro == null) {
                    mensagemErro = "Ocorreu um erro inesperado. Por favor, tente novamente.";
                }
            %>
            
            <div class="erro-mensagem">
                <strong>Detalhes do erro:</strong><br/>
                <%= mensagemErro %>
            </div>
            
            <p>Se o problema persistir, entre em contato com o administrador do sistema.</p>
            
            <a href="<%= request.getContextPath() %>/home/app/menu.jsp" class="botao-voltar">← Voltar ao Menu</a>
        </div>
    </body>
</html>