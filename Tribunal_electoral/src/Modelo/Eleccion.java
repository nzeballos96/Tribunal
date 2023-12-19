package Modelo;


public class Eleccion {
	
	int id, dia, mes,anio, Status;
	String detalle;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDia() {
		return dia;
	}


	public void setDia(int dia) {
		this.dia = dia;
	}


	public int getMes() {
		return mes;
	}


	public void setMes(int mes) {
		this.mes = mes;
	}


	public int getAnio() {
		return anio;
	}


	public void setAnio(int anio) {
		this.anio = anio;
	}


	public int getStatus() {
		return Status;
	}


	public void setStatus(int status) {
		Status = status;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public Eleccion(int id, int dia, int mes, int anio, int status, String detalle) {
		super();
		this.id = id;
		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		Status = status;
		this.detalle = detalle;
	}


	public Eleccion() {
		super();
	}
	
	
}


