package persistence;

import model.ModelTipoPagamento;
import model.ModelVendas;

public class DaoTipoPagamento extends Conexao {
	
	//metodo salvar tipo pagamento
		public int salvarTipoPagamentoDAO(ModelTipoPagamento pModelTipoPagamento) {
			try {
				this.conectar();
				return this.insertSQL("INSERT INTO tb_tipo_pagamento(" 
						+ "nome"
						+ ") VALUES (" 
						+ "'" + pModelTipoPagamento.getNome() + "');");
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			} finally {
				this.fecharConexao();
			}
		}
		//metodo para excluir tipo pagamento
		public boolean excluirTipoPagamentoDAO(int pId) {
			try {
				this.conectar();
				return this.executarUpdateDeleteSQL("DELETE FROM tb_tipo_pagamento WHERE tb_tipo_pagamento.id = '" + pId + "'");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				this.fecharConexao();
			}
		}
		//metodo para alterar venda
		public boolean alterarTipoPagamentoDAO(ModelTipoPagamento pModelTipoPagamento) {
			try {
				this.conectar();
				return this.executarUpdateDeleteSQL("UPDATE tb_tipo_pagamento SET " 
						+ "nome = '" + pModelTipoPagamento.getNome()+ "' "
						+ "WHERE id = '" + pModelTipoPagamento.getId() + "'");
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				this.fecharConexao();
			}
		}
		/* Retorna um tipo pagamento em especifico atraves do id */
		public ModelTipoPagamento retornaTipoPagamentoDAO(int pId) {
			ModelTipoPagamento modelVendas = new ModelTipoPagamento();
			try {
				this.conectar();
				this.executarSQL("SELECT "
						+ "id,"
						+ "nome"
						+ " FROM tb_tipo_pagamento WHERE id = '"+pId+"';");
				
				while(this.getResultSet().next()) {
					modelVendas.setId(this.getResultSet().getInt(1));
					modelVendas.setNome(this.getResultSet().getString(2));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				this.fecharConexao();
			}
			return modelVendas;
		}
		/* Retorna um tipo pagamento em especifico atraves do nome */
		public ModelTipoPagamento retornaTipoPagamentoNomeDAO(String pNome) {
			ModelTipoPagamento modelVendas = new ModelTipoPagamento();
			try {
				this.conectar();
				this.executarSQL("SELECT "
						+ "id,"
						+ "nome"
						+ " FROM tb_tipo_pagamento WHERE nome = '"+pNome+"';");
				
				while(this.getResultSet().next()) {
					modelVendas.setId(this.getResultSet().getInt(1));
					modelVendas.setNome(this.getResultSet().getString(2));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				this.fecharConexao();
			}
			return modelVendas;
		}

}
