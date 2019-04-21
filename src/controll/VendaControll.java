package controll;

import java.util.ArrayList;

import model.ModelVendas;
import persistence.DaoVendas;

public class VendaControll {
	private DaoVendas daoVendas = new DaoVendas();

	public int salvarVendasControle(ModelVendas pModelVendas) {
		return this.daoVendas.salvarVendasDAO(pModelVendas);
	}

	public boolean excluirVendasControle(int pCodigo) {
		return this.daoVendas.excluirVendasDAO(pCodigo);
	}

	public boolean alterarVendasControle(ModelVendas pModelVendas) {
		return this.daoVendas.alterarVendasDAO(pModelVendas);
	}

	public ModelVendas retornarVendaControle(int pCodigo) {
		return this.daoVendas.retornaVendasDAO(pCodigo);
	}

	public ArrayList<ModelVendas> retornaListaVendasControle() {
		return this.daoVendas.retornarListaVendasDAO();
	}

	/*
	 * implementação futura
	 * public ArrayList<ModelVendas> retornaListaBuscaClienteControle(String pBox,
	 * String pBusca) { return this.daoVendas.retornarListaBuscaCategoriaDAO(pBox,
	 * pBusca); }
	 */

}
