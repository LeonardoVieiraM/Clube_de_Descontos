package com.ti2cc;

public class Cupom {
	private int id;
	private String codigo;
	private float desconto;
	private int estoque;
	private int id_loja;
	private int id_assinatura;
	
	//metodo construtor vazio	
	public Cupom(){
		id = -1;
		codigo = "";
		desconto = -1;
		estoque = -1;
		id_loja = -1;
		id_assinatura = -1;
	}
	//metodo construtor parametros
	public Cupom(int id, String codigo, float desconto, int estoque, int id_loja, int id_assinatura) {
		this.id = id;
		this.codigo = codigo;
		this.desconto = desconto;
		this.estoque = estoque;
		this.id_loja = id_loja;
		this.id_assinatura = id_assinatura;
	}
	
	//metodos set
	public void setId(int id) {
		this.id = id;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}
	
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	
	public void setLoja(int id_loja) {
		this.id_loja = id_loja;
	}
	
	public void setAssinatura(int id_assinatura) {
		this.id_assinatura = id_assinatura;
	}
	
	//metodos get
	public int getId() {
		return id;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public float getDesconto() {
		return desconto;
	}
	
	public int getEstoque() {
		return estoque;
	}
	
	public int getLoja() {
		return id_loja;
	}
	
	public int getAssinatura() {
		return id_assinatura;
	}
	
	//converter para String
	public String dataToString() {
		return "Cupom [id=" + id + ", codigo=" + codigo + ", desconto=" + desconto + ", estoque=" + estoque + ", id_loja=" + id_loja + ", id_assinatura=" + id_assinatura + "]";
	}
}

