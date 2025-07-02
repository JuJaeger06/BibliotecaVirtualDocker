<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="ISO-8859-1">
    <title>Cadastrar Usuário</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Cadastrar Usuário</h3>

                    <form action="usuario" method="post">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" class="form-control" name="nome" placeholder="Digite seu nome" required>
                        </div>

                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" placeholder="Digite seu email" required>
                        </div>

                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" class="form-control" name="senha" placeholder="Digite uma senha" required>
                        </div>

                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" class="form-control" name="cpf" placeholder="Digite seu CPF" required>
                        </div>

                        <div class="mb-3">
                            <label for="dt_nascimento" class="form-label">Data de Nascimento</label>
                            <input type="date" class="form-control" name="dt_nascimento" required>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Cadastrar</button>
                        </div>

                        <div class="mt-3 text-center">
                            <a href="index.jsp" class="btn btn-link">Voltar para o login</a>
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
    .btn-primary{
        background-color: saddlebrown;
        color: white;
        border-color: saddlebrown;
    }
</style>
