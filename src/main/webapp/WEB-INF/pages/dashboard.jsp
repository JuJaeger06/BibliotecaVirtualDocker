<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container mt-5">
    <h1 class="text-center mb-4">Dashboard</h1>

    <!-- Navegação com abas -->
    <ul class="nav nav-tabs mb-3" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
            <button class="nav-link active" id="usuarios-tab" data-bs-toggle="tab" data-bs-target="#usuarios" type="button" role="tab">Usuários</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="filmes-tab" data-bs-toggle="tab" data-bs-target="#filmes" type="button" role="tab">Filmes/Séries</button>
        </li>
        <li class="nav-item" role="presentation">
            <button class="nav-link" id="livros-tab" data-bs-toggle="tab" data-bs-target="#livros" type="button" role="tab">Livros</button>
        </li>
        <li class="nav-item ms-auto" role="presentation">
            <a class="nav-link text-info" href="index.jsp">
                <i class="bi bi-box-arrow-right"></i> Logout
            </a>
        </li>
    </ul>

    <div class="tab-content" id="myTabContent">
        <!-- Usuários -->
        <div class="tab-pane fade show active" id="usuarios" role="tabpanel">
            <h4>Informações do Usuário Logado</h4>
            <div class="card shadow-sm p-4 mb-4">
                <div class="card-body">
                    <div class="mb-2">
                        <strong>Nome:</strong> ${userLogado.nome}
                    </div>
                    <div class="mb-2">
                        <strong>Email:</strong> ${userLogado.email}
                    </div>
                    <div class="mb-2">
                        <strong>CPF:</strong> ${userLogado.cpf}
                    </div>
                    <div class="mb-2">
                        <strong>Data de Nascimento:</strong> ${userLogado.dtNascimento}
                    </div>

                    <div class="mt-4">
                        <a href="usuario?opcao=editar&&info=${userLogado.idUser}" class="btn btn-primary me-2">Editar</a>
                        <a href="usuario?opcao=excluir&&info=${userLogado.idUser}" class="btn btn-danger">Excluir Usuário Permanentemente</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Filmes/Séries -->
        <div class="tab-pane fade" id="filmes" role="tabpanel">
            <div class="d-grid gap-2 d-md-flex justify-content-between">
                <h4>Filmes/Séries Cadastrados do Usuário Logado</h4>

                <a href="filmes?opcao=inserir&&info=idUser" class="btn btn-outline-primary">Cadastrar</a>
            </div>

            <br>

            <div class="row">
                <c:forEach var="filme" items="${filmes}">
                    <!--  <td>${filme.idPrograma}</td> -->
                    <div class="card col-3" style="width: 20rem; height: 17rem; margin: 5px">
                        <h5 class="card-header">${filme.movie == true ? 'Filme' : 'Série'}: ${filme.nome}</h5>

                        <div class="card-body">
                            <div class="row" >
                                <div class="col-6">
                                    <h6>Gênero</h6>
                                    <p>${filme.genero}</p>
                                </div>
                                <div class="col-6">
                                    <h6>País de Origem</h6>
                                    <p>${filme.paisOrigem}</p>
                                </div>
                            </div>

                            <c:if test="${filme.numTemporadas != 0}">
                                <h6>Número de Temporadas</h6>
                                <p>${filme.numTemporadas != null ? filme.numTemporadas : '-'}</p>
                            </c:if>
                        </div>

                        <div class="card-footer text-end">
                                <a href="filmes?opcao=editar&&info=${filme.idPrograma}" class="btn btn-sm btn-primary">Editar</a>
                                <a href="filmes?opcao=excluir&&info=${filme.idPrograma}" class="btn btn-sm btn-danger" style="padding-inline: 10px">Excluir</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <!-- Livros -->
        <div class="tab-pane fade" id="livros" role="tabpanel">
            <div class="d-grid gap-2 d-md-flex justify-content-between">
                <h4>Livros Cadastrados do Usuário Logado</h4>

                <a href="livros?opcao=inserir&&info=idUser" class="btn btn-outline-primary">Cadastrar</a>
            </div>

            <br>

            <div class="row">
                <c:forEach var="livro" items="${livros}">
                    <!--  <td>${livro.idLivro}</td> -->
                    <div class="card col-3" style="width: 23rem; height: 22rem; margin: 5px">
                        <h5 class="card-header">${livro.nome}</h5>

                        <div class="card-body">
                            <div class="row" >
                                <div class="col-6">
                                    <h6>Autor</h6>
                                    <p>${livro.autor}</p>
                                </div>
                                <div class="col-6">
                                    <h6>Gênero</h6>
                                    <p>${livro.genero}</p>
                                </div>
                            </div>

                            <h6>Número de Páginas</h6>
                            <p>${livro.numPaginas}</p>

                            <div class="row">
                                <div class="col-6">
                                    <h6>Data Início da Leitura</h6>
                                    <p>${livro.dtInicio}</p>
                                </div>

                                <div class="col-6">
                                    <h6>Data Final da Leitura</h6>
                                    <p>${livro.dtFinal}</p>
                                </div>
                            </div>
                        </div>

                        <div class="card-footer text-end">
                            <a href="livros?opcao=editar&&info=${livro.idLivro}" class="btn btn-sm btn-primary">Editar</a>
                            <a href="livros?opcao=excluir&&info=${livro.idLivro}" class="btn btn-sm btn-danger" style="padding-inline: 10px">Excluir</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Bundle JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>

<style>
    .btn-primary{
        background-color: saddlebrown;
        color: white;
        border-color: saddlebrown;
    }

    .btn-outline-primary{
        color: saddlebrown;
        border-color: saddlebrown;
    }

    .nav-link {
        color: #261307;
        font-weight: bold;
        text-decoration: none;
        transition: all 0.3s ease;
    }

    .nav-link:hover {
        color: #ffffff;
        background-color: #261307;
        text-decoration: underline;
    }
</style>