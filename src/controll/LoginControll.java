package controll;

import java.util.ArrayList;

import model.ModelLogin;
import persistence.DaoLogin;


public class LoginControll {
	
	private DaoLogin daoLogin = new DaoLogin();

	public int salvarLoginControle(ModelLogin pModelLogin) {
		return this.daoLogin.salvarLoginDAO(pModelLogin);
	}

	public boolean excluirLoginControle(String pNome) {
		return this.daoLogin.excluirLoginDAO(pNome);
	}

	public boolean alterarLoginControle(ModelLogin pModelLogin) {
		return this.daoLogin.alterarLoginDAO(pModelLogin);
	}

	public ModelLogin retornarLoginControle(String pNome) {
		return this.daoLogin.retornaLoginDAO(pNome);	
	}

	public ArrayList<ModelLogin> retornaListaLoginControle() {
		return this.daoLogin.retornarListaLoginDAO();
	}

}
