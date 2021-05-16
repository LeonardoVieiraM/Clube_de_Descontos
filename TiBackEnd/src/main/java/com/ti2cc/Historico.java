package com.ti2cc;

public class Historico {
	private int id_cliente;
	private int id_cupom;
	
	//metodo construtor vazio
	public Historico() {
		id_cliente = -1;
		id_cupom = -1;
	}
	
	//metodo construtor parametros
	public Historico(int id_cliente, int id_cupom) {
		this.id_cliente = id_cliente;
		this.id_cupom = id_cupom;
	}

	//metodos set
	public void setCliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	public void setCupom(int id_cupom) {
		this.id_cupom = id_cupom;
	}
	
	//metodos get
	public int getCliente() {
		return id_cliente;
	}
	
	public int getCupom() {
		return id_cupom;
	}
	
	//converter para String
	public String dataToString() {
		return "Assinatura [id_cliente=" + id_cliente + ", id_cupom=" + id_cupom + "]";
	}
	
}


