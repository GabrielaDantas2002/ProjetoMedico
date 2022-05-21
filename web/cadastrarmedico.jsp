<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Controle de Médicos</title>
    </head>
    <body>
        <h1 align="center">Controle de Médicos</h1>
        <hr>
        <h2 align="center"><${mensagem}/h2>

        <form name="cadastrarmedico" action="CadastrarMedico" method="POST">
            <table align="center">
                <tr>
                    <th>Cadastrar Médicos</th>
                </tr>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nome"></td>
                </tr>
                <tr>
                    <td>Endereço:</td>
                    <td><input type="text" name="endereco"></td>
                </tr>
                <tr>
                    <td>CRM:</td>
                    <td><input type="text" name="crm"></td>
                </tr>
                <tr>
                    <td>Especialidade:</td>
                    <td>
                        <select name="especialidade">
                            <c:forEach var = "especialidade" items = "${especialidades}">
                                <option value="${especialidade.idEspecialidade}">${especialidade.nomeEspecialidade}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="cadastrar" value="Cadastrar"></td>
                </tr>


            </table>
            <h2 align="center"><a href="index.jsp">Voltar</a></h2>
        </form>
    </body>
</html>
