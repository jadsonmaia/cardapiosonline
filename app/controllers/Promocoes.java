package controllers;

import java.util.List;

import enums.TipoUsuario;
import models.Promocao;
import models.Restaurante;
import models.Usuario;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Promocoes extends Controller{
	
	public static void form() {
		render();
	}
	public static void salvarFotoPromocao(Promocao foto, Long PromocaoID) {
		Promocao promocao = Promocao.findById(PromocaoID);
		foto.save();
		
	}
	public static void listar() {
		List<Promocao> promocoes = Promocao.findAll();
		render(promocoes);
	}
	public static void salvar(Promocao promocoes) {
		promocoes.save();
		listar();
	}
	public static void detalhes(Long id) {
		Promocao promocao = Promocao.findById(id);
		render(promocao);
	}
	public static void editar(Long id) {
		Promocao promocao = Promocao.findById(id);
		renderTemplate("promocoes/form.html", promocao);
	}
	public static void remver(Long id) {
		Promocao promocao = Promocao.findById(id);
		promocao.delete();
		listar();
	}
	
}