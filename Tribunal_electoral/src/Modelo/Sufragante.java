package Modelo;


public class Sufragante extends Ciudadano{

	Boolean checkin;

	public Boolean getCheckin() {
		return checkin;
	}

	public void setCheckin(Boolean checkin) {
		this.checkin = checkin;
	}

	public Sufragante(int dU, int edad, String apellido, String nombre, String domicilio, EGenero genero,
			Boolean checkin) {
		super(dU, edad, apellido, nombre, domicilio, genero);
		this.checkin = checkin;
	}

	public Sufragante(int dU, int edad, String apellido, String nombre, String domicilio, EGenero genero) {
		super(dU, edad, apellido, nombre, domicilio, genero);
	}
	
	
}
