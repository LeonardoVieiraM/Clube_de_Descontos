package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	
	// metodo construtor
	public DAO() {
		conexao = null;
	}
	
	//metodo para conectar c banco de dados
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "TI2";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "postgres";
		String password = "Bacon1000";
		boolean status = false;
		
		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexao com postgres efetuada!");
		}
		catch (ClassNotFoundException e){
			System.err.println("Conexao nao efetuada com o postgres... driver nao encontrado..." + e.getMessage());
		}
		catch (SQLException e) { 
			System.err.println("Conexao nao efetuada com o postgres..." + e.getMessage());
		}
		
		return status;
	}
	
	
	//metodo para fechar conexao
	public boolean close() {
		boolean status = false;
		try {
			conexao.close();
			status = true;			
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
		
	}
	
	//Cliente
	
	//metodo para inserir cliente na tabela
	public boolean inserirCliente(Cliente cliente) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO cliente(email,nome,nascimento,senha,id_assinatura) VALUES ('"	+ cliente.getEmail() + "', '" + cliente.getNome() + "', '" 
			+ cliente.getNascimento() + "', '" + cliente.getSenha() + "', " + cliente.getAssinatura() + ")";
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	//metodo atualizar valores de cliente na tabela
	public boolean atualizarClienteEmail (Cliente cliente, String email_antigo) { //ver com os meninos se eles preferem id ou email
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cliente SET email = '" + cliente.getEmail() + "' WHERE email = '" + email_antigo + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarClienteSenha (Cliente cliente) { //ver com os meninos se eles preferem id ou email
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cliente SET senha = '" + cliente.getSenha() + "' WHERE email = '" + cliente.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarClienteAssinatura (Cliente cliente) { //ver com os meninos se eles preferem id ou email
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cliente SET id_assinatura = " + cliente.getAssinatura() + " WHERE email = '" + cliente.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	//metodo para excluir cliente da tabela
	public boolean excluirCliente (Cliente cliente) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM cliente WHERE email = '" + cliente.getEmail() + "'";		
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Cliente getCliente(String email, String senha) {
		Cliente[] clientes = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cliente where email ='" + email + "' and senha ='" + senha + "'");
			if (rs.next()) {
				rs.last();
				clientes = new Cliente[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next(); i++) {
					clientes[i] = new Cliente();
					clientes[i].setEmail(rs.getString("email")); 
					clientes[i].setSenha(rs.getString("senha")); 
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return clientes[0];
		
	}
	
	
	//Loja
	
	//metodo para inserir loja na tabela
	public boolean inserirLoja(Loja loja) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO loja(email,nome,site,senha) VALUES ('"	+ loja.getEmail() + "', '" + loja.getNome() + "', '" 
			+ loja.getSite() + "', '" + loja.getSenha() + "')";
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	//metodo atualizar valores de loja na tabela
	public boolean atualizarLojaEmail (Loja loja, String email_antigo) { //ver com os meninos se eles preferem id ou email
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE loja SET email = '" + loja.getEmail() + "' WHERE email = '" + email_antigo + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarLojaSenha (Loja loja) { //ver com os meninos se eles preferem id ou email
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE loja SET senha = '" + loja.getSenha() + "' WHERE email = '" + loja.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarLojaSite (Loja loja) { //ver com os meninos se eles preferem id ou email
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE loja SET site = '" + loja.getSite() + "' WHERE email = '" + loja.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
		
	//metodo para excluir loja da tabela
	public boolean excluirLoja (Loja loja) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM loja WHERE email = '" + loja.getEmail() + "'";		
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public Loja getLoja(String email, String senha) {
		Loja[] lojas = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM loja where email ='" + email + "' and senha ='" + senha + "'");
			if (rs.next()) {
				rs.last();
				lojas = new Loja[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next(); i++) {
					lojas[i] = new Loja();
					lojas[i].setEmail(rs.getString("email")); 
					lojas[i].setSenha(rs.getString("senha")); 
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return lojas[0];
		
	}
	

	//Cupom
	
	//metodo para inserir cupom na tabela
	public boolean inserirCupom(Cupom cupom) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO cupom(codigo,desconto,estoque,id_loja,id_assinatura) VALUES ('" + cupom.getCodigo() + "', " + cupom.getDesconto() + "," 
			+ cupom.getEstoque() + "," + cupom.getLoja() + "," + cupom.getAssinatura() + ")";
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	//metodo atualizar estoque de cupom na tabela
	public boolean atualizarCupomEstoque (Cupom cupom) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cupom SET estoque = " + cupom.getEstoque() + " WHERE id = '" + cupom.getId() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarCupomDesconto (Cupom cupom) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cupom SET desconto = " + cupom.getDesconto() + " WHERE id = '" + cupom.getId() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	
	public Cupom localizarCupom(int id_cupom) {
		Cupom[] cupons = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cupom where id =" + id_cupom);
			if (rs.next()) {
				rs.last();
				cupons = new Cupom[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; i == 1; i++) {
					cupons[i] = new Cupom(rs.getInt("id"), rs.getString("codigo"), rs.getFloat("desconto"),
					rs.getInt("estoque"), rs.getInt("id_loja"), rs.getInt("id_assinatura"));
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cupons[0];
		
	}
	
	public boolean queimarCupom (int id_cupom) { //dar select no numero de estoque do cupom pelo id,retirar uma unidade e dar um update
		boolean status = false;
		Cupom cupom = localizarCupom(id_cupom);
		int novo_estoque = cupom.getEstoque() - 1;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cupom SET estoque = " + novo_estoque + " WHERE id = '" + cupom.getId() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	//metodo para excluir cupom da tabela
	public boolean excluirCupom (Cupom cupom) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM cupom WHERE id = " + cupom.getId();		
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	
	//pegar todos os cupons de uma loja
	public Cupom[]  getCupons(int id_loja) {
		Cupom[] cupons = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cupom where id_loja =" + id_loja);
			if (rs.next()) {
				rs.last();
				cupons = new Cupom[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next(); i++) {
					cupons[i] = new Cupom(rs.getInt("id"), rs.getString("codigo"), rs.getFloat("desconto"),
					rs.getInt("estoque"), rs.getInt("id_loja"), rs.getInt("id_assinatura"));
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cupons;
		
	}
		
	
	
	//Histórico
	public Historico[] getHistorico(int id_cliente) {
		Historico[] registros = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM historico where id_cliente =" + id_cliente);
			if (rs.next()) {
				rs.last();
				registros = new Historico[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next(); i++) {
					registros[i] = new Historico(rs.getInt("id_cliente"), rs.getInt("id_codigo"));
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return registros;
		
	}
	
	
}
