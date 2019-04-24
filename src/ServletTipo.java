import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ModelTipoPagamento;

public class ServletTipo extends HttpServlet {
	ModelTipoPagamento modelTipo = new ModelTipoPagamento();
	List<String> listaNomes = modelTipo.getCamposNome();
	ArrayList<String> listaNomesPage= new ArrayList<>();
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Enumeration paramNames = request.getParameterNames();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		out.print("<html><body>");
		out.print("<h1> Tipo Pagamento...</h1>");
		
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
						System.out.println("for iniciado");
						System.out.println(paramName);
						System.out.println(listaNomes.get(i));
						if(paramName.equals(listaNomes.get(i))) {
							System.out.println("entrou no if");
							inserir(paramName, paramValue);
						}
						String x = listaNomes.get(i);
						//System.out.println(x);
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
	private void inserir(String x, String valor){
		switch(x) {
		case "id":
			modelTipo.setId(Integer.parseInt(valor));
			System.out.println("salvo id");
			break;
		case "nome":
			modelTipo.setNome(valor);
			break;
		}
	}

}
