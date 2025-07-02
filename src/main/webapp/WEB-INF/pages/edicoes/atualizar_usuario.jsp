<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="ISO-8859-1">
    <title>Atualizar Usuário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Atualizar Usuário</h3>

                    <form action="usuario?opcao=edit" method="post">
                        <input type="hidden" name="idUser" value="${user.idUser}">

                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" class="form-control" name="nome" value="${user.nome}" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">E-mail</label>
                            <input type="email" class="form-control" name="email" value="${user.email}" required>
                        </div>

                        <div class="mb-3">
                            <label for="dt_nascimento" class="form-label">Data de Nascimento</label>
                            <input type="date" class="form-control" name="dt_nascimento" value="${user.dtNascimentoValue}" required>
                        </div>

                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control" name="cpf" value="${user.cpf}" required>
                        </div>

                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" class="form-control" name="senha" value="${user.senha}" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Salvar Alterações</button>
                        </div>

                        <div class="mt-3 text-center">
                            <a href="usuarios" class="btn btn-link">Cancelar</a>
                        </div>
                    </form>

                    <c:if test="${not empty msg}">
                        <div class="alert alert-info mt-3 text-center">${msg}</div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

<style>
    .btn-primary {
        background-color: saddlebrown;
        color: white;
        border-color: saddlebrown;
    }
</style>
