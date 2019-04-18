import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.Enumeration;

public class ServletVenda extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration paramNames = request.getParameterNames();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.print("<html><body>");
		out.print("<h1> Venda...</h1>");
		
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			out.print(  paramName + " : ");
			String[] paramValues = request.getParameterValues(paramName);
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					out.println("No Value");
				else
					out.println(paramValue + "<br>");
			} else {
				out.println("<br>");
				for (int i = 0; i < paramValues.length; i++) {
					out.println( paramValues[i] + "<br>");
				}
				out.println("<br>");
			}
		}
		
	}
}