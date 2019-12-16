package jobs;

import java.io.File;

import enums.TipoUsuario;
import models.Usuario;
import models.Restaurante;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Inicializador extends Job {

	@Override
	public void doJob() throws Exception {
		
		Usuario cli3 = null;
		
		
		if (Usuario.count() == 0) {

			Usuario cli1 = new Usuario();
			cli1.nome = "Jadson Maia";
			cli1.email = "jadson@exemplo.com";
			cli1.senha = "123";

			cli1.tipoUsuario = TipoUsuario.PADRAO;
			cli1.save();

			Usuario cli2 = new Usuario();
			cli2.nome = "Dono";
			cli2.email = "sistema@exemplo.com";
			cli2.senha = "123";
			cli2.tipoUsuario = TipoUsuario.ADMIN;
			cli2.save();

			cli3 = new Usuario();
			cli3.nome = "adminRestaurante";
			cli3.email = "admin@exemplo.com";
			cli3.senha = "123";			
			cli3.tipoUsuario = TipoUsuario.ADMIN_RESTAURANTE;
			cli3.save();
		}

		if (Restaurante.count() == 0) {

			Restaurante res1 = new Restaurante();
			res1.nome = "Burguer King";
			res1.endereco = "Rua Tenente Vitor";
			res1.numero = "9102321321";
			res1.cardapios = "A melhor comida de Macau";
			res1.administrador = cli3;
			res1.save();

			Restaurante res2 = new Restaurante();
			res2.nome = "Sun7";
			res2.endereco = "Praia de Camapum";
			res2.cardapios = "A melhor comida de Macau 2";
			res2.numero = "123";
			res2.save();

			Restaurante res3 = new Restaurante();
			res3.nome = "adminRestaurante";
			res3.endereco = "rua exemplo";
			res3.cardapios = "A melhor comida de Macau 3";
			res3.numero = "123414312";
			res3.save();
		}
	}
}
