import javax.servlet.*;
import javax.servlet.http.*;

import controll.TipoPagamentoControll;
import model.ModelTipoPagamento;
import model.ModelVendas;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

public class ServletVenda extends HttpServlet {
	ModelVendas modelVendas = new ModelVendas();
	List<String> listaNomes = modelVendas.getCamposnome();
	ArrayList<String> listaNomesPage= new ArrayList<>();
	TipoPagamentoControll tipoControll = new TipoPagamentoControll();

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Enumeration paramNames = request.getParameterNames();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.print("<html><body>");
		out.print("<h1> Venda...</h1>");
		
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			out.print(  paramName + " : ");
			listaNomesPage.add(paramName);
			String[] paramValues = request.getParameterValues(paramName);
			//
			if (paramValues.length == 1) {
				String paramValue = paramValues[0];
				if (paramValue.length() == 0)
					out.println("No Value");
				else {
					out.println(paramValue + "<br>");
					for(int i=0;i<listaNomes.size();i++) { 
						if(paramName==listaNomes.get(i)) {
							inserir(paramName, paramValue);
						}
						String x = listaNomes.get(i);
						System.out.println(x);
						}
					}	
			} else {
				out.println("</br>");
				for (int i = 0; i < paramValues.length; i++) {
					out.println( paramValues[i] + "<br>");
				}
				out.println("</br>");
			}
		}
		//inserir botao de confirmação com açao de salvar no banco
		out.print("<>");
		out.print("<>");
		out.print("</body>");
		out.print("</html>");
	}
	//metodo q seta os valores do formulario no modelo
	private void inserir(String x, String valor) {
		switch(x) {
		case "id":
			modelVendas.setId(Integer.parseInt(valor));
			break;
		case "data":
			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			//LocalDate data = LocalDate.parse("23/11/2015", formato);
			Date dataFormatada = (Date) formato.parse(valor);
			modelVendas.setData(dataFormatada);
			//Se quiser pegar a data apenas, use java.time.LocalDate.now();
			//Se quiser data e hora, use java.time.Instant.now();
			break;
		case "client":
			modelVendas.setCliente(valor);
			break;
		case "tipo pagamento":
			ModelTipoPagamento tipo = new ModelTipoPagamento();
			//busca banco tipo pagamento equivalente ao nome do selecionado
			tipo=tipoControll.retornarTipoPagamentoNomeControle(valor);
			//seta id od yipo selecionado no modelo vendas
			modelVendas.setTipo_pagamento(tipo.getId());
			break;
		case "vendedor":
			modelVendas.setVendedor(valor);
			break;
		case "produto":
			modelVendas.setProduto(valor);
			break;
		case "valor":
			modelVendas.setValor(Double.parseDouble(valor));
			break;
		case "quantidade":
			modelVendas.setQuantidade(Integer.parseInt(valor));
			break;
		case "observacao":
			modelVendas.setObservacao(valor);
		}
		
		/*
		 * (formatação de data e hora )
		 * LocalDateTime agora = LocalDateTime.now(); DateTimeFormatter formatador =
		 * DateTimeFormatter .ofLocalizedDateTime(FormatStyle.SHORT) .withLocale(new
		 * Locale("pt", "br")); agora.format(formatador); //08/04/14 10:02
		 */		
		
	}
}