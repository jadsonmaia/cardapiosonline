package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import enums.TipoUsuario;
import play.data.validation.Email;
import play.data.validation.Max;
import play.data.validation.Min;
import play.data.validation.Required;
import play.db.jpa.Blob;
import play.db.jpa.Model;
@Entity
public class Usuario extends Model{
	@Required
	public String nome;
	@Required
	@Email
	public String email;
	@Required
	public String endereco;
	public String bairro;
	@Required
	public String numero;
	public String senha;
	public Blob foto;
	public String nomeFoto;
	
	public TipoUsuario tipoUsuario;	
	
	public Usuario() {
		tipoUsuario = TipoUsuario.PADRAO;
	}
	
	@ManyToMany(mappedBy="clientes")
	public List<Restaurante> restaurantes;
}