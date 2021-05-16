package com.ti2cc;

public class Assinatura {
	private int id;
	private String nome;
	
	//metodo construtor vazio
	public Assinatura() {
		id = -1;
		nome = "";
	}
	
	//metodo construtor parametros 
	public Assinatura(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	//metodos set
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//metodos get
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	
	//converter para String
	public String dataToString() {
		return "Assinatura [id=" + id + ", nome=" + nome + "]";
	}
}
