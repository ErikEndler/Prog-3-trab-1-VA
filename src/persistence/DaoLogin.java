package persistence;

import java.util.ArrayList;

import model.ModelLogin;

public class DaoLogin extends Conexao {
	
	//metodo salvar login
			public int salvarLoginDAO(ModelLogin pModelLogin) {
				try {
					this.conectar();
					return this.insertSQL("INSERT INTO tb_login(" 
							+ "nome"
							+ "senha"
							+ ") VALUES (" 
							+ "'" + pModelLogin.getLogin() + "', " 
							+ "'" + pModelLogin.getSenha() + "');");
				} catch (Exception e) {
					e.printStackTrace();
					return 0;
				} finally {
					this.fecharConexao();
				}
			}
			//metodo para excluir login
			public boolean excluirLoginDAO(String pNome) {
				try {
					this.conectar();
					return this.executarUpdateDeleteSQL("DELETE FROM tb_login WHERE tb_login.nome = '" + pNome + "'");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					this.fecharConexao();
				}
			}
			//metodo para alterar Login
			public boolean alterarLoginDAO(ModelLogin pModelLogin) {
				try {
					this.conectar();
					return this.executarUpdateDeleteSQL("UPDATE tb_login SET " 
							+ "login = '" + pModelLogin.getLogin()+ "', "
							+ "senha - '" + pModelLogin.getSenha()+ "' "
							+ "WHERE login = '" + pModelLogin.getLogin() + "'");
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					this.fecharConexao();
				}
			}
			/* Retorna um login em especifico atraves do id */
			public ModelLogin retornaLoginDAO(String pLogin) {
				ModelLogin modelLogin = new ModelLogin();
				try {
					this.conectar();
					this.executarSQL("SELECT "
							+ "login,"
							+ "senha"
							+ " FROM tb_login WHERE login = '"+pLogin+"';");
					
					while(this.getResultSet().next()) {
						modelLogin.setLogin(this.getResultSet().getString(1));
						modelLogin.setSenha(this.getResultSet().getString(2));

					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					this.fecharConexao();
				}
				return modelLogin;
			}
			/* Retorna a lista de login */
			public ArrayList<ModelLogin> retornarListaLoginDAO(){
				ArrayList<ModelLogin> listaLogin = new ArrayList<>();
				ModelLogin modelLogin = new ModelLogin();
				try {
					this.conectar();
					this.executarSQL("SELECT "
							+ "login,"
							+ "senha "
							+ "FROM tb_login;");
					while(this.getResultSet().next()) {
						modelLogin = new ModelLogin();
						modelLogin.setLogin(this.getResultSet().getString(1));
						modelLogin.setSenha(this.getResultSet().getString(2));						
						listaLogin.add(modelLogin);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					this.fecharConexao();
				}
				return listaLogin;	
			}

}
