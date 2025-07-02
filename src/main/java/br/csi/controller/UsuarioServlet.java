package br.csi.controller;

import br.csi.model.FilmeSerie;
import br.csi.model.Usuario;
import br.csi.service.FilmeSerieService;
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

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    private static UsuarioService service = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Usuario user = new Usuario();
        // Cadastrar Usuário
        user.setNome(req.getParameter("nome"));
        user.setEmail(req.getParameter("email"));
        user.setSenha(req.getParameter("senha"));
        user.setCpf(req.getParameter("cpf"));
        user.setDtNascimento(LocalDate.parse(req.getParameter("dt_nascimento")));

        String opcao = req.getParameter("opcao");
        String paginaDestino = "/WEB-INF/pages/dashboard.jsp";
        String retorno = "";

        if(opcao != null) {
            if(opcao.equals("edit")) {
                user.setIdUser(Integer.parseInt(req.getParameter("idUser")));

                retorno = service.atualizarUsuario(user);

                req.getSession().setAttribute("userLogado", new UsuarioService().getUserLogado(Integer.parseInt(req.getParameter("idUser"))));
            }
        } else {
            retorno = new UsuarioService().cadastrarUsuario(user);
            paginaDestino = "index.jsp";
        }

        req.setAttribute("msg", retorno);

        RequestDispatcher dispatcher = req.getRequestDispatcher(paginaDestino);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Realizar Ações
        String opcao = request.getParameter("opcao");
        String info = request.getParameter("info");
        HttpSession session = request.getSession(false);
        Integer idUser = (Integer) session.getAttribute("idUser");

        if (opcao != null) {
            if (opcao.equals("editar")) {
                Usuario user = new UsuarioService().getUserLogado(idUser);
                request.setAttribute("user", user);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/pages/edicoes/atualizar_usuario.jsp");
                rd.forward(request, response);
            }
            else if (opcao.equals("excluir")) {

                String valor = service.excluir(Integer.parseInt(info));

                request.setAttribute("msg", valor);



                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            }
        } else {
//            request.setAttribute("clients", new ClienteService().listarClientes());

            request.getRequestDispatcher("/WEB-INF/pages/cadastros/cadastrar_usuario.jsp")
                    .forward(request, response);
        }
    }
}

