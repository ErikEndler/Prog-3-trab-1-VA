package persistence;

import java.util.ArrayList;

import model.ModelTipoPagamento;
import model.ModelVendedor;

public class DaoVendedor extends Conexao {
	
	//metodo salvar vendedor
			public int salvarVendedorDAO(ModelVendedor pModelVendedor) {
				try {
					this.conectar();
					return this.insertSQL("INSERT INTO tb_vendedor(" 
							+ "nome"
							+ ") VALUES (" 
							+ "'" + pModelVendedor.getNome() + "');");
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				} finally {
					this.fecharConexao();
				}
			}
			//metodo para excluir vendedor
			public boolean excluirVendedorDAO(int pId) {
				try {
					this.conectar();
					return this.executarUpdateDeleteSQL("DELETE FROM tb_vendedor WHERE tb_vendedor.id = '" + pId + "'");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					this.fecharConexao();
				}
			}
			//metodo para alterar Vendedor
			public boolean alterarVendedorDAO(ModelVendedor pModelVendedor) {
				try {
					this.conectar();
					return this.executarUpdateDeleteSQL("UPDATE tb_vendedor SET " 
							+ "nome = '" + pModelVendedor.getNome()+ "' "
							+ "WHERE id = '" + pModelVendedor.getId() + "'");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					this.fecharConexao();
				}
			}
			/* Retorna um vendedor em especifico atraves do id */
			public ModelVendedor retornaVendedorDAO(int pId) {
				ModelVendedor modelVendedor = new ModelVendedor();
				try {
					this.conectar();
					this.executarSQL("SELECT "
							+ "id,"
							+ "nome"
							+ " FROM tb_vendedor WHERE id = '"+pId+"';");
					
					while(this.getResultSet().next()) {
						modelVendedor.setId(this.getResultSet().getInt(1));
						modelVendedor.setNome(this.getResultSet().getString(2));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					this.fecharConexao();
				}
				return modelVendedor;
			}
			/* Retorna um venedor em especifico atraves do nome */
			public ModelVendedor retornaVendedorNomeDAO(String pNome) {
				ModelVendedor modelVendedor = new ModelVendedor();
				try {
					this.conectar();
					this.executarSQL("SELECT "
							+ "id,"
							+ "nome "
							+ " FROM tb_vendedor WHERE nome = '"+pNome+"' ;");
					
					while(this.getResultSet().next()) {
						modelVendedor.setId(this.getResultSet().getInt(1));
						modelVendedor.setNome(this.getResultSet().getString(2));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					this.fecharConexao();
				}
				return modelVendedor;
			}
			
			/* Retorna a lista de vendedores */
			public ArrayList<ModelVendedor> retornarListaVendedoresDAO(){
				ArrayList<ModelVendedor> listaVendedores = new ArrayList<>();
				ModelVendedor modelVendedor = new ModelVendedor();
				try {
					this.conectar();
					this.executarSQL("SELECT "
							+ "id,"
							+ "nome "
							+ "FROM tb_vendedor;");
					while(this.getResultSet().next()) {
						modelVendedor = new ModelVendedor();
						modelVendedor.setId(this.getResultSet().getInt(1));
						modelVendedor.setNome(this.getResultSet().getString(2));					
						listaVendedores.add(modelVendedor);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					this.fecharConexao();
				}
				return listaVendedores;	
			}


}
