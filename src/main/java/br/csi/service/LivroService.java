package br.csi.service;

import br.csi.dao.LivroDAO;
import br.csi.model.Livros;

import java.time.LocalDate;
import java.util.ArrayList;

public class LivroService {
    private static LivroDAO livroDAO = new LivroDAO();

    public ArrayList<Livros> getLivros(int idUser) {
        return livroDAO.getLivros(idUser);
    }

    public String cadastrarLivro(Livros livro) {

        return livroDAO.inserir(livro);
    }

    public String excluir(int idLivro) {
        if (livroDAO.excluir(idLivro)) {
            return "Sucesso ao excluir livro";
        } else {
            return "Erro ao excluir livro";
        }
    }

    public String atualizarLivro(Livros livro) {
        if (livroDAO.alterar(livro)) {
            return "Sucesso ao alterar livro";
        } else {
            return "Erro ao alterar livro";
        }
    }

    public Livros buscarEditar(int idUser, int idLivro) {
        return livroDAO.buscarEditar(idUser, idLivro);
    }
}
