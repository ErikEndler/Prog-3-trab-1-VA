import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controll.LoginControll;
import model.ModelLogin;
import util.FileToString;

public class ServletLogin extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginControll loginControll = new LoginControll();
		ModelLogin modelLogin = new ModelLogin();
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		login = (login != null ? login : "").trim();
		senha = (senha != null ? senha : "").trim();

		modelLogin = loginControll.retornarLoginControle(login);
		System.out.println(modelLogin.getLogin());
		System.out.println(modelLogin.getSenha());
		System.out.println(login);
		
		

		if (modelLogin.getLogin()==null || modelLogin.getSenha()==null) {
			System.out.println("redirecionamento");
			HttpSession session = request.getSession(true);
			session.setAttribute("LoggedIn", new String("false"));
			sendLoginForm(response, true);
		} 
		else {
			if (modelLogin.getLogin().equals(login) && modelLogin.getSenha().equals(senha)) {
				System.out.println("logado");
				HttpSession session = request.getSession(true);
				session.setAttribute("LoggedIn", new String("true"));
				session.setAttribute("usuario",login);
				response.sendRedirect("home");

			} else {
				System.out.println("erro ao logar");
				HttpSession session = request.getSession(true);
				session.setAttribute("LoggedIn", new String("false"));
				sendLoginForm(response, true);
			}
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		sendLoginForm(response, false);
	}

	private void sendLoginForm(HttpServletResponse response, boolean withErrorMessage)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fileSeparator = System.getProperty("file.separator");

		String formLogin = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "login.html");
		if (withErrorMessage) {
			String aux = "Login failed. Please try again.";
			String newLogin = formLogin.replace("<p></p>", "<p>" + aux + "</p>");
			out.println(newLogin);

			out.flush();
		} else {
			out.println(formLogin);

			out.flush();
		}

	}
}
