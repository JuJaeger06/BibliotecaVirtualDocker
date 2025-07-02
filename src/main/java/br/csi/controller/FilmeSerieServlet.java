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
import java.util.ArrayList;

@WebServlet("/filmes")
public class FilmeSerieServlet extends HttpServlet {
    private static FilmeSerieService service = new FilmeSerieService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        FilmeSerie filmeSerie = new FilmeSerie();

        filmeSerie.setNome(req.getParameter("nome"));
        filmeSerie.setMovie(Boolean.parseBoolean(req.getParameter("isMovie")));
        filmeSerie.setNumTemporadas(Integer.parseInt(req.getParameter("num_temporadas")));
        filmeSerie.setGenero(req.getParameter("genero"));
        filmeSerie.setPaisOrigem(req.getParameter("pais_origem"));
        filmeSerie.setIdUser(Integer.parseInt(req.getParameter("id_user"))); // id do usuário que cadastrou

        String opcao = req.getParameter("opcao");
        String retorno = "";

        if(opcao != null) {
            if(opcao.equals("edit")) {
                filmeSerie.setIdPrograma(Integer.parseInt(req.getParameter("id_programa")));
                retorno = service.atualizarFilmeSerie(filmeSerie);
            }
        } else {
            retorno = service.cadastrarFilmeSerie(filmeSerie);
        }


        req.setAttribute("msg", retorno);

        ArrayList<FilmeSerie> filmes = service.getFilmesSeries(Integer.parseInt(req.getParameter("id_user")));
        req.setAttribute("filmes", filmes);

        ArrayList<Livros> livros = new LivroService().getLivros(Integer.parseInt(req.getParameter("id_user")));
        req.setAttribute("livros", livros);

        req.getRequestDispatcher("/WEB-INF/pages/dashboard.jsp")
                .forward(req, resp);
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
                        int idPrograma = Integer.parseInt(info);
                        FilmeSerie filmeSerie = new FilmeSerieService().buscarEditar(idUser, idPrograma);
                        request.setAttribute("filmeSerie", filmeSerie);
                        paginaDestino = "/WEB-INF/pages/edicoes/atualizar_filmeSerie.jsp";
                    }
                    break;

                case "excluir":
                    if (info != null) {
                        String resultado = service.excluir(Integer.parseInt(info));
                        request.setAttribute("msg", resultado);
                    }
                    break;

                case "inserir":
                    paginaDestino = "/WEB-INF/pages/cadastros/cadastrar_filmeSerie.jsp";
                    break;

                default:
                    break;
            }
        }

        // Só carrega lista se for dashboard
        if (paginaDestino.equals("/WEB-INF/pages/dashboard.jsp")) {
            ArrayList<FilmeSerie> filmes = service.getFilmesSeries(idUser);
            request.setAttribute("filmes", filmes);

            ArrayList<Livros> livros = new LivroService().getLivros(idUser);
            request.setAttribute("livros", livros);

            // Se quiser carregar usuários:
            // ArrayList<Usuario> usuarios = new UsuarioService().getUsuarios();
            // request.setAttribute("users", usuarios);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }
}
