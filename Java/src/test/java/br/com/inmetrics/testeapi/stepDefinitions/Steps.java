package br.com.inmetrics.testeapi.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {
	

	private static final String USERNAME = "inmetrics";
	private static final String PASSWORD = "automacao";
	private static final String BASE_URL = "http://inm-api-test.herokuapp.com";

	private static Response response;

	
	@Given("Que eu esteja autenticado")
	public void que_eu_esteja_autenticado() {
			System.out.println("Eu estou autenticado");
	}

	@When("Eu solicito o cadastro do usuario")
	public void eu_solicito_o_cadastro_do_usuario() {
			System.out.println("Solicito cadastro");
			RestAssured.baseURI = BASE_URL;
			
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			request.auth().preemptive().basic(USERNAME, PASSWORD);
			response = request.body(" {\n" + 
					"        \"nome\": \"Pitoco Oliveira 2\",\n" + 
					"        \"sexo\": \"m\",\n" + 
					"        \"cpf\": \"116.665.435-43\",\n" + 
					"        \"cargo\": \"Engenheiro de Teste\",\n" + 
					"        \"departamentoId\": 1,\n" + 
					"        \"admissao\": \"25/09/2019\",\n" + 
					"        \"salario\": \"4.500,00\",\n" + 
					"        \"comissao\": \"0,00\",\n" + 
					"        \"tipoContratacao\": \"clt\"\n" + 
					"}").post("/empregado/cadastrar");			
			if (response.getStatusCode()==202) {
				System.out.println("Cadastro aceito!");
			
			}
			
			

	}

	@Then("Eu recebo reposta ok")
	public void eu_recebo_reposta_ok() {
	    // Write code here that turns the phrase above into concrete actions
	    	System.out.println("Recebo resposta OK");
	}

	@When("Eu solicito a listagem de todos os usuarios")
	public void eu_solicito_a_listagem_de_todos_os_usuarios() {

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
		request.auth().preemptive().basic(USERNAME, PASSWORD);
		response = request.get(BASE_URL+"/empregado/list_all");

		if (response.getStatusCode()==200) {
			System.out.println("Retorno API Ok");
		
		}
		
	}

	@Then("Eu recebo a lista de todos os usuarios")
	public void eu_recebo_a_lista_de_usuarios() {

		JsonPath jsonPathEvaluator = response.jsonPath();
		String nome = jsonPathEvaluator.getString("nome");
		System.out.println("Listagem: " + nome );
		
	}
	
	@When("Eu solicito listar usuario {string}")
	public void eu_solicito_listar_usuario(String usuarioId) {
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		
		request.header("Content-Type", "application/json");
		request.auth().preemptive().basic(USERNAME, PASSWORD);
		response = request.get(BASE_URL + "/empregado/list/" + usuarioId);;

		if (response.getStatusCode()==200) {
			System.out.println("Retorno API Ok");
		
		}		

	}

	@Then("Eu recebo o registro do usuario")
	public void eu_recebo_o_registro_do_usuario() {

		String jsonString = response.asString();
		System.out.println("Usuario Listado: " + jsonString);

		
	}
	@When("Eu solicito alterar usuario {string} do {string} pelo {string}")
	public void eu_solicito_alterar_usuario_do_pelo(String usuarioId, String campo, String valor) {
		System.out.println("Solicito alteracao");
		RestAssured.baseURI = BASE_URL;
		
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		request.auth().preemptive().basic(USERNAME, PASSWORD);
		response = request.body(" {\n" + 
				"        \"nome\": \""+ valor + "\",\n" + 
				"        \"sexo\": \"m\",\n" +
				"        \"cpf\": \"116.665.435-43\",\n" + 
				"        \"cargo\": \"Engenheiro de Teste\",\n" + 
				"        \"departamentoId\": 1,\n" + 
				"        \"admissao\": \"25/09/2019\",\n" + 
				"        \"salario\": \"4.500,00\",\n" + 
				"        \"comissao\": \"0,00\",\n" + 
				"        \"tipoContratacao\": \"clt\"\n" + 
				"}").put("/empregado/alterar/" + usuarioId);			
		if (response.getStatusCode()==202) {
			System.out.println("Alteracao aceita!");
		
		}
		
	}
	
}
