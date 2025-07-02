<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="ISO-8859-1">
    <title>Cadastrar Livro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Cadastrar Livro</h3>

                    <form action="livros" method="post">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome do Livro</label>
                            <input type="text" class="form-control" name="nome" required>
                        </div>

                        <div class="mb-3">
                            <label for="autor" class="form-label">Autor</label>
                            <input type="text" class="form-control" name="autor" required>
                        </div>

                        <div class="mb-3">
                            <label for="dt_inicio" class="form-label">Data de Início da Leitura</label>
                            <input type="date" class="form-control" name="dt_inicio" required>
                        </div>

                        <div class="mb-3">
                            <label for="dt_final" class="form-label">Data Final da Leitura</label>
                            <input type="date" class="form-control" name="dt_final" required>
                        </div>

                        <div class="mb-3">
                            <label for="num_paginas" class="form-label">Número de Páginas</label>
                            <input type="number" class="form-control" name="num_paginas" required>
                        </div>

                        <div class="mb-3">
                            <label for="genero" class="form-label">Gênero</label>
                            <input type="text" class="form-control" name="genero" required>
                        </div>

                        <div class="mb-3">
                            <input type="hidden" name="id_user" value="${sessionScope.idUser}">
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Cadastrar</button>
                        </div>

                        <div class="mt-3 text-center">
                            <a href="livros" class="btn btn-link">Voltar</a>
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
