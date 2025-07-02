package br.csi.service;

import br.csi.dao.UsuarioDAO;
import br.csi.model.Usuario;

public class LoginService {

    public int autenticar(String email, String senha) {
        UsuarioDAO Userdao = new UsuarioDAO();

        int isAutentic = 0;

        for (Usuario user: Userdao.getUsuarios()){
            if(user.getEmail().equals(email) && user.getSenha().equals(senha)){
                isAutentic = user.getIdUser();
                break;
            }
        }

        return isAutentic;
    }

}
