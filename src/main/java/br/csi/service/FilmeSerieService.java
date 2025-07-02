package br.csi.service;

import br.csi.dao.FilmeSerieDAO;
import br.csi.model.FilmeSerie;

import java.util.ArrayList;

public class FilmeSerieService {
    private static FilmeSerieDAO filmeSerieDAO = new FilmeSerieDAO();

    public ArrayList<FilmeSerie> getFilmesSeries(int idUser) {
        return filmeSerieDAO.getFilmesSeries(idUser);
    }

    public String cadastrarFilmeSerie(FilmeSerie filmeSerie) {
        return filmeSerieDAO.inserir(filmeSerie);
    }

    public String excluir(int idPrograma) {
        if (filmeSerieDAO.excluir(idPrograma)) {
            return "Sucesso ao excluir programa";
        } else {
            return "Erro ao excluir programa";
        }
    }

    public String atualizarFilmeSerie(FilmeSerie fs) {
        if (filmeSerieDAO.alterar(fs)) {
            return "Sucesso ao alterar programa";
        } else {
            return "Erro ao alterar programa";
        }
    }

    // Se quiser permitir busca individual:
    public FilmeSerie buscarEditar(int idUser, int idPrograma) {
        return filmeSerieDAO.buscarEditar(idUser, idPrograma);
    }
}
