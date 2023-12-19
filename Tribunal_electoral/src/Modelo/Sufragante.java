package Modelo;


public class Sufragante extends Ciudadano{

	int checkin;



	public int getCheckin() {
		return checkin;
	}



	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}



	public Sufragante(int dU, int edad, String apellido, String nombre, String domicilio, String genero, int checkin) {
		super(dU, edad, apellido, nombre, domicilio, genero);
		this.checkin = checkin;
	}

	public Sufragante() {
	//	super();
	}	
}

