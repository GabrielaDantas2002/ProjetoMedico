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

        <form name="editarmedico" action="EditarMedico" method="POST">
            <table align="center">
                <tr>
                    <th>Editar cadastro de Médicos</th>
                </tr
                
                <tr>
                    <td>ID Pessoa:</td>
                    <td><input type="text" name="idPessoa" value="${medico.idPessoa}" readonly="true"></td>
                </tr>
                      <tr>
                    <td>ID Médico:</td>
                    <td><input type="text" name="idMedico" value="${medico.idMedico}" readonly="true"></td>
                </tr>
                <tr>
                    <td>Nome:</td>
                    <td><input type="text" name="nome" value="${medico.nome}"></td>
                </tr>
                <tr>
                    <td>Endereço:</td>
                    <td><input type="text" name="endereco" value="${medico.endereco}"></td>
                </tr>
                <tr>
                    <td>CRM:</td>
                    <td><input type="text" name="CRM" value="${medico.CRM}"></td>
                </tr>
                <tr>
                    <td>Especialidade:</td>
                    <td>
                        <select name="especialidade">
                          <option selected value="${medico.especialidade.idEspecialidade}">${medico.especialidade.nomeEspecialidade}</option>

                           <c:forEach var = "especialidade" items = "${especialidades}">
                                <option value="${especialidade.idEspecialidade}">${especialidade.nomeEspecialidade}</option>
                           </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" name="salvar" value="Salvar"></td>
                </tr>


            </table>
            <h2 align="center"><a href="index.jsp">Voltar</a></h2>
        </form>
    </body>
</html>
