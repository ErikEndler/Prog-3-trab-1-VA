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
import model.ModelTipoPagamento;
import model.ModelVendas;
import util.FileToString;

public class ServletVendasCadastro2 extends HttpServlet {

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

	private void procurarTipo(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fileSeparator = System.getProperty("file.separator");

		String FormularioVendas2 = FileToString.convert(
				this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "FormularioVendas2.html");

		TipoPagamentoControll tipoControll = new TipoPagamentoControll();
		ArrayList<ModelTipoPagamento> tipos = tipoControll.retornaListaTiposControle();

		String modificador = new String();
		if (tipos != null)
			modificador = construirComboboxTipo(tipos);

		String NovoFormulario = FormularioVendas2.replaceAll("<option>##</option>", modificador);
		NovoFormulario = inserirAction(NovoFormulario, request);

		out.print(NovoFormulario);

	}

	private String construirComboboxTipo(ArrayList<ModelTipoPagamento> listTipo) {
		String options = new String();

		for (ModelTipoPagamento tipo : listTipo) {
			String aux2 = new String();
			String aux3 = new String();
			String aux = "<option value=\"#\">@</option> \n";

			aux2 = aux.replaceAll("#", String.valueOf(tipo.getId()));

			aux3 = aux2.replaceAll("@", tipo.getNome());
			options += aux3;
		}
		return options;
	}

	private String inserirAction(String string, HttpServletRequest request) {
		String nova = string.replace("form id=\"1\" METHOD=POST",
				"form id=\"1\" METHOD=POST action=\"" + request.getRequestURI() + "\"");
		return nova;

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inserir(request, response);

	}

	private void inserir(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		session.setAttribute("tipopagamento", new String(request.getParameter("tipopagamento")));
		session.setAttribute("produto", new String(request.getParameter("produto")));
		session.setAttribute("valor", new String(request.getParameter("valor")));
		session.setAttribute("quantidade", new String(request.getParameter("quantidade")));
		session.setAttribute("observacao", new String(request.getParameter("observacao")));
		inserir2(request, response);

	}

	private void inserir2(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);

		//(String) session.getAttribute("LoggedIn")
		VendaControll vendaControll = new VendaControll();
		ModelVendas venda = new ModelVendas();
		venda.setCliente((String) session.getAttribute("cliente"));
		venda.setData((String) session.getAttribute("data"));
		venda.setObservacao((String) session.getAttribute("observacao"));
		venda.setProduto((String) session.getAttribute("produto"));
		venda.setQuantidade(Integer.parseInt((String) session.getAttribute("quantidade")));
		venda.setTipo_pagamento(Integer.parseInt((String) session.getAttribute("tipopagamento")));
		String valor = (String) session.getAttribute("valor");
		valor = valor.replaceAll(",", ".");
		venda.setValor(Double.parseDouble(valor));
		venda.setVendedor(Integer.parseInt((String) session.getAttribute("vendedor")));
		int i = vendaControll.salvarVendasControle(venda);
		// se i for 0 deu errado
		if (i == 0)
			response.sendRedirect("");
		// int i = 1 deu certo
		else if (i == 1)
			response.sendRedirect("vendas");

	}

}
