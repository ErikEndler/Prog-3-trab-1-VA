import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controll.TipoPagamentoControll;
import model.ModelTipoPagamento;
import util.FileToString;

public class ServletTipoListar extends HttpServlet {
	
	public String loginUrl = "login";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		listar(response);
	}

	private void listar(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		TipoPagamentoControll tipoControll = new TipoPagamentoControll();

		ArrayList<ModelTipoPagamento> tipos = tipoControll.retornaListaTiposControle();
		StringBuilder modificador = new StringBuilder();
		for (ModelTipoPagamento tipo : tipos) {

			modificador.append("<TR>\n");
			modificador.append("<TD>" + tipo.getId() + "</TD>\n");
			modificador.append("<TD>" + tipo.getNome() + "</TD>\n");
			modificador.append("<TD ><a HREF=updateTipo?id=" + tipo.getId()
					+ " class=\"badge badge-warning\">"+"Alterar"+"</a></TD>\n");
			modificador.append("<TD ><a HREF=deleteTipo?id=" + tipo.getId()
					+ " class=\"badge badge-danger\">"+"Delete"+"</a></TD>\n");
			modificador.append("</TR>\n");
		}
		String fileSeparator = System.getProperty("file.separator");
		String listaHtml = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "Tipo.html");
		String novaListaHtml = new String();
		novaListaHtml = listaHtml.replaceAll("##", modificador.toString());

		out.println(novaListaHtml);

	}

}
