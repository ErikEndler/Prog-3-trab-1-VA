

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.TipoPagamentoControll;
import controll.VendaControll;
import model.ModelTipoPagamento;
import model.ModelVendas;

public class ServletCadastro extends HttpServlet{
	
	ModelVendas modelVenda = new ModelVendas();
	VendaControll vendasControll = new VendaControll();
	TipoPagamentoControll tipoControll = new TipoPagamentoControll();
	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		modelVenda.setCliente(request.getParameter("nome"));
		modelVenda.setData(request.getParameter("data"));
		modelVenda.setProduto(request.getParameter("produto"));
		modelVenda.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		
		String valor = request.getParameter("valor");
		valor = valor.replace(",", ".");
		double dValor = Double.parseDouble(valor);
		modelVenda.setValor(dValor);
		
		modelVenda.setVendedor(request.getParameter("vendedor"));
		modelVenda.setObservacao(request.getParameter("observacao"));
		
		ModelTipoPagamento tipo = new ModelTipoPagamento();
		//busca banco tipo pagamento equivalente ao nome do selecionado
		tipo=tipoControll.retornarTipoPagamentoNomeControle(request.getParameter("tipo pagamento"));
		//seta id od tipo selecionado no modelo vendas
		modelVenda.setTipo_pagamento(tipo.getId());
		
		int i=vendasControll.salvarVendasControle(modelVenda);
		if(i==1)
			System.out.println("Salvo com sucesso");
		if(i==0)
			System.out.println("Erro ao salvar");
		
	}

}
