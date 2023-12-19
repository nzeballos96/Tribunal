package Modelo;

public class Votacion {

	int id, id_candidato, id_sufragante;

	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId_candidato() {
		return id_candidato;
	}



	public void setId_candidato(int id_candidato) {
		this.id_candidato = id_candidato;
	}



	public int getId_sufragante() {
		return id_sufragante;
	}



	public void setId_sufragante(int id_sufragante) {
		this.id_sufragante = id_sufragante;
	}



	public Votacion(int id, int id_candidato, int id_sufragante) {
		super();
		this.id = id;
		this.id_candidato = id_candidato;
		this.id_sufragante = id_sufragante;
	}



	public Votacion() {
		super();
	}
	
}
