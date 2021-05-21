package com.ti2cc;


import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.*;

public class Principal {
	
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
		String nome =  clienteMap.get("nome");
		String nascimento =  "01/01/2000"; //request.queryParams("nascimento");
		String senha =  clienteMap.get("senha");
		int id_assinatura = Integer.parseInt(clienteMap.get("id_assinatura"));
													
		Cliente cliente = new Cliente(0,email,nome,nascimento,senha,id_assinatura);
		
		int status = dao.inserirCliente(cliente);
		response.status(status == 0 ? 201 : (status == -1 ? 202 : 203));
		
		if(status == 0)
			return email;
		else 
			return status == -1 ? "Email invalido2" : "Erro no servidor2";
	}
	
	
	public Object excluirCliente(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		String email = request.params("email");
		System.out.println(request.body());
		Cliente cliente = new Cliente();
		cliente.setEmail(email);
		dao.excluirCliente(cliente);
		response.status(201);
		
		System.out.println("O cliente: " + email + " foi removido.");
		return "O cliente: " + email + " foi removido.";
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

	public boolean loginCliente(Request request, Response response){
		boolean status = false;
		DAO dao = DAO();
		
		dao.conectar();
		
		Map<String, String> clienteMap = getQueryMap(request.body());
		String email = clienteMap.get("email");
		String senha =  clienteMap.get("senha");
	
		Cliente cliente = dao.getCliente(email, senha);
		
		if (cliente.getEmail().equals(email)) {
			status = true;
			response.status(201);
			System.out.println("Login de: " + email + " realizado com sucesso.");
			return status;	
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
		System.out.println(request.url());
		String returnValue = new String("");
		//String email = request.queryParams("email");

		Cupom[] cupons = dao.getCupons();
		if (cupons == null) {
			return "Nao há Cupons";
			
		}
		else {
			
			int i = 0;
			for(; i < cupons.length; ++i)
			{
				returnValue += "<div class=\"card col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\">" +                
                "<img class=\"bannerElem card-img-top\" src=\"../Assets/imgs/mainPageImg03-Prod.jpg\" alt=\"Card image cap\">" +
                "<div class=\"bannerElem card-body\">" +
                "<h5 class=\"bannerElem card-title\">" + cupons[i].getCodigo() + " - " + cupons[i].getDesconto() + "%</h5>" + 
                "<p class=\"bannerElem card-text\">Some quick example text to build on the card title and make up the bulk of the card\'s content.</p>" +
                "<button style=\"text-align:center; margin: 0; display:none; background-color: rgb(75, 75, 75); color: white;\" onclick= >ir para o site da loja </button>" +
                "<!--cuponId:" + cupons[i].getCodigo() + ";-->" +
                "</div>" +                       
                "</div>\n";
			}
			
			while(i % 4 != 0)
			{
				returnValue += "<div class=\"cardFill col-12 col-sm-6 col-md-3 col-lg-3 col-xl-3\"></div>\n";
				++i;
			}
			System.out.println(returnValue);
			/*
			returnValue.append("Cupons:   \n\n\n");
			for (int i = 0; i < cupons.length; i++) {
				if (i < (cupons.length - 1)) {
					returnValue.append(cupons[i].dataToString()+";   ");
					
				}
				else {
					returnValue.append(cupons[i].dataToString());
				}
			}
					
			System.out.println("|" + returnValue.toString() + "|");
			return returnValue.toString();		*/
		}
		
		return returnValue;
	}

	//Histórico

	public Object inserirHistorico(Request request, Response response){
		DAO dao = DAO();
		
		dao.conectar();
		
		Map<String, String> clienteMap = getQueryMap(request.body());
		String codigo = clienteMap.get("codigo_cupom");
		String email_cliente =  clienteMap.get("email_cliente");
		
		System.out.println("->" + request.body());
		dao.inserirHistorico(email_cliente, codigo);
		
		response.status(204);
		
		return "Novo registro no histórico.";
	}
	
	
	public Object listarHistorico(Request request, Response response) {
		DAO dao = DAO();
		
		dao.conectar();
		
		StringBuffer returnValue = new StringBuffer();
		String email_cliente = request.queryParams("email_cliente");

		Historico[] historico = dao.getHistorico(email_cliente);
		if (historico == null) {
			return null;
		}
		else {
			returnValue.append("Cupons:   \n\n\n");
			
			for (int i = 0; i < historico.length; i++) {
				if (i < (historico.length - 1)) {
					returnValue.append(historico[i].dataToString()+";   ");
				}
				else {
					returnValue.append(historico[i].dataToString());
				}
			}
		}
				
		
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
