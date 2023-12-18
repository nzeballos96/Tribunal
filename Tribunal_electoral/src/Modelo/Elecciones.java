package Modelo;

import java.util.Scanner;

public class Elecciones {

		
	public void Menu() {
		 Scanner scanner = new Scanner(System.in);

	        // DatosVentas comven = new ControlVoto();
	        System.out.println("Bienvenido al menú");
	        System.out.println("1. --> ALTA VOTANTE");
	        System.out.println("2. --> ALTA CANDIDATO");
	        System.out.println("3. --> VOTAR");
	        System.out.println("4. --> PADRON");
	        System.out.println("5. --> CANDIDATOS");
	        System.out.println("6. --> ESCRUTINIO PROVISORIO");
	        System.out.println("7. --> ESCRUTINIO DEFINITIVO");
	        System.out.println("8. --> CARGAR NUEVO PARTIDO");
	        System.out.println("9. --< Acerca de >--");
	        System.out.println("0. --> Salir <--");

	        int opcion = scanner.nextInt();

	        switch (opcion) {
	            case 1:

	                Menu();
	                break;
	            case 2:


	                break;
	            case 3:


	                break;
	            case 4:


	                break;
	            case 5:


	                break;
	            case 6:
	

	                break;

	            case 7:

	                break;

	            case 8:

	                Menu();

	                break;
	            case 9:

	                Menu();

	                break;
	            case 0:
	                System.out.println("Gracias por usar el menú");
	                System.exit(0);
	            default:
	                System.out.println("Opción inválida");
	                break;
	        }
	    }
		
	}


