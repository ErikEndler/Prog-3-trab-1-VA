package persistence;

import java.util.ArrayList;

import model.ModelVendas;

public class DaoVendas extends Conexao {
	
	//metodo salvar venda
	public int salvarVendasDAO(ModelVendas pModelVenda) {
		try {
			this.conectar();
			return this.insertSQL("INSERT INTO tb_vendas(" 
					+ "data," 
					+ "cliente," 
					+ "tipo_pagamento,"
					+ "vendedor,"
					+ "produto,"
					+ "valor,"
					+ "quantidade,"
					+ "observacao"
					+ ") VALUES (" 
					+ "'" + pModelVenda.getData() + "',"  
					+ "'" + pModelVenda.getCliente() + "',"
					+ "'" + pModelVenda.getTipo_pagamento() + "',"
					+ "'" + pModelVenda.getVendedor() + "',"
					+ "'" + pModelVenda.getProduto() + "',"
					+ "'" + pModelVenda.getValor() + "',"
					+ "'" + pModelVenda.getQuantidade() + "',"
					+ "'" + pModelVenda.getObservacao() + "');");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			this.fecharConexao();
		}
	}
	
	//metodo para excluir venda
	public boolean excluirVendasDAO(int pId) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("DELETE FROM tb_vendas WHERE tb_vendas.id = '" + pId + "'");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	//metodo para alterar venda
	public boolean alterarVendasDAO(ModelVendas pModelVenda) {
		try {
			this.conectar();
			return this.executarUpdateDeleteSQL("UPDATE tb_vendas SET " 
					+ "data = '" + pModelVenda.getData()+ "'," 
					+ "cliente = '" + pModelVenda.getCliente()+ "',"
					+ "tipo_pagamento = '" + pModelVenda.getTipo_pagamento()+ "',"
					+ "vendedor = '" + pModelVenda.getVendedor()+ "',"
					+ "produto = '" + pModelVenda.getProduto()+ "',"
					+ "valor = '" + pModelVenda.getValor()+ "',"
					+ "quantidade = '" + pModelVenda.getQuantidade()+ "',"
					+ "observacao = '" + pModelVenda.getObservacao()+ "' " 
					+ "WHERE id = '" + pModelVenda.getId() + "'");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			this.fecharConexao();
		}
	}
	/* Retorna uma venda em especifico atraves do id */
	public ModelVendas retornaVendasDAO(int pId) {
		ModelVendas modelVendas = new ModelVendas();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ "id,"
					+ "data,"
					+ "cliente,"
					+ "tipo_pagamento,"
					+ "vendedor,"
					+ "produto,"
					+ "valor,"
					+ "quantidade,"
					+ "observacao"
					+ " FROM tb_vendas WHERE id = '"+pId+"';");
			
			while(this.getResultSet().next()) {
				modelVendas.setId(this.getResultSet().getInt(1));
				modelVendas.setData(this.getResultSet().getString(2));
				modelVendas.setCliente(this.getResultSet().getString(3));
				modelVendas.setTipo_pagamento(this.getResultSet().getInt(4));
				modelVendas.setVendedor(this.getResultSet().getInt(5));
				modelVendas.setProduto(this.getResultSet().getString(6));
				modelVendas.setValor(this.getResultSet().getDouble(7));
				modelVendas.setQuantidade(this.getResultSet().getInt(8));
				modelVendas.setObservacao(this.getResultSet().getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return modelVendas;
	}
	/* Retorna a lista de vendas */
	public ArrayList<ModelVendas> retornarListaVendasDAO(){
		ArrayList<ModelVendas> listaVendas = new ArrayList<>();
		ModelVendas modelVendas = new ModelVendas();
		try {
			this.conectar();
			this.executarSQL("SELECT "
					+ " id,"
					+ "data,"
					+ "cliente,"
					+ "tipo_pagamento,"
					+ "vendedor,"
					+ "produto,"
					+ "valor,"
					+ "quantidade,"
					+ "observacao "
					+ "FROM tb_vendas;");
			while(this.getResultSet().next()) {
				modelVendas = new ModelVendas();
				modelVendas.setId(this.getResultSet().getInt(1));
				modelVendas.setData(this.getResultSet().getString(2));
				modelVendas.setCliente(this.getResultSet().getString(3));
				modelVendas.setTipo_pagamento(this.getResultSet().getInt(4));
				modelVendas.setVendedor(this.getResultSet().getInt(5));
				modelVendas.setProduto(this.getResultSet().getString(6));
				modelVendas.setValor(this.getResultSet().getDouble(7));
				modelVendas.setQuantidade(this.getResultSet().getInt(8));
				modelVendas.setObservacao(this.getResultSet().getString(8));
				
				listaVendas.add(modelVendas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			this.fecharConexao();
		}
		return listaVendas;	
	}

}
