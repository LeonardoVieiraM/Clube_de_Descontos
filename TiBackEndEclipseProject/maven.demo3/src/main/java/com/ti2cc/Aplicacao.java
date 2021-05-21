package com.ti2cc;
import static spark.Spark.*;

	public class Aplicacao {
		
		private static Principal clienteService = new Principal();
		private static Principal lojaService = new Principal();
		private static Principal cupomService = new Principal();
		private static Principal historicoService = new Principal();
		public static void main(String[] args){
			port(4567);
			
			/*
			options("/cupons/*",
		            (request, response) -> {

		                String accessControlRequestHeaders = request
		                        .headers("Access-Control-Request-Headers");
		                if (accessControlRequestHeaders != null) {
		                    response.header("Access-Control-Allow-Headers",
		                            accessControlRequestHeaders);
		                }

		                String accessControlRequestMethod = request
		                        .headers("Access-Control-Request-Method");
		                if (accessControlRequestMethod != null) {
		                    response.header("Access-Control-Allow-Methods",
		                            accessControlRequestMethod);
		                }

		                return "OK";
		            });

		    before("/cupons/*", (request, response) -> response.header("Access-Control-Allow-Origin", "*"));
			*/
			//Cliente
			post("/cliente", (request, response) -> clienteService.inserirCliente(request, response));
			get("/cliente/delete/:email", (request, response) -> clienteService.excluirCliente(request, response));
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
			get("/cupons/", (request, response) -> cupomService.listarCupons(request, response));
			
			//Historico
			post("/historico/add", (request, response) -> historicoService.inserirHistorico(request, response));
			get("/historico", (request, response) -> historicoService.listarHistorico(request, response));

			
		}

		
}
	
	
//OBS: Dúvidas sobre quais métodos são post e quais são get