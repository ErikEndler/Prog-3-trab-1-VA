package controll;

import java.util.ArrayList;

import model.ModelTipoPagamento;
import persistence.DaoTipoPagamento;

public class TipoPagamentoControll {
	
	private DaoTipoPagamento daoTypePayment = new DaoTipoPagamento();
	
	public int salvarTipoPagamentoControle(ModelTipoPagamento pModeltipoPagamento) {
		return this.daoTypePayment.salvarTipoPagamentoDAO(pModeltipoPagamento);
	}
	public boolean excluirTipoPagamentoControle(int pCodigo) {
		return this.daoTypePayment.excluirTipoPagamentoDAO(pCodigo);
	}
	public boolean alterarTipoPagamentoControle(ModelTipoPagamento pModeloTipoPagamento) {
		return this.daoTypePayment.alterarTipoPagamentoDAO(pModeloTipoPagamento);
	}
	public ModelTipoPagamento retornarTipoPagamentoControle(int pCodigo) {
		return this.daoTypePayment.retornaTipoPagamentoDAO(pCodigo);
	}
	public ModelTipoPagamento retornarTipoPagamentoNomeControle(String pNome) {
		return this.daoTypePayment.retornaTipoPagamentoNomeDAO(pNome);
	}
	/*
	 * public ArrayList<ModelTypePayment> retornaListaCategoriaControle(){ return
	 * this.daoTypePayment.retornarListaCategoriaDAO(); } public
	 * ArrayList<ModelTypePayment> retornaListaBuscaClienteControle(String
	 * pBox,String pBusca){ return
	 * this.daoTypePayment.retornarListaBuscaCategoriaDAO(pBox, pBusca); }
	 */

}
