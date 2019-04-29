import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.TipoPagamentoControll;
import model.ModelTipoPagamento;
import model.ModelVendas;
import util.FileToString;

public class ServletTipoUpdate extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		preencherHtml(response, request);

		PrintWriter out = response.getWriter();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		update(request, response);

	}

	private void preencherHtml(HttpServletResponse response, HttpServletRequest request) throws IOException {
		TipoPagamentoControll tipoControll = new TipoPagamentoControll();
		ModelTipoPagamento modelTipo = tipoControll
				.retornarTipoPagamentoControle(Integer.parseInt(request.getParameter("id")));

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String fileSeparator = System.getProperty("file.separator");
		
		String FormularioTipo = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "FormularioTipo.html");
		String novoForm = FormularioTipo;
		novoForm = inserirId(novoForm, modelTipo);
		novoForm = inserirNome(novoForm, modelTipo);
		novoForm=inserirAction(novoForm, request, modelTipo);
		
		out.print(novoForm);

	}

	private String inserirAction(String string, HttpServletRequest request, ModelTipoPagamento modelTipo) {
		String nova=string.replace("form id=\"1\" METHOD=POST",
				"form id=\"1\" method=post action=\"" + request.getRequestURI() + "?id=" + modelTipo.getId()+  "\"");
		return nova;
	}

	private String inserirId(String string, ModelTipoPagamento pTipo) {
		String nova = string.replace("name=\"id\" value=\"\"", "name=\"id\" value=\"" + pTipo.getId() + "\"");
		return nova;
	}
	private String inserirNome(String string, ModelTipoPagamento pTipo) {
		String nova = string.replace("name=\"nome\" value=\"\"", "name=\"nome\" value=\"" + pTipo.getNome() + "\"");
		return nova;
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TipoPagamentoControll tipoControll = new TipoPagamentoControll();
		ModelTipoPagamento mTipo = new ModelTipoPagamento();
		mTipo.setId(Integer.parseInt(request.getParameter("id")));
		mTipo.setNome(request.getParameter("nome"));
		tipoControll.alterarTipoPagamentoControle(mTipo);
		
		//redirecionar para pagina geral de tipos pagamento
		response.sendRedirect("");
	}

}
