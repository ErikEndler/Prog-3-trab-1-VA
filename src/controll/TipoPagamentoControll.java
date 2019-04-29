package controll;

import java.util.ArrayList;

import model.ModelTipoPagamento;
import persistence.DaoTipoPagamento;

public class TipoPagamentoControll {
	
	private DaoTipoPagamento daoTipoPagamento = new DaoTipoPagamento();
	
	public int salvarTipoPagamentoControle(ModelTipoPagamento pModeltipoPagamento) {
		return this.daoTipoPagamento.salvarTipoPagamentoDAO(pModeltipoPagamento);
	}
	public boolean excluirTipoPagamentoControle(int pCodigo) {
		return this.daoTipoPagamento.excluirTipoPagamentoDAO(pCodigo);
	}
	public boolean alterarTipoPagamentoControle(ModelTipoPagamento pModeloTipoPagamento) {
		return this.daoTipoPagamento.alterarTipoPagamentoDAO(pModeloTipoPagamento);
	}
	public ModelTipoPagamento retornarTipoPagamentoControle(int pCodigo) {
		return this.daoTipoPagamento.retornaTipoPagamentoDAO(pCodigo);
	}
	public ModelTipoPagamento retornarTipoPagamentoNomeControle(String pNome) {
		return this.daoTipoPagamento.retornaTipoPagamentoNomeDAO(pNome);
	}
	public ArrayList<ModelTipoPagamento> retornaListaTiposControle() {
		return this.daoTipoPagamento.retornarListaTiposDAO();
	}
	/*
	 * public ArrayList<ModelTypePayment> retornaListaCategoriaControle(){ return
	 * this.daoTypePayment.retornarListaCategoriaDAO(); } public
	 * ArrayList<ModelTypePayment> retornaListaBuscaClienteControle(String
	 * pBox,String pBusca){ return
	 * this.daoTypePayment.retornarListaBuscaCategoriaDAO(pBox, pBusca); }
	 */

}
