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
		String mydatabase = "postgres";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "postgres";
		String password = "zelda10";
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
	public int inserirCliente(Cliente cliente) { //CHECK
		int status = -2;
		int max = -1;
		try {
			//conseguir um id valido e testar se o email é valido: 
			
			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery("SELECT * FROM cliente");
				if (rs.next()) {
					rs.last();
					rs.beforeFirst();
					
					for (; rs.next(); ) { 
						if(max < rs.getInt("id"))
							max = rs.getInt("id");
						if(cliente.getEmail().equals(rs.getString("email")))
						{
							st.close();
							return -1;
						}
					}
				}
				st.close();
			}catch (Exception e) {
				System.err.println(e.getMessage());
				
			}
			
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO cliente(id,email,nome,nascimento,senha,id_assinatura) VALUES (" + (max + 1) + ", '" + cliente.getEmail() + "', '" + cliente.getNome() + "', '" 
			+ "2000-01-01" + "', '" + cliente.getSenha() + "', " + cliente.getAssinatura() + ");";
			
			
			st.executeUpdate(sql);
			st.close();
			status = 0;
		} catch (SQLException u) {
			return status;
		} 
		System.out.println((status == 0 ? ("Inserindo usuario. id: " + (max + 1)) : 
				"Erro: Nao foi possivel adicionar usuario"));
		return status;
	}
	
	
	//metodo atualizar valores de cliente na tabela
	public boolean atualizarClienteEmail (Cliente cliente, String email_antigo) { //CHECK
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
	
	public boolean atualizarClienteSenha (Cliente cliente) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cliente SET senha = '" + cliente.getSenha() + "' WHERE email = '" + cliente.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			return false;
		}
		return status;
	}
	
	public boolean atualizarClienteAssinatura (Cliente cliente) { //CHECK
		boolean status = false;
		try {
			System.out.println(cliente.getEmail());
			Statement st = conexao.createStatement();
			String sql = "UPDATE cliente SET id_assinatura = " + cliente.getAssinatura() + " WHERE email = '" + cliente.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			return false;
		}
		return status;
	}
	
	//metodo para excluir cliente da tabela
	public boolean excluirCliente (Cliente cliente) { //CHECK
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
	
	public Cliente getCliente(String email, String senha) { //CHECK
		Cliente[] clientes = null;
		Cliente cliente = new Cliente();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cliente where email ='" + email + "' and senha ='" + senha + "'");
			if (rs.next()) {
				rs.last();
				clientes = new Cliente[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next();) {
					clientes[i] = new Cliente();
					clientes[i].setEmail(rs.getString("email")); 
					clientes[i].setSenha(rs.getString("senha"));
					return clientes[0];
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return cliente;
		}
		return cliente;
		
	}
	
	
	//Loja
	
	//metodo para inserir loja na tabela
	public boolean inserirLoja(Loja loja) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO loja(email,nome,site,senha) VALUES ('"	+ loja.getEmail() + "', '" + loja.getNome() + "', '" 
			+ loja.getSite() + "', '" + loja.getSenha() + "')";
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			return false;
		}
		return status;
	}
	
	//metodo atualizar valores de loja na tabela
	public boolean atualizarLojaEmail (Loja loja, String email_antigo) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE loja SET email = '" + loja.getEmail() + "' WHERE email = '" + email_antigo + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			return false;
		}
		return status;
	}
	
	public boolean atualizarLojaSenha (Loja loja) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE loja SET senha = '" + loja.getSenha() + "' WHERE email = '" + loja.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			return false;
		}
		return status;
	}
	
	public boolean atualizarLojaSite (Loja loja) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE loja SET site = '" + loja.getSite() + "' WHERE email = '" + loja.getEmail() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			return false;
		}
		return status;
	}
	
		
	//metodo para excluir loja da tabela
	public boolean excluirLoja (Loja loja) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM loja WHERE email = '" + loja.getEmail() + "'";		
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			return  false;
		}
		return status;
	}
	
	public Loja getLoja(String email, String senha) { //CHECK
		Loja[] lojas = null;
		Loja loja = new Loja();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM loja where email ='" + email + "' and senha ='" + senha + "'");
			if (rs.next()) {
				rs.last();
				lojas = new Loja[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next();) {
					lojas[i] = new Loja();
					lojas[i].setEmail(rs.getString("email")); 
					lojas[i].setSenha(rs.getString("senha")); 
					return lojas[0];
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return loja;
		
	}
	

	//Cupom
	
	public Loja getIdLoja(String email) { //CHECK
		Loja[] lojas = null;
		Loja loja = new Loja();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM loja where email = '" + email + "'");
			if (rs.next()) {
				rs.last();
				lojas = new Loja[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next();) {
					lojas[i] = new Loja();
					lojas[i].setId(rs.getInt("id")); 
					return lojas[0];
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return loja;
		}
		return loja;
		
	}
	
	
	//metodo para inserir cupom na tabela
	public boolean inserirCupom(Cupom cupom, String email) { //CHECK
		boolean status = false;
		
		Loja loja = getIdLoja(email);
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO cupom(codigo,desconto,estoque,id_loja,id_assinatura) VALUES ('" + cupom.getCodigo() + "', " + cupom.getDesconto() + "," 
			+ cupom.getEstoque() + "," + loja.getId() + "," + cupom.getAssinatura() + ")";
			
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			return false;
		}
		return status;
	}
	
	//metodo atualizar estoque de cupom na tabela
	public boolean atualizarCupomEstoque (Cupom cupom) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cupom SET estoque = " + cupom.getEstoque() + " WHERE codigo = '" + cupom.getCodigo() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			//throw new RuntimeException(u);
			return false;
		}
		return status;
	}
	
	public boolean atualizarCupomDesconto (Cupom cupom) {//CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cupom SET desconto = " + cupom.getDesconto() + " WHERE codigo = '" + cupom.getCodigo() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	
	public Cupom localizarCupom(String codigo) { //CHECK
		Cupom[] cupons = null;
		Cupom cupom = new Cupom();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cupom where codigo = '" + codigo + "'");
			if (rs.next()) {
				rs.last();
				cupons = new Cupom[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next();) {
					cupons[i] = new Cupom(rs.getInt("id"), rs.getString("codigo"), rs.getFloat("desconto"),
					rs.getInt("estoque"), rs.getInt("id_loja"), rs.getInt("id_assinatura"));
					return cupons[0];
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return cupom;
		
	}
	
	public boolean queimarCupom (String codigo) { //CHECK
		boolean status = false;
		Cupom cupom = localizarCupom(codigo);
		int novo_estoque = cupom.getEstoque() - 1;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE cupom SET estoque = " + novo_estoque + " WHERE codigo = '" + cupom.getCodigo() + "'";
			st.executeUpdate(sql);
			
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	//metodo para excluir cupom da tabela
	public boolean excluirCupom (Cupom cupom) { //CHECK
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM cupom WHERE codigo = '" + cupom.getCodigo() + "'";		
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			//throw new RuntimeException(u);
			return false;
		}
		return status;
	}
	
	
	
	//pegar todos os cupons de uma loja
	public Cupom[]  getCupons(String email) {//CHECK
		Cupom[] cupons = null;
		Loja loja = getIdLoja(email);
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cupom where id_loja = '" + loja.getId() + "'");
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
	
	//pegar todos os cupons
	public Cupom[]  getCupons() {//CHECK
			Cupom[] cupons = null;

			try {
				Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = st.executeQuery("SELECT * FROM cupom;");
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
	
	public Cliente getIdCliente(String email) { //CHECK
		Cliente[] clientes = null;
		Cliente cliente = new Cliente();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM cliente where email = '" + email + "'");
			if (rs.next()) {
				rs.last();
				clientes = new Cliente[rs.getRow()];
				rs.beforeFirst();
				
				for (int i = 0; rs.next();) {
					clientes[i] = new Cliente();
					clientes[i].setId(rs.getInt("id")); 
					return clientes[0];
				}
			}
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
			return cliente;
		}
		return cliente;
		
	}
	
	
	public boolean inserirHistorico(String email, String codigo_cupom) { //CHECK
		boolean status = false;
		
		Cliente cliente = getIdCliente(email);
		Cupom cupom = localizarCupom(codigo_cupom);
		System.out.println("cupom inserido ao historico!" + email + ", " + cupom.getId());
		try {
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO historico(id_cliente,id_codigo) VALUES ("+ cliente.getId()+ ", "+ cupom.getId() + ");";
			
			
			st.executeUpdate(sql);
			
			st.close();
			status = true;
			
			
		} catch (SQLException u) {
			System.out.println("cupom inserido ao historico2!");
			return false;
		}
		return status;
		
	}
	
	public Historico[] getHistorico(String email_cliente) { //CHECK
		Historico[] registros = null;
		
		Cliente cliente = getIdCliente(email_cliente);
		//System.out.println(cliente.getId());
		
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//System.out.println("1");
			ResultSet rs = st.executeQuery("SELECT * FROM historico where id_cliente =" + cliente.getId());
			if (rs.next()) {
				rs.last();
				registros = new Historico[rs.getRow()];
				rs.beforeFirst();
					
				for (int i = 0; rs.next(); i++) {
					registros[i] = new Historico(rs.getInt("id_cliente"), rs.getInt("id_codigo"));
				}
			}
			//System.out.println("1");
			st.close();
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return registros;
		
	}
	
	


}





