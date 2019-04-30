import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.FileToString;

public class ServletHome extends HttpServlet {

	public String loginUrl = "login";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null)
			response.sendRedirect(loginUrl);
		else {
			String loggedIn = (String) session.getAttribute("LoggedIn");

			if (loggedIn == null) {
				response.sendRedirect(loginUrl);
			} else if (!loggedIn.equals("true"))
				response.sendRedirect(loginUrl);
		}

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fileSeparator = System.getProperty("file.separator");

		String HomeHtml = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "Home.html");
		
		out.print(HomeHtml);

	}

}
