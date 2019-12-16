package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Restaurante extends Model {
	@Required
	public String nome;
	public String endereco;
	public String numero;
	@Required
	public String cardapios;
	@Required
	public Blob foto;
	
	@ManyToMany
	@JoinTable(name="restaurante_cliente")
	public List<Usuario> clientes;
	
	@OneToOne
	public Usuario administrador;
	
	@OneToMany(mappedBy="restaurante")
	public List<Promocao> promocoes;
	
	public Restaurante() {
		this.clientes = new ArrayList<Usuario>();
		this.promocoes = new ArrayList<>();
	}
	
	public boolean temCliente(String idCliente) {
		for(Usuario cliente: clientes) {
			if(cliente.getId().toString().equals(idCliente)) {
				return true;
			}
		}
		return false;
	}
}