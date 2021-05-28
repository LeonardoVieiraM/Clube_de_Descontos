package com.ti2cc;


import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Principal {
	
	//Data:
	private static Map<Long, String> sessions;
	
	public static void init()
	{
		sessions = new HashMap<Long, String>();
	}
	
	public static long genSessionId()
	{
		Random rand = new Random();
		long result = rand.nextLong();
		while(sessions.containsKey(result))
			result = rand.nextLong();
		
		return result;
	}
	
	//Functions:
	public static DAO DAO() {
		DAO dao = new DAO();
		return dao;
	}
	
	
	private static Map<String, String> getQueryMap(String query)  
	 {  
	     String[] params = query.split("&");  
	     Map<String, String> map = new HashMap<String, String>();  
	     for (String param : params)  
	     {  
	         String name = param.split("=")[0];  
	         String value = param.split("=")[1];  
	         map.put(name, value);  
	     }  
	     return map;  
	 } 
	
	
	//Cliente	
	public Object inserirCliente(Request request, Response response){
		DAO dao = DAO();
		
		dao.conectar();
		Map<String, String> clienteMap = getQueryMap(request.body());
		String email = clienteMap.get("email");
		email.replace('\'', 'ÿ');
		email.replace('\"', 'ÿ');
		String nome =  clienteMap.get("nome");
		nome.replace('\'', 'ÿ');
		nome.replace('\"', 'ÿ');
		String nascimento =  "01/01/2000"; //request.queryParams("nascimento");
		String senha =  clienteMap.get("senha");
		senha.replace('\'', 'ÿ');
		senha.replace('\"', 'ÿ');
		
		int id_assinatura = Integer.parseInt(clienteMap.get("id_assinatura"));
		
		Cliente cliente = new Cliente(0,email,nome,nascimento,senha,id_assinatura);
		
		int status = dao.inserirCliente(cliente);
		response.status(status == 0 ? 201 : (status == -1 ? 202 : 203));
		
		if(status == 0)
		{
			long newId = genSessionId();
			sessions.put(newId, email);
			System.out.println("Cliente inserido; email: " + email + "; id:" + newId);
			return ((Long)newId).toString();
		}
		else 
			return status == -1 ? "Email ja existente" : "Erro no servidor2";
	}
	
	
	
	public Object retomarSessao(Request request, Response response)
	{
		DAO dao = DAO();
		
		dao.conectar();
		long id = Long.parseLong(request.params("sessionId"));
		System.out.println(request.body());
		String email = new String("");
		
		if(sessions.containsKey(id))
			email = sessions.get(id);
		
		if(!email.equals("") && dao.getCliente(email).getEmail().equals(email))
		{
			System.out.println("Sessao retomada de: " + email + "\n");
			response.status(201);
		}
		else
			response.status(202);
		
		System.out.println("Sessoes: " + sessions.toString() + "\n");
		
		return response.status() == 201 ? 
				"Autenticado com sucesso" :
				"Erro na autenticacao";
	}
	
	
	public Object encerrarSessao(Request request, Response response)
	{
		DAO dao = DAO();
		
		dao.conectar();
		long id = Long.parseLong(request.params("sessionId"));
		System.out.println(request.body());
		if(sessions.containsKey(id))
		{
			sessions.remove(id);
			response.status(201);
		}
		else
			response.status(202);
		
		return " ";
	}
	
	
	public Object excluirCliente(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		long id = Long.parseLong(request.params("sessionId"));
		String email = sessions.get(id);
		Cliente cliente = new Cliente();
		cliente.setEmail(email);
		Cliente cliente2 = dao.getCliente(email);
		
		if(cliente2.getEmail().equals(email))
		{
			dao.excluirCliente(cliente2);
			response.status(201);
			System.out.println("O cliente: " + email + " foi removido.");
		}
		else
		{
			System.out.println("Nao foi possivel remover o cliente: " + email);
			response.status(202);
		}
		
		return response.status() == 201 ? "O cliente: " + email + " foi removido."
										: ("Nao foi possivel remover o cliente: " + email);
		
	}
	
	public Object atualizarClienteEmail(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String email_novo =  request.queryParams("email_novo");
		String email_antigo =  request.queryParams("email");
		
		
		Cliente cliente = new Cliente();
		cliente.setEmail(email_novo);
		
		dao.atualizarClienteEmail(cliente, email_antigo);
		
		response.status(201);
		
		return "O email foi atualizado para: "+ email_novo ;
	}
	
	public Object atualizarClienteSenha(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String email =  request.queryParams("email");
		String nova_senha =  request.queryParams("nova_senha");
		
		
		Cliente cliente = new Cliente();
		cliente.setEmail(email);
		cliente.setSenha(nova_senha);
		
		
		dao.atualizarClienteSenha(cliente);
		
		response.status(201);
		
		return "A senha do cliente foi atualizada.";
	}
	
	public Object atualizarClienteAssinatura(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		int assinatura =  Integer.parseInt(request.queryParams("assinatura"));
		String email =  request.queryParams("email");
		
		
		Cliente cliente = new Cliente();
		cliente.setEmail(email);
		cliente.setAssinatura(assinatura);
		
		
		dao.atualizarClienteAssinatura(cliente);
		
		response.status(201);
		
		return "A assinatura do cliente foi atualizada.";
	}

	public Object loginCliente(Request request, Response response){
		boolean status = false;
		DAO dao = DAO();
		
		dao.conectar();
		
		Map<String, String> clienteMap = getQueryMap(request.body());
		
		String email = clienteMap.get("email");
		String senha =  clienteMap.get("senha");
	
		Cliente cliente = dao.getCliente(email, senha);
		
		if (cliente.getEmail().equals(email)) {
			
			//remove previous session
			while(sessions.containsValue(email))
			{
				long tmp = 0;
				for(Map.Entry<Long, String> entry : sessions.entrySet())
					if(entry.getValue().equals(email))
					{
						tmp = entry.getKey();
						break;
					}
				
				sessions.remove(tmp);
				System.out.println("AAAAAAAAa");
			}
			
			//create new session
			long newId = genSessionId();
			sessions.put(newId, email);
			System.out.println("Cliente inserido; email: " + email + "; id:" + newId);
			
			response.status(201);
			System.out.println("Login de: " + email + " realizado com sucesso.");
			return ((Long)newId).toString();
		}
			
		System.out.println("Login de: " + email + " Falhou.");
		response.status(400);
		
		return status;
	}
	
	//Loja
	
	public Object inserirLoja(Request request, Response response){
		DAO dao = DAO();
		
		dao.conectar();
		
		String email = request.queryParams("email");
		String nome =  request.queryParams("nome");
		String site =  request.queryParams("site");
		String senha =  request.queryParams("senha");
		
		Loja loja = new Loja(-1,email,nome,site,senha);
		
		dao.inserirLoja(loja);
		
		response.status(201);
		
		return "Nova loja:   " + loja.dataToString();
	}

	public Object excluirLoja(Request request, Response response) {

		DAO dao = DAO();
		
		dao.conectar();
		
		String email = request.queryParams("email");
		Loja loja = new Loja();
		loja.setEmail(email);
		dao.excluirLoja(loja);
		response.status(201);
		
		return "A loja: " + email + " foi removido.";
	}
	
	public Object atualizarLojaEmail(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String email_novo =  request.queryParams("email_novo");
		String email_antigo =  request.queryParams("email");
		
		
		Loja loja = new Loja();
		loja.setEmail(email_novo);
		
		dao.atualizarLojaEmail(loja, email_antigo);
		
		response.status(201);
		
		return "O email foi atualizado para: "+ email_novo ;
	}
	
	public Object atualizarLojaSenha(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String email =  request.queryParams("email");
		String nova_senha =  request.queryParams("nova_senha");
		
		
		Loja loja = new Loja();
		loja.setEmail(email);
		loja.setSenha(nova_senha);
		
		
		dao.atualizarLojaSenha(loja);
		
		response.status(201);
		
		return "A senha da loja foi atualizada.";
	}

	public Object atualizarLojaSite(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String email =  request.queryParams("email");
		String site =  request.queryParams("site");
		
		Loja loja = new Loja();
		loja.setEmail(email);
		loja.setSite(site);
		
		
		dao.atualizarLojaSite(loja);
		
		response.status(201);
		
		return "O site da loja foi atualizada.";
	}

	public boolean loginLoja(Request request, Response response){
		boolean status = false;
		DAO dao = DAO();
		
		dao.conectar();
		
		String email = request.queryParams("email");
		String senha =  request.queryParams("senha");
		
		
		Loja loja = dao.getLoja(email, senha);
		
		if (loja.getEmail().equals(email)) {
			status = true;
			response.status(201);
			return status;	
		}
				
		response.status(400);
		
		return status;
	}

	//Cupom
	
	public Object inserirCupom(Request request, Response response){
		DAO dao = DAO();
		
		dao.conectar();
		
		int id = Integer.parseInt(request.queryParams("id"));
		String codigo = request.queryParams("codigo");
		float desconto =  Float.parseFloat(request.queryParams("desconto"));
		int estoque = Integer.parseInt(request.queryParams("estoque"));
		String email_loja = request.queryParams("email_loja");
		int id_assinatura = Integer.parseInt(request.queryParams("id_assinatura"));
		
		Cupom cupom = new Cupom(id,codigo,desconto,estoque,-1,id_assinatura);
		
		dao.inserirCupom(cupom, email_loja);
		
		response.status(201);
		
		return "Novo cupom: " + cupom.dataToString();
	}

	public Object atualizarCupomEstoque(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String codigo =  request.queryParams("codigo");
		int estoque =  Integer.parseInt(request.queryParams("estoque"));
		
		
		Cupom cupom = new Cupom();
		cupom.setCodigo(codigo);
		cupom.setEstoque(estoque);
		
		dao.atualizarCupomEstoque(cupom);
		
		response.status(201);
		
		return "O estoque do cupom: " + codigo + " foi atualizado para: " + estoque;
	}
	
	public Object atualizarCupomDesconto(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String codigo =  request.queryParams("codigo");
		float desconto =  Float.parseFloat(request.queryParams("desconto"));
		
		
		Cupom cupom = new Cupom();
		cupom.setCodigo(codigo);
		cupom.setDesconto(desconto);
		
		dao.atualizarCupomDesconto(cupom);
		
		response.status(201);
		
		return "O desconto do cupom: " + codigo + " foi atualizado para: " + desconto;
	}

	public Object queimarCupom(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String codigo =  request.queryParams("codigo");
		
		
		dao.queimarCupom(codigo);
		
		response.status(201);
		
		return "O cupom foi queimado.";
	}

	public Object excluirCupom(Request request, Response response) {

		DAO dao = DAO();
		
		dao.conectar();
		
		String codigo = request.queryParams("codigo");
		Cupom cupom = new Cupom();
		cupom.setCodigo(codigo);
		dao.excluirCupom(cupom);
		response.status(201);
		
		return "O cupom: " + cupom + " foi removido.";
	}

	public Object listarCupons(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String sessionIdStr = request.params("sessionId");
		/*
		long sessionId = Long.parseLong(sessionIdStr);
		
		if(!sessions.containsKey(sessionId) || 
			!dao.getCliente(sessions.get(sessionId)).getEmail().equals(sessions.get(sessionId)))
		{	
			response.status(202);
			System.out.println("Falha na requisicao de cupons de: " + sessionId);
			return "";
		}
		response.status(201);
		
		int clienteId = dao.getCliente(sessions.get(sessionId)).getId();
		*/
		response.status(201);
		Cupom[] cupons = dao.getCupons();
		//System.out.println(historicoCupons.toString());
		
		//send the cupons in an JSON object
		JSONObject returnValue = new JSONObject();
		if (cupons == null) {
			return "";
		}
		else {
			
			JSONArray arr = new JSONArray();
			

			for (int i = 0; i < cupons.length; i++) {
				
				JSONObject cupom = new JSONObject();
				//System.out.println(historicoCupons[i].toString());
				cupom.put("id", cupons[i].getId());
				cupom.put("codigo", cupons[i].getCodigo());
				cupom.put("desconto", cupons[i].getDesconto());
				cupom.put("estoque", cupons[i].getEstoque());
				cupom.put("id_loja", cupons[i].getLoja());
				cupom.put("id_assinatura", cupons[i].getAssinatura());
				
				arr.put(cupom);
				//cupom.clear();
			}
			
			returnValue.put("cupons", arr);
		}
				
		System.out.println(returnValue.toString());
		return returnValue.toString();	
	}

	//Histórico

	public Object inserirHistorico(Request request, Response response){
		DAO dao = DAO();
		
		dao.conectar();
		
		Map<String, String> clienteMap = getQueryMap(request.body());
		String codigo = clienteMap.get("codigo_cupom");		
		String sessionIdStr = clienteMap.get("sessionId");
		long sessionId = Long.parseLong(sessionIdStr);
		if(!sessions.containsKey(sessionId) || 
			!dao.getCliente(sessions.get(sessionId)).getEmail().equals(sessions.get(sessionId)))
		{	
			response.status(202);
			System.out.println("Falha na insercao de historico de: " + sessionId);
			return "";
		}
		
		String email_cliente = sessions.get(sessionId);
		System.out.println("->" + request.body());
		dao.inserirHistorico(email_cliente, codigo);
		
		response.status(204);
		
		return "Novo registro no histórico.";
	}
	
	
	public Object listarHistorico(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		String sessionIdStr = request.params("sessionId");
		long sessionId = Long.parseLong(sessionIdStr);
		if(!sessions.containsKey(sessionId) || 
			!dao.getCliente(sessions.get(sessionId)).getEmail().equals(sessions.get(sessionId)))
		{	
			response.status(202);
			System.out.println("Falha na requisicao do historico de: " + sessionId);
			return "";
		}
		response.status(201);
		
		int clienteId = dao.getCliente(sessions.get(sessionId)).getId();
		Cupom[] historicoCupons = dao.cuponsDoCliente(clienteId);
		//System.out.println(historicoCupons.toString());
		
		//send the cupons in an JSON object
		JSONObject returnValue = new JSONObject();
		if (historicoCupons == null) {
			return "";
		}
		else {
			
			JSONArray arr = new JSONArray();
			

			for (int i = 0; i < historicoCupons.length; i++) {
				
				JSONObject cupom = new JSONObject();
				//System.out.println(historicoCupons[i].toString());
				cupom.put("id", historicoCupons[i].getId());
				cupom.put("codigo", historicoCupons[i].getCodigo());
				cupom.put("desconto", historicoCupons[i].getDesconto());
				cupom.put("estoque", historicoCupons[i].getEstoque());
				cupom.put("id_loja", historicoCupons[i].getLoja());
				cupom.put("id_assinatura", historicoCupons[i].getAssinatura());
				
				arr.put(cupom);
				//cupom.clear();
			}
			
			returnValue.put("cupons", arr);
		}
				
		System.out.println(returnValue.toString());
		return returnValue.toString();		
	}


}

/*	public Object listarBD(Request request, Response response) {
DAO dao = DAO();

dao.conectar();

StringBuffer returnValue = new StringBuffer("<funcionarios type=\"array\">");
for(Funcionario funcionario : dao.getFuncionarios()) {
	returnValue.append("\n<funcionario>\n" +
			"\t<codigo>" + funcionario.getCodigo() + "</codigo>\n" +
			"\t<salario>" + funcionario.getSalario() + "</salario>\n" +
			"\t<departamento>" + funcionario.getDepartamento() + "</departamento>\n" +
			"\t<primeiro_nome>" + funcionario.getPrimeiroNome() + "</primeiro_nome>\n" +
			"\t<ultimo_nome>" + funcionario.getUltimoNome() + "</ultimo_nome>\n" +
			"\t<sexo>" + funcionario.getSexo() + "</sexo>\n"
			);
	returnValue.append("</funcionarios>");
	response.header("Content-Type", "application/xml");
	response.header("Content-Encoding", "UTF-8");
	
	
}
		
return returnValue.toString();
}*/
