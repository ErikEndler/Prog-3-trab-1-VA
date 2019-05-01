import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.TipoPagamentoControll;
import model.ModelTipoPagamento;
import util.FileToString;

public class ServletTipoDelete extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("entrou doget delete");
		preencherHtml(response, request);
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
		novoForm=modificarBotao(novoForm);
		novoForm=campoOff(novoForm);
		
		out.print(novoForm);
		
	}

	private String inserirAction(String string, HttpServletRequest request, ModelTipoPagamento modelTipo) {
		String nova=string.replace("form id=\"1\" METHOD=POST",
				"form id=\"1\" method=post action=\"" + request.getRequestURI() + "?id=" + modelTipo.getId()+  "\"");
		return nova;
	}

	private String inserirNome(String string, ModelTipoPagamento modelTipo) {
		String nova = string.replace("name=\"nome\" value=\"\"", "name=\"nome\" value=\"" + modelTipo.getNome() + "\"");
		return nova;
	}

	private String inserirId(String string, ModelTipoPagamento modelTipo) {
		String nova = string.replace("name=\"id\" value=\"\"", "name=\"id\" value=\"" + modelTipo.getId() + "\"");
		return nova;
	}
	
	private String modificarBotao(String string) {
		String nova = string.replace("<button type=\"reset\" class=\"btn btn-warning\">Limpar</button>", "<a class=\"btn btn-danger\" href=\"tipospagamento\" role=\"button\">Cancelar</a>");
		String nova2 = nova.replace("type=\"submit\">Salvar</button>", "type=\"submit\">Deletar</button>");
		return nova2;
		
	}
	
	private String campoOff(String string) {
		String nova = string.replace("placeholder", "disabled placeholder");
		return nova;
		
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		delete(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		TipoPagamentoControll tipoControll = new TipoPagamentoControll();
		tipoControll.excluirTipoPagamentoControle(Integer.parseInt(request.getParameter("id")) );
		response.sendRedirect("tipospagamento");
		
	}

}
