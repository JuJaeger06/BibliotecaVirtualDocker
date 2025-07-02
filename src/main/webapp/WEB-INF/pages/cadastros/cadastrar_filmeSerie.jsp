<%@page contentType="text/html; charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="ISO-8859-1">
    <title>Cadastrar Filme ou Série</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-8 col-lg-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Cadastrar Filme ou Série</h3>

                    <form action="filmes" method="post">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" class="form-control" name="nome" required>
                        </div>

                        <div class="mb-3">
                            <label for="isMovie" class="form-label">Tipo</label>
                            <select class="form-select" name="isMovie" required>
                                <option value="true">Filme</option>
                                <option value="false">Série</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="num_temporadas" class="form-label">Número de Temporadas</label>
                            <input type="number" class="form-control" name="num_temporadas" min="0" required>
                        </div>

                        <div class="mb-3">
                            <label for="genero" class="form-label">Gênero</label>
                            <input type="text" class="form-control" name="genero" required>
                        </div>

                        <div class="mb-3">
                            <label for="pais_origem" class="form-label">País de Origem</label>
                            <input type="text" class="form-control" name="pais_origem" required>
                        </div>

                        <div class="mb-3">
                            <input type="hidden" name="id_user" value="${sessionScope.idUser}">
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Cadastrar</button>
                        </div>

                        <div class="mt-3 text-center">
                            <a href="filmes" class="btn btn-link">Voltar</a>
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
