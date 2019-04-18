package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ModelVendas {
	private int id;
	private String cliente;
	private int tipo_pagamento;
	private String Vendedor;
	private String produto;
	private double valor;
	private int quantidade;
	private String observacao;
	private Date data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public int getTipo_pagamento() {
		return tipo_pagamento;
	}
	public void setTipo_pagamento(int tipo_pagamento) {
		this.tipo_pagamento = tipo_pagamento;
	}
	public String getVendedor() {
		return Vendedor;
	}
	public void setVendedor(String vendedor) {
		Vendedor = vendedor;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	//lista para implementa��o de DAO generico
	public List<String> getCamposnome(){
		ArrayList<String> listNomes = new ArrayList<>();
		listNomes.add("id");
		listNomes.add("data");
		listNomes.add("cliente");
		listNomes.add("tipo_pagamento");
		listNomes.add("vendedor");
		listNomes.add("produto");
		listNomes.add("valor");
		listNomes.add("quantidade");
		listNomes.add("observacao");		
		return listNomes;		
	}


}
