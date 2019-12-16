package controllers;

import org.junit.Before;

import play.mvc.Controller;

public class Seguranca extends Controller{
	
	
	
	@Before
 static void verificar () {
		if (session.contains("Cliente.nome") == false) {
			Login.form();
 }
}
}


