package controllers;

import models.Promocao;
import models.Restaurante;
import models.Usuario;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class PainelAdminRestaurante extends Controller{
	
	public static void index() {
		Usuario usuario = Usuario.findById(Long.parseLong(session.get("usuario.id")));
		Restaurante restaurante = Restaurante.find("administrador = ?", usuario).first();
        render(restaurante);
	}
	
	public static void mostrarImagemPromocao(Long id) {
		Promocao promocao = Promocao.findById(id);
		renderBinary(promocao.foto.get());
	}
	
	
	public static void cadastro () {
		render();
	}
	
	public static void atualizarCadastroCardapio(Restaurante restaurante) {
		restaurante.save();
		index();
	}
	
	public static void formPromocao() {
		Usuario usuario = Usuario.findById(Long.parseLong(session.get("usuario.id")));
		Restaurante restaurante = Restaurante.find("administrador = ?", usuario).first();
        render(restaurante);
	}
	
	public static void salvar(Promocao promocao) {
		Usuario usuario = Usuario.findById(Long.parseLong(session.get("usuario.id")));
		Restaurante restaurante = Restaurante.find("administrador = ?", usuario).first();
		promocao.restaurante = restaurante;
		promocao.save();
		index();
	}
	
	public static void cadastraCardapios() {
		Usuario usuario = Usuario.findById(Long.parseLong(session.get("usuario.id")));
		Restaurante restaurante = Restaurante.find("administrador = ?", usuario).first();
        render(restaurante);
	}
	
}
