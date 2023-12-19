package Modelo;

public class Candidato extends Ciudadano {

	String Lema, Partido;
	int Votorec;
	public String  getPartido() {
		return Partido;
	}
	public void setPartido(String partido) {
		Partido = partido;
	}
	public String getLema() {
		return Lema;
	}
	public void setLema(String lema) {
		Lema = lema;
	}
	public int getVotorec() {
		return Votorec;
	}
	public void setVotorec(int votorec) {
		Votorec = votorec;
	}
	public Candidato(int dU, int edad, String apellido, String nombre, String domicilio, String genero,
			String  partido, String lema, int votorec) {
		super(dU, edad, apellido, nombre, domicilio, genero);
		Partido = partido;
		Lema = lema;
		Votorec = votorec;
	}
	public Candidato() {
		super();
	}
	
	
}
