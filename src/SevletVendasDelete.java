import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controll.VendaControll;

public class SevletVendasDelete extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VendaControll vendaControl = new VendaControll();
		vendaControl.excluirVendasControle(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect("vendas");
	}

}
