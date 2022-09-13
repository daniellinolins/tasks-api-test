package br.ce.wcaquino.tasks.apitest;

import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APItest {
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8080/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
		
		RestAssured.given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200)
		;
	}

	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		RestAssured.given()
			.body("{\"task\": \"Criado via teste de API\",\"dueDate\": \"2022-09-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.statusCode(201)
		;
	}

	@Test
	public void naodeveAdicionarTarefaInvalida() {
		RestAssured.given()
			.body("{\"task\": \"Não deve ser Criado via teste de API\",\"dueDate\": \"2020-09-30\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			//.log().all()
			.statusCode(400)
			.body("message", CoreMatchers.is("Due date must not be in past"))
		;
	}	
	
}
