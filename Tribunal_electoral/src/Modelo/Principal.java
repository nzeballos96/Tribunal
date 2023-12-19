package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Controlador.CEleccion;

public class Principal {

	public static void main(String[] args) throws SQLException{
		// TODO Auto-generated method stub

		
	//	Instancia Instancia = new Instancia();
	//	Instancia.Menu();
		
		CEleccion eleccion = new CEleccion();
		
	
	
	System.out.println("BIENVENIDO AL SISTEMA, SELECCIONE UNA OPCION");
	System.out.println("1- INGRESAR A UNA ELECCION");
	System.out.println("2- CARGAR UNA ELECCION");
	System.out.println("0- SALIR");
	int opcion = new Scanner(System.in).nextInt();

	switch (opcion) {
		case 1:

			System.out.println("<=========================================>");
			ArrayList<Eleccion> elecciones = eleccion.listaelecciones();
			System.out.println("ELECCIONES");
			eleccion.imprimirELECCIONES(elecciones);
			System.out.println("<=========================================>");
			
			
			System.out.println("BIENVENIDO AL SISTEMA, SELECCIONE UN ID ELECCION");
			
			
			final int id = new Scanner(System.in).nextInt();
			
			eleccion.buscarESTATUS(id);
			
			
			Eleccion celeccion = eleccion.buscar(id); 
			if (eleccion != null) {
				Instancia in = new Instancia();
				in.Menu(id);

			} else {
				System.out.println("ELECCION INEXISTENTE, CARGUE UNO");
				main(args);
			}
			break;

		case 2:

			System.out.println("INGRESE DIA");
			int dia = new Scanner(System.in).nextInt();
			System.out.println("INGRESE MES");
			int mes = new Scanner(System.in).nextInt();
			System.out.println("INGRESE AÑO");
			int anio = new Scanner(System.in).nextInt();
			System.out.println("INGRESE DETALLE");
			String detalle = new Scanner(System.in).nextLine();

			eleccion.cargareleccion(dia, mes, anio, detalle);
			main(args);

			break;
		case 0:
			System.out.println("Gracias por usar el menu");
			System.exit(0);
		default:
			System.out.println("Opcion invalida");
			break;
	}

}

}


