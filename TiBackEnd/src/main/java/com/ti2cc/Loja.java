package com.ti2cc;

public class Loja {
	private int id;
	private String email;
	private String nome;
	private String site;
	private String senha;
	
	
	//metodo construtor vazio
	public Loja(){
		id = -1;
		email = "";
		nome = "";
		site = "";
		senha = "";
	}
	
	//metodo construtor parametros
	public Loja(int id, String email, String nome, String site, String senha) {
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.site = site;
		this.senha = senha;
	}
	
	//metodos set
	public void setId(int id) {
		this.id = id;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setSite(String site) {
		this.site = site;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//metodos get
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSite() {
		return site;
	}
	
	public String getSenha() {
		return senha;
	}
	
	//converter para String
	public String dataToString() {
		return "Loja [id=" + id + ", email=" + email + ", nome=" + nome + ", site=" + site + ", senha=" + senha + "]";
	}
}

