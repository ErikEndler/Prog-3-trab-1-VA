import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.TipoPagamentoControll;
import model.ModelTipoPagamento;
import util.FileToString;

public class ServletTipoInsert extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		modificarFormulario(response, request);

		PrintWriter out = response.getWriter();

	}

	private void modificarFormulario(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fileSeparator = System.getProperty("file.separator");
		String FormularioTipo = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "FormularioTipo.html");
		String NovoFormulario = new String();
		NovoFormulario = inserirAction(NovoFormulario, request);
		out.print(NovoFormulario);
		
	}

	private String inserirAction(String string, HttpServletRequest request) {
		String nova = string.replace("form id=\"1\" METHOD=POST",
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
		boolean resultado= tipoControll.alterarTipoPagamentoControle(mTipo);
		
		if(resultado==true) {
			//redirecionar para pagina geral de tipos pagamento
			response.sendRedirect("");
		}else {
			response.sendRedirect("");
		}
		
		
	}

}
