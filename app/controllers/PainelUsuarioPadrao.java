package controllers;

import java.util.List;

import models.Usuario;
import models.Restaurante;
import play.mvc.Controller;

public class PainelUsuarioPadrao extends Controller{
		
		public static void index() {
			List<Restaurante> restaurantes = Restaurante.findAll();
	        render(restaurantes);
		}
		public static void sobre() {
			render();
		}
		public static void menu(Long idRestaurante) {
			Restaurante restaurante = Restaurante.findById(idRestaurante);
			render(restaurante);
		}
		
		public static void logout() {
			session.clear();
			PainelUsuarioPadrao.index();
		}
		
		public static void salvar(Usuario Cliente) {
			Cliente.save();
			flash.success("salvo com sucesso.");
			PainelUsuarioPadrao.index();
		}
		
		public static void salvarClientePadrao(Usuario Cliente) {
			Cliente.save();
			index();
		}
	
}
