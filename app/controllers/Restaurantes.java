package controllers;

import java.util.List;

import enums.TipoUsuario;
import models.Usuario;
import models.Restaurante;
import play.data.validation.Valid;
import play.mvc.Controller;

public class Restaurantes extends Controller {

	public static void form() {
		render();
	}
	
	public static void mostrarImagem(Long idRestaurante) {
		Restaurante restaurante = Restaurante.findById(idRestaurante);
		renderBinary(restaurante.foto.get());
		
	}

	public static void salvar(@Valid Restaurante res, Usuario usuario, Long idCliente) {
		
		if (idCliente != null) {
			Usuario c = Usuario.findById(idCliente);
			if (res.clientes.contains(c) == false)
				res.clientes.add(c);
		}
		usuario.tipoUsuario = TipoUsuario.ADMIN_RESTAURANTE;
		usuario.save();
		res.administrador = usuario;
		res.save();
		editar(res.id);
	}
	
	public static void favoritar(Long idRestaurante, Long idUsuario) {
		Restaurante restaurante = Restaurante.findById(idRestaurante);
		Usuario cliente = Usuario.findById(idUsuario);
		restaurante.clientes.add(cliente);
		restaurante.save();
		PainelUsuarioPadrao.index();
	}

	public static void listar(String busca) {

		List<Restaurante> lista;
		if (busca == null) {
			lista = Restaurante.findAll();
		} else {
			lista = Restaurante.find("nome like ?1 ", "%" + busca + "%").fetch();
		}
		render(lista);
	}

	public static void editar(long id) {
		Restaurante res = Restaurante.findById(id);
		renderTemplate("Restaurantes/form.html", res);
	}

	public static void deletar(long id) {
		Restaurante res = Restaurante.findById(id);
		res.delete();

		listar(null);
	}

	public static void removerCliente(Long idRes, Long idCli) {
		Restaurante res = Restaurante.findById(idRes);
		Usuario cli = Usuario.findById(idCli);

		res.clientes.remove(cli);
		res.save();
		editar(res.id);
	}
}
