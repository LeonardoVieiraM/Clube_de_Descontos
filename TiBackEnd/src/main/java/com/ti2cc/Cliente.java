package com.ti2cc;

public class Cliente {
	private int id;
	private String email;
	private String nome;
	private String nascimento;
	private String senha;
	private int id_assinatura;
	//metodo construtor vazio
	public Cliente() {
		id = -1;
		email = "";
		nome = "";
		nascimento = "";
		senha = "";
		id_assinatura = -1;
		
	}
	//metodo construtor parametros
	public Cliente(int id, String email, String nome, String nascimento, String senha, int id_assinatura) {
		
		this.id = id;
		this.email = email;
		this.nome = nome;
		this.nascimento = nascimento;
		this.senha = senha;
		this.id_assinatura = id_assinatura;
		
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
	
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void setAssinatura(int id_assinatura) {
		this.id_assinatura = id_assinatura;
	}
	
	//metodos get
	
	public int getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNome () {
		return nome;
	}
	
	public String getNascimento() {
		return nascimento;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public int getAssinatura() {
		return id_assinatura;
	}
	
	//converter dados para String
	public String dataToString() {
		return "Cliente [id=" + id + ", email=" + email + ", nome=" + nome + ", nascimento=" + nascimento + ", senha=" + senha + ", id_assinatura=" + id_assinatura + "]";
	}
}

