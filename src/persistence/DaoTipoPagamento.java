package persistence;

import java.util.ArrayList;

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
		//metodo para alterar Tip ode venda
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
			ModelTipoPagamento modelTipoPagamento = new ModelTipoPagamento();
			try {
				this.conectar();
				this.executarSQL("SELECT "
						+ "id,"
						+ "nome "
						+ " FROM tb_tipo_pagamento WHERE nome = '"+pNome+"' ;");
				
				while(this.getResultSet().next()) {
					modelTipoPagamento.setId(this.getResultSet().getInt(1));
					modelTipoPagamento.setNome(this.getResultSet().getString(2));

				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				this.fecharConexao();
			}
			return modelTipoPagamento;
		}
		
		/* Retorna a lista de tipos de vendas */
		public ArrayList<ModelTipoPagamento> retornarListaTiposDAO(){
			ArrayList<ModelTipoPagamento> listaTipos = new ArrayList<>();
			ModelTipoPagamento modelTipo = new ModelTipoPagamento();
			try {
				this.conectar();
				this.executarSQL("SELECT "
						+ "id,"
						+ "nome "
						+ "FROM tb_tipo_pagamento;");
				while(this.getResultSet().next()) {
					modelTipo = new ModelTipoPagamento();
					modelTipo.setId(this.getResultSet().getInt(1));
					modelTipo.setNome(this.getResultSet().getString(2));					
					listaTipos.add(modelTipo);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				this.fecharConexao();
			}
			return listaTipos;	
		}

}
