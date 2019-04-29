import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.VendaControll;
import model.ModelVendas;
import util.FileToString;

public class ServletVendasListar extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listar(response);
	}

	private void listar(HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		VendaControll vendaControll = new VendaControll();
		
		ArrayList<ModelVendas> listVendas = vendaControll.retornaListaVendasControle();
		StringBuilder modificador = new StringBuilder();

		for (ModelVendas venda : listVendas) {
			modificador.append("<TR>\n");
			modificador.append("<TD>" + venda.getData() + "</TD>\n");
			modificador.append("<TD>" + venda.getCliente() + "</TD>\n");
			modificador.append("<TD>" + venda.getProduto() + "</TD>\n");
			modificador.append("<TD>" + venda.getValor() + "</TD>\n");
			modificador.append("<TD>" + venda.getQuantidade()+ "</TD>\n");
			modificador.append("<TD>" + venda.getTipo_pagamento() + "</TD>\n");
			modificador.append("<TD>" + venda.getVendedor() + "</TD>\n");
			modificador.append("<TD ><a HREF=updateVenda?id=" + venda.getId() + " class=\"badge badge-warning\">"
					+ "Alterar" + "</a></TD>\n");
			modificador.append("<TD ><a HREF=deleteVenda?id=" + venda.getId() + " class=\"badge badge-danger\">"
					+ "Delete" + "</a></TD>\n");
			modificador.append("</TR>\n");
		}
		String fileSeparator = System.getProperty("file.separator");
		String listaHtml = FileToString
				.convert(this.getServletContext().getRealPath(fileSeparator) + fileSeparator + "Vendas.html");
		String novaListaHtml = new String();
		novaListaHtml = listaHtml.replaceAll("##", modificador.toString());
		
		out.println(novaListaHtml);
	}

}
