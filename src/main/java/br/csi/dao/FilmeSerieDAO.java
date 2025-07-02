package br.csi.dao;

import br.csi.model.FilmeSerie;

import java.sql.*;
import java.util.ArrayList;

public class FilmeSerieDAO {
    public String inserir(FilmeSerie fs) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            System.out.println("UUUUUUUUUUUUU");

            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO filmes_series (nome, isMovie, num_temporadas, genero, pais_origem, id_user) " +
                            "VALUES (?, ?, ?, ?, ?, ?)"
            );

            stmt.setString(1, fs.getNome());
            stmt.setBoolean(2, fs.isMovie());
            stmt.setInt(3, fs.getNumTemporadas());
            stmt.setString(4, fs.getGenero());
            stmt.setString(5, fs.getPaisOrigem());
            stmt.setInt(6, fs.getIdUser());

            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir filme/série: " + e.getMessage());
            return "Erro ao inserir!";
        }

        return "Inserido com sucesso!";
    }

    public boolean alterar(FilmeSerie fs) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE filmes_series SET nome = ?, isMovie = ?, num_temporadas = ?, genero = ?, pais_origem = ?, id_user = ? WHERE id_programa = ?"
            );

            stmt.setString(1, fs.getNome());
            stmt.setBoolean(2, fs.isMovie());
            stmt.setInt(3, fs.getNumTemporadas());
            stmt.setString(4, fs.getGenero());
            stmt.setString(5, fs.getPaisOrigem());
            stmt.setInt(6, fs.getIdUser());
            stmt.setInt(7, fs.getIdPrograma());

            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao alterar filme/série: " + e.getMessage());
            return false;
        }

        return true;
    }

    public boolean excluir(int idPrograma) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM filmes_series WHERE id_programa = ?");
            stmt.setInt(1, idPrograma);
            stmt.execute();

            return stmt.getUpdateCount() > 0;

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao excluir filme/série: " + e.getMessage());
            return false;
        }
    }

    public FilmeSerie buscarEditar(int idUser, int idPrograma) {
        FilmeSerie filmeSerie = new FilmeSerie();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM filmes_series WHERE id_user = ? AND id_programa = ?");
            stmt.setInt(1, idUser);
            stmt.setInt(2, idPrograma);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                filmeSerie.setNome(rs.getString("nome"));
                filmeSerie.setMovie(rs.getBoolean("isMovie"));
                filmeSerie.setNumTemporadas(rs.getInt("num_temporadas"));
                filmeSerie.setGenero(rs.getString("genero"));
                filmeSerie.setPaisOrigem(rs.getString("pais_origem"));
                filmeSerie.setIdUser(rs.getInt("id_user"));
                filmeSerie.setIdPrograma(rs.getInt("id_programa"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar filmes/séries por usuário: " + e.getMessage());
        }

        return filmeSerie;
    }

    public ArrayList<FilmeSerie> getFilmesSeries(int idUser) {
        ArrayList<FilmeSerie> lista = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            String sql = "SELECT * FROM filmes_series WHERE id_user = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idUser);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                FilmeSerie fs = new FilmeSerie();
                fs.setIdPrograma(rs.getInt("id_programa"));
                fs.setNome(rs.getString("nome"));
                fs.setMovie(rs.getBoolean("isMovie"));
                fs.setNumTemporadas(rs.getInt("num_temporadas"));
                fs.setGenero(rs.getString("genero"));
                fs.setPaisOrigem(rs.getString("pais_origem"));
                fs.setIdUser(rs.getInt("id_user"));

                lista.add(fs);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao listar filmes/séries: " + e.getMessage());
        }

        return lista;
    }
}
