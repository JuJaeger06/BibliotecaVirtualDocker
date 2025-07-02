package br.csi.controller;

import br.csi.model.FilmeSerie;
import br.csi.model.Livros;
import br.csi.model.Usuario;
import br.csi.service.FilmeSerieService;
import br.csi.service.LivroService;
import br.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {
    private static final LivroService service = new LivroService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Livros livro = new Livros();
        livro.setNome(req.getParameter("nome"));
        livro.setAutor(req.getParameter("autor"));
        livro.setDtInicio(LocalDate.parse(req.getParameter("dt_inicio")));
        livro.setDtFinal(LocalDate.parse(req.getParameter("dt_final")));
        livro.setNumPaginas(Integer.parseInt(req.getParameter("num_paginas")));
        livro.setGenero(req.getParameter("genero"));
        livro.setIdUser(Integer.parseInt(req.getParameter("id_user")));

        String opcao = req.getParameter("opcao");
        String retorno = "";

        if(opcao != null) {
            if (opcao.equals("edit")) {
                livro.setIdLivro(Integer.parseInt(req.getParameter("idLivro")));
                retorno = service.atualizarLivro(livro);

            }
        } else {
            retorno = service.cadastrarLivro(livro);
        }

        req.setAttribute("msg", retorno);

        ArrayList<Livros> livros = service.getLivros(Integer.parseInt(req.getParameter("id_user")));
        req.setAttribute("livros", livros);

        ArrayList<FilmeSerie> filmes = new FilmeSerieService().getFilmesSeries(Integer.parseInt(req.getParameter("id_user")));
        req.setAttribute("filmes", filmes);

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        Integer idUser = (Integer) session.getAttribute("idUser");
        String opcao = request.getParameter("opcao");
        String info = request.getParameter("info");

        String paginaDestino = "/WEB-INF/pages/dashboard.jsp";

        if (opcao != null) {
            switch (opcao) {
                case "editar":
                    if (info != null) {
                        int idLivro = Integer.parseInt(info);
                        Livros livro = service.buscarEditar(idUser, idLivro);
                        request.setAttribute("livro", livro);
                        paginaDestino = "/WEB-INF/pages/edicoes/atualizar_livro.jsp";
                    }
                    break;

                case "excluir":
                    if (info != null) {
                        String resultado = service.excluir(Integer.parseInt(info));
                        request.setAttribute("msg", resultado);
                    }
                    break;

                case "inserir":
                    paginaDestino = "/WEB-INF/pages/cadastros/cadastrar_livro.jsp";
                    break;

                default:
                    break;
            }
        }

        // Só carrega lista se for dashboard
        if (paginaDestino.equals("/WEB-INF/pages/dashboard.jsp")) {
            ArrayList<Livros> livros = service.getLivros(idUser);
            request.setAttribute("livros", livros);

            ArrayList<FilmeSerie> filmes = new FilmeSerieService().getFilmesSeries(idUser);
            request.setAttribute("filmes", filmes);

            // Se desejar carregar usuários:
            // ArrayList<Usuario> usuarios = new UsuarioService().getUsuarios();
            // request.setAttribute("users", usuarios);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
