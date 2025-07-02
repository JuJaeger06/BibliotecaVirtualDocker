package br.csi.dao;

import br.csi.model.Livros;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class LivroDAO {

    public String inserir(Livros livro) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO livros (nome, autor, dt_inicio, dt_final, num_paginas, genero, id_user) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.setDate(3, Date.valueOf(livro.getDtInicioValue()));
            stmt.setDate(4, Date.valueOf(livro.getDtFinalValue()));
            stmt.setInt(5, livro.getNumPaginas());
            stmt.setString(6, livro.getGenero());
            stmt.setInt(7, livro.getIdUser());

            stmt.execute();
            return "Livro inserido com sucesso!";

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir livro: " + e.getMessage());
            return "Erro ao inserir livro!";
        }
    }

    public boolean alterar(Livros livro) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE livros SET nome = ?, autor = ?, dt_inicio = ?, dt_final = ?, num_paginas = ?, genero = ?, id_user = ? WHERE id_livro = ?"
            );

            stmt.setString(1, livro.getNome());
            stmt.setString(2, livro.getAutor());
            stmt.setDate(3, Date.valueOf(livro.getDtInicioValue()));
            stmt.setDate(4, Date.valueOf(livro.getDtFinalValue()));
            stmt.setInt(5, livro.getNumPaginas());
            stmt.setString(6, livro.getGenero());
            stmt.setInt(7, livro.getIdUser());
            stmt.setInt(8, livro.getIdLivro());

            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao alterar livro: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean excluir(int idLivro) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM livros WHERE id_livro = ?");
            stmt.setInt(1, idLivro);
            stmt.execute();

            return stmt.getUpdateCount() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir livro: " + e.getMessage());
            return false;
        }
    }

    public Livros buscarEditar(int idUser, int idLivro) {
        Livros livro = new Livros();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM livros WHERE id_user = ? AND id_livro = ?");
            stmt.setInt(1, idUser);
            stmt.setInt(2, idLivro);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                livro.setDtInicio(LocalDate.parse(rs.getString("dt_inicio")));
                livro.setDtFinal(LocalDate.parse(rs.getString("dt_final")));
                livro.setNumPaginas(rs.getInt("num_paginas"));
                livro.setGenero(rs.getString("genero"));
                livro.setIdUser(rs.getInt("id_user"));
                livro.setIdLivro(rs.getInt("id_livro"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar livro: " + e.getMessage());
        }

        return livro;
    }

    public ArrayList<Livros> getLivros(int idUser) {
        ArrayList<Livros> lista = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            String sql = "SELECT * FROM livros WHERE id_user = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUser);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Livros livro = new Livros();
                livro.setIdLivro(rs.getInt("id_livro"));
                livro.setNome(rs.getString("nome"));
                livro.setAutor(rs.getString("autor"));
                livro.setDtInicio(rs.getDate("dt_inicio").toLocalDate());
                livro.setDtFinal(rs.getDate("dt_final").toLocalDate());
                livro.setNumPaginas(rs.getInt("num_paginas"));
                livro.setGenero(rs.getString("genero"));
                livro.setIdUser(rs.getInt("id_user"));

                lista.add(livro);
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }

        return lista;
    }
}
