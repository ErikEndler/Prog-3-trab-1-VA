import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.TipoPagamentoControll;

public class ServletTipoDelete extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("entrou doget delete");
		TipoPagamentoControll tipocontrol = new TipoPagamentoControll();
		tipocontrol.excluirTipoPagamentoControle(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("tipospagamento");
	}

}
