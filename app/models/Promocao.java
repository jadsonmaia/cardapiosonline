package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import play.data.validation.Valid;
import play.db.jpa.Blob;
import play.db.jpa.Model;

@Entity
public class Promocao extends Model{
	
	@Valid
	public String titulo;
	@Valid
	public String descricao;
	@Valid
	public Date dataInicio;
	@Valid
	public Date dataFim;
	public Blob foto;
	public boolean encerrado;
	
	@ManyToOne
	@JoinColumn(name="restaurante_id")
	public Restaurante restaurante;
}
