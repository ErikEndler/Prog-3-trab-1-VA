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

		if (modelLogin.getLogin().equals(login) && modelLogin.getSenha().equals(senha)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("LoggedIn", new String("true"));
			response.sendRedirect("Home.html");
			
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedIn", new String("false"));
			sendLoginForm(response, true);
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

		String HTML = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "login.html");

		out.println(HTML);

		out.flush();
	}
}
