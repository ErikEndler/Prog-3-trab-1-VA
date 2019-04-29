package controll;

import java.util.ArrayList;

import model.ModelVendedor;
import persistence.DaoVendedor;

public class VendedorControll {
	
	private DaoVendedor daoVendedor = new DaoVendedor();

	public int salvarVendedorControle(ModelVendedor pModelVendedor) {
		return this.daoVendedor.salvarVendedorDAO(pModelVendedor);
	}

	public boolean excluirVendedorControle(int pCodigo) {
		return this.daoVendedor.excluirVendedorDAO(pCodigo);
	}

	public boolean alterarVendedorControle(ModelVendedor pModelVendedor) {
		return this.daoVendedor.alterarVendedorDAO(pModelVendedor);
	}

	public ModelVendedor retornarVendedorControle(int pCodigo) {
		return this.daoVendedor.retornaVendedorDAO(pCodigo);
	}

	public ModelVendedor retornarVendedorNomeControle(String pNome) {
		return this.daoVendedor.retornaVendedorNomeDAO(pNome);
	}

	public ArrayList<ModelVendedor> retornaListaVendedoresControle() {
		return this.daoVendedor.retornarListaVendedoresDAO();
	}

}
