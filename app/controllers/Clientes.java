package controllers;


import java.io.File;
import java.util.Arrays;
import java.util.List;


import enums.TipoUsuario;
import models.Restaurante;
import models.Usuario;
import play.cache.Cache;
import play.data.validation.Valid;
import play.mvc.Controller;
import play.mvc.With;

@With(Seguranca.class)
public class Clientes extends Controller {
	
	public static void form() {
		Usuario Usuario = (Usuario)Cache.get("cli");
		Cache.clear();
		render(Usuario);
		List<TipoUsuario> tipoUsuario = Arrays.asList(TipoUsuario.values());
		render(tipoUsuario);
	}
	
	public static void mostrarImagemPerfil(Long id) {
		Usuario usuario = Usuario.findById(id);
		renderBinary(usuario.foto.get());
	}
	

	public static void salvar(@Valid Usuario Cliente, File foto) {
		
		
		if (validation.hasErrors()) {
			validation.keep();
			
			flash.error("falha ao cadastrar.");
			Cache.set("Usuario", Cliente);
			form();
		}


		
		Cliente.save();
		
		listar();

	}
	
	
	
	public static void listar() {
		String busca = params.get("busca");
		
		List<Usuario> lista;
		if(busca == null) {
			lista = Usuario.findAll();
		} else {
			lista = Usuario.find("nome like ?1 or email like ?1 ", "%"+busca+"%").fetch();
		}
		
		render(lista);
	}
	public static void editar(Long id) {
		List<TipoUsuario> tipoUsuario = Arrays.asList(TipoUsuario.values());
		Usuario cli = Usuario.findById(id);
		renderTemplate("Clientes/form.html", cli, tipoUsuario);
		
	}
	public static void deletar(Long id) {
		Usuario cli = Usuario.findById(id);
		cli.delete();
		flash.success("Cliente foi Removido.");
		listar();
	}
	
	

}
