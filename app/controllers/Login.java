package controllers;

import enums.TipoUsuario;
import models.Usuario;
import models.Restaurante;
import play.mvc.Controller;

public class Login extends Controller {
	public static void form() {
		render();
	}

	public static void cadastro() {
		render();
	}
	
	public static void logar(String email, String senha) {
		 		
		Usuario cli = Usuario.find("email = ?1 and senha =?2", email, senha).first();
		
		if(cli == null) {
			form();
		} else {
			session.put("usuario.id", cli.id);
			session.put("NomeCliente", cli.nome);			
			session.put("EmailCliente", cli.email);	
			if(cli.tipoUsuario.equals(TipoUsuario.PADRAO)) {
				session.put("ClienteID", cli.id);
			}

			if(cli.tipoUsuario.equals(TipoUsuario.PADRAO)) {
				PainelUsuarioPadrao.index();
			}else if(cli.tipoUsuario.equals(TipoUsuario.ADMIN)) {
				 Clientes.form();
			}else if(cli.tipoUsuario.equals(TipoUsuario.ADMIN_RESTAURANTE)) {
				PainelAdminRestaurante.cadastro();
				Restaurante restaurante = Restaurante.find("administrador = ?", cli).first();
				if(restaurante == null) {
					flash.error("Nenhum restaurante associado a este usuário. Por favor, contactar o administrador.");
					session.clear();
					form();
				}
				session.put("IdRestarante", restaurante.id);
				PainelAdminRestaurante.cadastro();
			}
			
		}
		
	}
	
	public static void sair() {
		session.clear();
		form();
	}
}
