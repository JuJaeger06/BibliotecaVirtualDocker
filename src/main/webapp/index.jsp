<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <!-- Bootstrap 5 CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6 col-lg-5">
            <div class="card shadow-lg">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Logar na Biblioteca Virtual</h3>

                    <form action="login" method="post">
                        <div class="mb-3">
                            <label for="email" class="form-label"><strong>Email</strong></label>
                            <input type="email" class="form-control" name="email" placeholder="Digite seu email" required>
                        </div>

                        <div class="mb-3">
                            <label for="senha" class="form-label"><strong>Senha</strong></label>
                            <input type="password" class="form-control" name="senha" placeholder="Digite sua senha" required>
                        </div>

                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-primary">Logar</button>
                        </div>

                        <div class="mt-3 text-center">
                            <a href="usuario" class="btn btn-link">Cadastrar Usuário</a>
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
