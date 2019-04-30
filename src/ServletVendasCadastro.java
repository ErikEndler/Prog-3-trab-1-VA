import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controll.TipoPagamentoControll;
import controll.VendaControll;
import controll.VendedorControll;
import model.ModelTipoPagamento;
import model.ModelVendas;
import model.ModelVendedor;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;
import util.FileToString;

public class ServletVendasCadastro extends HttpServlet {

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
		procurarTipo(response, request);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inserir(request, response);

	}

	void procurarTipo(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fileSeparator = System.getProperty("file.separator");

		String FormularioVendas1 = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "FormularioVendas.html");

		VendedorControll vendedorCon = new VendedorControll();
		ArrayList<ModelVendedor> vendedores = vendedorCon.retornaListaVendedoresControle();

		String modificador2 = new String();
		if (vendedores != null)
			modificador2 = construirComboboxVendedores(vendedores);

		String NovoFormulario = FormularioVendas1.replaceAll("<option>@@</option>", modificador2);
		NovoFormulario = inserirAction(NovoFormulario, request);
		out.print(NovoFormulario);

	}

	private String construirComboboxVendedores(ArrayList<ModelVendedor> vendedores) {
		String options = new String();

		for (ModelVendedor vendedor : vendedores) {
			String aux2 = new String();
			String aux3 = new String();
			String aux = "<option value=\"#\">@</option> \n";

			aux2 = aux.replaceAll("#", String.valueOf(vendedor.getId()));

			aux3 = aux2.replaceAll("@", vendedor.getNome());
			options += aux3;
		}
		return options;
	}

	

	private String inserirAction(String string, HttpServletRequest request) {
		String nova = string.replace("form id=\"1\" METHOD=POST",
				"form id=\"1\" METHOD=POST action=\"" + request.getRequestURI() + "\"");
		return nova;

	}

	private void inserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession(true);
		session.setAttribute("cliente", new String(request.getParameter("cliente")));
		session.setAttribute("data", new String(request.getParameter("data")));
		session.setAttribute("vendedor", new String(request.getParameter("vendedor")));
		response.sendRedirect("insertVenda2");
		
		
		/*
		 * VendaControll vendaControll = new VendaControll(); ModelVendas venda = new
		 * ModelVendas(); venda.setCliente(request.getParameter("cliente"));
		 * venda.setData(request.getParameter("data"));
		 * venda.setObservacao(request.getParameter("observacao"));
		 * venda.setProduto(request.getParameter("produto"));
		 * venda.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		 * venda.setTipo_pagamento(Integer.parseInt(request.getParameter("tipopagamento"
		 * ))); String valor = request.getParameter("valor"); valor =
		 * valor.replaceAll(",", "."); venda.setValor(Double.parseDouble(valor));
		 * venda.setVendedor(Integer.parseInt(request.getParameter("vendedor"))); int i
		 * = vendaControll.salvarVendasControle(venda); // se i for 0 deu errado if (i
		 * == 0) response.sendRedirect(""); // int i = 1 deu certo else if (i == 1)
		 * response.sendRedirect("vendas");
		 */

	}

}
