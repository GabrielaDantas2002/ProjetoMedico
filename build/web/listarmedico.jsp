<%@page import="br.com.projetomedico.model.Medico"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar M�dicos</title>
    </head>
    <body>
        <h1 align="center">Listar M�dicos</h1>
        <hr>
        <table align="center" border="1">
            <tr>
                <td colspan="11" align="center">Lista de M�dicos</td>
            </tr>
            <tr>
                <th align="center">ID Medico</th>
                <th align="center">Nome</th>
                <th align="center">CRM</th>
                <th align="center">Especialidade</th>
                <th align="center">Endere�o</th>
                <th align="center" colspan="2">Editar</th>
            </tr>
            
            <%
                List<Medico> medicos = (List<Medico>) request.getAttribute("medicos");
                for(Medico medico:medicos){
            %>
            <tr>
                <td align="center"><%=medico.getIdMedico()%></td>
                <td align="center"><%=medico.getNome()%></td>
                <td align="center"><%=medico.getCRM()%></td>
                <td align="center"><%=medico.getEspecialidade().getNomeEspecialidade()%></td>
                <td align="center"><%=medico.getEndereco()%></td>
                <td align="center"><a href="CarregarMedico?idMedico=<%=medico.getIdMedico()%>">Editar</a></td>
            </tr>
            <%
                }
            %>
            <tr>
                    <td align="center" colspan="11"><a href="index.jsp">Voltar</a></td>
            </tr>
        </table>
    </body>
</html>
