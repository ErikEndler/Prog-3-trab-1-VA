import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controll.LoginControll;
import model.ModelLogin;

public class ServletLogin extends HttpServlet {

	LoginControll loginControll = new LoginControll();
	ModelLogin modelLogin = new ModelLogin();
	String resultLogin;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		login = (login != null ? login : "").trim();
		senha = (senha != null ? senha : "").trim();
		
		modelLogin = loginControll.retornarLoginControle(login);
		
		if(modelLogin.getLogin().equals(login) || modelLogin.getSenha().equals(senha)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("LoggedIn", "");
			 resultLogin= "Login realizado com sucesso";
		}
		else {
			resultLogin = "Senha ou Login incorreto";
		}

	}
}
