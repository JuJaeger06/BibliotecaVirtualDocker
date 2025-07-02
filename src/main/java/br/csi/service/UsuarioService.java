package br.csi.service;

import br.csi.dao.ConectarBancoDados;
import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioService {

    private static UsuarioDAO usuarioDAO = new UsuarioDAO();

    public ArrayList<Usuario> getUsuarios() {
        return usuarioDAO.getUsuarios() ;
    }

    public String cadastrarUsuario(Usuario user) {
        return usuarioDAO.inserir(user);
    }

    public String excluir(int id){

        if(usuarioDAO.excluir(id)){
            return "Sucesso ao excluir usuario";
        }else{
            return "Erro ao excluir usuario";
        }

    }

    public Usuario getUserLogado(int id) {
        Usuario user = usuarioDAO.getUserLogado(id);

        return user;
    }

    public String atualizarUsuario(Usuario user) {
        if(usuarioDAO.alterar(user)){
            return "Sucesso ao alterar usuario";
        }else{
            return "Erro ao alterar usuario";
        }
    }
}
