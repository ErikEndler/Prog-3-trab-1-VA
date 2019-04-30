import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controll.TipoPagamentoControll;
import model.ModelTipoPagamento;
import util.FileToString;

public class ServletTipoInsert extends HttpServlet {
	
	public String loginUrl = "login";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session == null)
			response.sendRedirect(loginUrl);
		else {
			String loggedIn = (String) session.getAttribute("LoggedIn");

			if (!loggedIn.equals("true"))
				response.sendRedirect(loginUrl);
		}

		modificarFormulario(response, request);
	}

	private void modificarFormulario(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fileSeparator = System.getProperty("file.separator");
		String FormularioTipo = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "FormularioTipo.html");
		String NovoFormulario = new String();
		NovoFormulario = inserirAction(FormularioTipo, request);
		out.print(NovoFormulario);
		
	}

	private String inserirAction(String string, HttpServletRequest request) {
		String nova = string.replace("form id=\"1\" method=post",
				"form id=\"1\" method=post action=\"" + request.getRequestURI() + "\"");
		return nova;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inserir(request, response);

	}

	private void inserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TipoPagamentoControll tipoControll = new TipoPagamentoControll();
		ModelTipoPagamento mTipo = new ModelTipoPagamento();
		mTipo.setNome(request.getParameter("nome"));
		int resultado= tipoControll.salvarTipoPagamentoControle(mTipo);
		
		if(resultado==1) {
			//redirecionar para pagina geral de tipos pagamento
			response.sendRedirect("tipospagamento");
		}else {
			response.sendRedirect("");
		}
		
		
	}

}
