package model;

import java.util.ArrayList;
import java.util.List;

public class Tipo_Pagamento {
	private int id;
	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	//lista para implementação de DAO generico
	public List<String> getCamposNome() {
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("id");
		listNomes.add("nome");
		return listNomes ;
	}

}
