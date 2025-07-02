package br.csi.dao;
import br.csi.model.Livros;
import br.csi.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioDAO {

    public Boolean alterar(Usuario user) {
        try {
            // Conectar com o banco
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            // Montar SQL de atualização
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE usuario SET nome = ?, email = ?, dt_nascimento = ?, cpf = ?, senha = ? WHERE id_user = ?"
            );

            // Preencher os parâmetros
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setDate(3, Date.valueOf(user.getDtNascimentoValue()));
            stmt.setString(4, user.getCpf());
            stmt.setString(5, user.getSenha());
            stmt.setInt(6, user.getIdUser());

            // Executar SQL
            stmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean excluir(int id) {
        try{
            // Conectar com o banco
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            // Montar SQL de Excluir
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuario WHERE id_user = ?");

            stmt.setInt(1, id);
            stmt.execute();

            if(stmt.getUpdateCount() <= 0){
                return false;
            }

        } catch (SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
             return false;
        }

        return true;
    }

    public Usuario getUserLogado(int id) {
        Usuario user = null;

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id_user = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new Usuario();
                user.setIdUser(rs.getInt("id_user"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setDtNascimento(LocalDate.parse(rs.getString("dt_nascimento")));
                user.setCpf(rs.getString("cpf"));
                user.setSenha(rs.getString("senha"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar usuário: " + e.getMessage());
        }

        return user;
    }

    public String inserir(Usuario user) {
        try {
            // Conectar com o banco
            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            // Montar SQL de Inserir
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO usuario (nome, email, dt_nascimento, cpf, senha) VALUES (?, ?, ?, ?, ?)"
            );

            // Preencher os parâmetros
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setDate(3, Date.valueOf(user.getDtNascimentoValue()));
            stmt.setString(4, user.getCpf());
            stmt.setString(5, user.getSenha());

            // Executar SQL
            stmt.execute();

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return "Erro ao inserir o usuário!";
        }

        return "Inserido com sucesso";
    }

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            System.out.println("Conectado com sucesso!");

            Connection conn = ConectarBancoDados.conectarBancoPostgres();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuario");

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUser(rs.getInt("id_user"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setDtNascimento(rs.getDate("dt_nascimento").toLocalDate());
                u.setCpf(rs.getString("cpf"));
                u.setSenha(rs.getString("senha"));

                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar!");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não carregou!");
            ex.printStackTrace();
        }

        return usuarios;
    }

}
