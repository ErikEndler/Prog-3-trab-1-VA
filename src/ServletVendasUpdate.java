import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import controll.TipoPagamentoControll;
import controll.VendaControll;
import controll.VendedorControll;
import model.ModelTipoPagamento;
import model.ModelVendas;
import model.ModelVendedor;
import util.FileToString;

public class ServletVendasUpdate extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		preencherHTML(response,request);

	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		update(request, response);
	}


	void preencherHTML(HttpServletResponse response, HttpServletRequest request) throws IOException {
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
		
		out.print(NovoFormulario);

	}

	private String construirCombobox(ArrayList<ModelTipoPagamento> listTipo, HttpServletRequest request, ModelVendas venda) {
		String options = new String();
		
		for (ModelTipoPagamento tipo : listTipo) {
			if(venda.getTipo_pagamento()==tipo.getId()) {
				String aux2 = new String();
				String aux3 = new String();
				String aux = "<option value=\"#\" selected>@</option> \n";

				aux2 = aux.replaceAll("#", String.valueOf(tipo.getId()));

				aux3 = aux2.replaceAll("@", tipo.getNome());
				options += aux3;
			}else {
				String aux2 = new String();
				String aux3 = new String();
				String aux = "<option value=\"#\">@</option> \n";

				aux2 = aux.replaceAll("#", String.valueOf(tipo.getId()));

				aux3 = aux2.replaceAll("@", tipo.getNome());
				options += aux3;
			}
			
		}
		return options;
	}
	
	//fazer modificação para setar selecionado igual combobox acima
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
			}else {
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
	private String inserirData(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"data\" value=\"\"", "name=\"data\" value=\""+pVenda.getData()+"\"");
		return nova;		
	}
	private String inserirCliente(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"cliente\" value=\"\"", "name=\"cliente\" value=\""+pVenda.getCliente()+"\"");
		return nova;
	}
	private String inserirProduto(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"produto\" value=\"\"", "name=\"produto\" value=\""+pVenda.getProduto()+"\"");
		return nova;
	}
	private String inserirValor(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"valor\" value=\"\"", "name=\"valor\" value=\""+pVenda.getValor()+"\"");
		return nova;
	}
	private String inserirQuantidade(String string, ModelVendas pVenda) {
		String nova = string.replace("name=\"quantidade\" value=\"\"", "name=\"quantidade\" value=\""+pVenda.getQuantidade()+"\"");
		return nova;
	}
	private String inserirObservacao(String string, ModelVendas pVenda) {
		String nova = string.replace("></textarea>", ">"+pVenda.getObservacao()+"</textarea>");
		return nova;
	}
	private String inserirAction(String string, HttpServletRequest request, ModelVendas venda) {
		String nova=string.replace("form id=\"1\" METHOD=POST",
				"form id=\"1\" METHOD=POST action=\"" + request.getRequestURI() + "?id=" + venda.getId()+  "\"");
		return nova;		
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VendaControll vendaControll = new VendaControll();
		ModelVendas venda = new ModelVendas();
		venda.setId(Integer.parseInt(request.getParameter("id")));
		venda.setCliente(request.getParameter("cliente"));
		venda.setData(request.getParameter("data"));
		venda.setObservacao(request.getParameter("observacao"));
		venda.setProduto(request.getParameter("produto"));
		String quantidade = request.getParameter("quantidade");
		venda.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		venda.setTipo_pagamento(Integer.parseInt(request.getParameter("tipopagamento")));
		String valor = request.getParameter("valor");
		valor = valor.replaceAll(",", ".");
		venda.setValor(Double.parseDouble(valor));
		venda.setVendedor(Integer.parseInt(request.getParameter("vendedor")));
		
		vendaControll.alterarVendasControle(venda);
		
		//redirecionar para pagina geral de vendas
		response.sendRedirect("vendas");
	}

}
