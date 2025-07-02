package br.csi.controller;

import br.csi.model.Usuario;
import br.csi.service.FilmeSerieService;
import br.csi.service.LivroService;
import br.csi.service.LoginService;
import br.csi.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        System.out.println("\n\nEmail: " + email + " Senha: " + senha);

        req.setAttribute("users", new UsuarioService().getUsuarios());

        RequestDispatcher dispatcher;

        int retorno = new LoginService().autenticar(email, senha);
        req.setAttribute("idUser", retorno);
        req.getSession().setAttribute("idUser", retorno);

        if(retorno != 0) {
            System.out.println("FIZ LOGIN \n\n");
            req.setAttribute("filmes", new FilmeSerieService().getFilmesSeries(retorno));
            req.setAttribute("livros", new LivroService().getLivros(retorno));
            req.getSession().setAttribute("userLogado", new UsuarioService().getUserLogado(retorno));

            dispatcher = req.getRequestDispatcher("WEB-INF/pages/dashboard.jsp");
            dispatcher.forward(req, resp);
        } else {
            dispatcher = req.getRequestDispatcher("index.jsp");

            req.setAttribute("msg", "Login ou Senha incorreto!");

            dispatcher.forward(req, resp);
        }
    }
}
