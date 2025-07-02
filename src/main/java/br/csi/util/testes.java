package br.csi.util;

//import br.csi.dao.ClienteDAO;
//import br.csi.dao.UsuarioDAO;
//import br.csi.model.Usuario;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

public class testes {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();

        for (Usuario u : dao.getUsuarios()){
            imprimir(u);
        }
    }

    public static void imprimir(Usuario user) {
        System.out.println(
                "ID: " + user.getIdUser()
                        + "\nEmail: " + user.getEmail()
                        + "\nSenha: " + user.getSenha()
                        + "\n"
        );
    }
}
