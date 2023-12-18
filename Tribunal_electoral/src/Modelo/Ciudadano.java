package Modelo;

public class Ciudadano {

	int DU, Edad;
	String Apellido, Nombre, Domicilio;
	EGenero Genero;
	public int getDU() {
		return DU;
	}
	public void setDU(int dU) {
		DU = dU;
	}
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int edad) {
		Edad = edad;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDomicilio() {
		return Domicilio;
	}
	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}
	public EGenero getGenero() {
		return Genero;
	}
	public void setGenero(EGenero genero) {
		Genero = genero;
	}
	public Ciudadano(int dU, int edad, String apellido, String nombre, String domicilio, EGenero genero) {
		super();
		DU = dU;
		Edad = edad;
		Apellido = apellido;
		Nombre = nombre;
		Domicilio = domicilio;
		Genero = genero;
	}
	public Ciudadano() {
		super();
	}
	
	
}
