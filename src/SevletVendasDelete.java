import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.TipoPagamentoControll;
import controll.VendaControll;
import controll.VendedorControll;
import model.ModelTipoPagamento;
import model.ModelVendas;
import model.ModelVendedor;
import util.FileToString;

public class SevletVendasDelete extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preencherHTML(response,request);
		
	}
	
	private void preencherHTML(HttpServletResponse response, HttpServletRequest request) throws IOException {
		TipoPagamentoControll tipoControll = new TipoPagamentoControll();
		VendaControll vendaControll = new VendaControll();
		ModelVendas venda = vendaControll.retornarVendaControle(Integer.parseInt(request.getParameter("id")));
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String fileSeparator = System.getProperty("file.separator");

		String FormularioTipo = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "FormularioVendasFull.html");

		ArrayList<ModelTipoPagamento> tipos = tipoControll.retornaListaTiposControle();
		
		String modificador = new String();
		if (tipos != null)
			modificador = construirCombobox(tipos,request,venda);

		String NovoFormulario = FormularioTipo.replaceAll("<option>##</option>", modificador);
		
		VendedorControll vendedorCon = new VendedorControll();
		ArrayList<ModelVendedor> vendedores = vendedorCon.retornaListaVendedoresControle();
		String modificador2 = new String();
		if (vendedores != null)
			modificador2 = construirComboboxVendedores(vendedores, venda);

		NovoFormulario = NovoFormulario.replaceAll("<option>@@</option>", modificador2);
		
		NovoFormulario= inserirData(NovoFormulario,venda);
		NovoFormulario=inserirCliente(NovoFormulario, venda);
		NovoFormulario=inserirProduto(NovoFormulario, venda);
		NovoFormulario=inserirValor(NovoFormulario, venda);
		NovoFormulario=inserirQuantidade(NovoFormulario, venda);
		NovoFormulario=inserirObservacao(NovoFormulario, venda);
		NovoFormulario=inserirAction(NovoFormulario, request,venda);
		NovoFormulario=mudarBotao(NovoFormulario);
		
		out.print(NovoFormulario);
		
	}

	private String mudarBotao(String string) {
		String nova = string.replace("<button type=\"reset\" class=\"btn btn-warning\">Limpar</button>", "<a class=\"btn btn-danger\" href=\"vendas\" role=\"button\">Cancelar</a>");
		String nova2 = nova.replace("type=\"submit\">Salvar</button>", "type=\"submit\">Deletar</button>");
		return nova2;
	}

	private String inserirAction(String string, HttpServletRequest request, ModelVendas venda) {
		String nova=string.replace("form id=\"1\" METHOD=POST",
				"form id=\"1\" METHOD=POST action=\"" + request.getRequestURI() + "?id=" + venda.getId()+  "\"");
		return nova;
	}

	private String inserirObservacao(String string, ModelVendas pVenda) {
		String nova = string.replace("></textarea>", " disabled>"+pVenda.getObservacao()+"</textarea>");
		return nova;
	}

	private String inserirQuantidade(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"quantidade\" value=\"\"", "disabled name=\"quantidade\" value=\""+pVenda.getQuantidade()+"\"");
		return nova;
	}

	private String inserirValor(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"valor\" value=\"\"", "disabled name=\"valor\" value=\""+pVenda.getValor()+"\"");
		return nova;
	}

	private String inserirProduto(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"produto\" value=\"\"", "disabled name=\"produto\" value=\""+pVenda.getProduto()+"\"");
		return nova;
	}

	private String inserirCliente(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"cliente\" value=\"\"", "disabled name=\"cliente\" value=\""+pVenda.getCliente()+"\"");
		return nova;
	}

	private String inserirData(String string, ModelVendas venda) {
		String nova = string.replace("name=\"data\" value=\"\"", "disabled name=\"data\" value=\""+venda.getData()+"\"");
		return nova;
	}

	private String construirComboboxVendedores(ArrayList<ModelVendedor> vendedores, ModelVendas venda) {
		String options = new String();

		for (ModelVendedor vendedor : vendedores) {
			if(venda.getId()==vendedor.getId()) {
				String aux2 = new String();
				String aux3 = new String();
				String aux = "<option value=\"#\">@</option> \n";

				aux2 = aux.replaceAll("#", String.valueOf(vendedor.getId()));

				aux3 = aux2.replaceAll("@", vendedor.getNome());
				options += aux3;
			}
			
		}
		return options;
	}

	private String construirCombobox(ArrayList<ModelTipoPagamento> listTipo, HttpServletRequest request,
			ModelVendas venda) {
String options = new String();
		
		for (ModelTipoPagamento tipo : listTipo) {
			if(venda.getTipo_pagamento()==tipo.getId()) {
				String aux2 = new String();
				String aux3 = new String();
				String aux = "<option value=\"#\" selected>@</option> \n";

				aux2 = aux.replaceAll("#", String.valueOf(tipo.getId()));

				aux3 = aux2.replaceAll("@", tipo.getNome());
				options += aux3;
			}			
		}
		return options;
	}
	
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		delete(request, response);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VendaControll vendaControl = new VendaControll();
		vendaControl.excluirVendasControle(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("vendas");
		
	}

}
