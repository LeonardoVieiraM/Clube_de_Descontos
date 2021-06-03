package com.ti2cc;
import static spark.Spark.*;


public class Aplicacao {
		
	private static Principal clienteService = new Principal();
	private static Principal lojaService = new Principal();
	private static Principal cupomService = new Principal();
	private static Principal historicoService = new Principal();
	public static void main(String[] args){
		Principal.init();
		port(4567);
		
		//Cliente
		post("/cliente", (request, response) -> clienteService.inserirCliente(request, response));
		get("/cliente/autenticar/:sessionId", (request, response) -> clienteService.retomarSessao(request, response));
		get("/cliente/delete/:sessionId", (request, response) -> clienteService.excluirCliente(request, response));
		get("/cliente/update/email", (request, response) -> clienteService.atualizarClienteEmail(request, response)); 
		get("/cliente/update/password", (request, response) -> clienteService.atualizarClienteSenha(request, response)); 
		get("/cliente/update/license", (request, response) -> clienteService.atualizarClienteAssinatura(request, response));
		post("/cliente/login", (request, response) -> clienteService.loginCliente(request, response));
		
		
		//Loja
		post("/loja", (request, response) -> lojaService.inserirLoja(request, response));
		get("/loja/delete/", (request, response) -> lojaService.excluirLoja(request, response));
		get("/loja/update/email", (request, response) -> lojaService.atualizarLojaEmail(request, response)); 
		get("/loja/update/password", (request, response) -> lojaService.atualizarLojaSenha(request, response)); 
		get("/loja/update/site", (request, response) -> lojaService.atualizarLojaSite(request, response));
		post("/loja/login", (request, response) -> lojaService.loginLoja(request, response));
		
		//Cupom
		post("/cupom", (request, response) -> cupomService.inserirCupom(request, response));
		get("/cupom/delete/", (request, response) -> cupomService.excluirCupom(request, response));
		get("/cupom/update/stock", (request, response) -> cupomService.atualizarCupomEstoque(request, response));
		get("/cupom/update/discount", (request, response) -> cupomService.atualizarCupomDesconto(request, response));
		get("/cupom/update/used", (request, response) -> cupomService.queimarCupom(request, response));
		get("/cupons/:sessionId", (request, response) -> cupomService.listarCupons(request, response));
		get("/cupons/pesquisar/:searchWord", (request, response) -> cupomService.pesquisarCupons(request, response));
		
		//Historico
		post("/historico/add", (request, response) -> historicoService.inserirHistorico(request, response));
		get("/historico/:sessionId", (request, response) -> historicoService.listarHistorico(request, response));
	}

		
}
	
	
//OBS: Dúvidas sobre quais métodos são post e quais são get