package Modelo;

import java.util.Scanner;

import Controlador.CCandidato;
import Controlador.CSufragante;

public class Instancia {

	
	CSufragante sufragante  = new CSufragante();
	CCandidato candidato = new CCandidato();
	public Instancia() {
		
	}
	
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
	            	
	            	 System.out.println("----BIENVNIDO A AGREGAR SUFRAGANTE---");
	                 System.out.println("INGRESE NOMBRE");
	                 String nombres = scanner.next();
	                 System.out.println("INGRESE APELLIDO");
	                 String apellidos = scanner.next();
	                 System.out.println("INGRESE DNI");
	                 int dus = scanner.nextInt();
	                 System.out.println("SELECCIONES UN GENERO");
	                 for (int i = 0; i < EGenero.values().length; i++) {
	                     System.out.println(i + 1 + " - " + EGenero.values()[i].name());
	                 }
	                 int generos = scanner.nextInt();
	                 System.out.println("INGRESE EDAD");
	                 int edads = scanner.nextInt();
	                 System.out.println("INGRESE DOMICILIO");
	                 String domicilios = scanner.next();

	                 sufragante.cargarvotante(dus, nombres, apellidos, domicilios, edads, generos);
	            	
	                 //cargarvotante(int du, String nombre, String apellido, String domicilio, int edad, int genero )
	                 
	                Menu();
	                break;
	                
	            case 2:

	            	System.out.println("----BIENVNIDO A AGREGAR CANDIDATO---");
	               // System.out.println("INGRESE NOMBRE");
	              //  String nombrec = scanner.next();

	             //   System.out.println("INGRESE APELLIDO");
	             //   String apellidoc = scanner.next();

	                System.out.println("INGRESE DNI");
	                int duc = scanner.nextInt();

	              //  System.out.println("SELECCIONES UN GENERO");
	             //   for (int i = 0; i < EGenero.values().length; i++) {
	              //      System.out.println(i + 1 + " - " + EGenero.values()[i].name());
	             //   }
	             //   int generoc = scanner.nextInt();

	             //   System.out.println("INGRESE EDAD");
	             //   int edadc = scanner.nextInt();

	            //    System.out.println("INGRESE DOMICILIO");
	            //    String domicilioc = scanner.next();

	                System.out.println("SELECCIONE UN PARTIDO:");
	                for (int i = 0; i < EPartido.values().length; i++) {
	                    System.out.println(i + 1 + " - " + EPartido.values()[i].name());
	                }
	                int partidoc = scanner.nextInt();

	                System.out.println("INGRESE LEMA");
	                String lemac = scanner.next();
	                
	                System.out.println("ID ELECCION");
	                int idelec = scanner.nextInt();
	            	
	                candidato.cargagarcandidato(duc, 
	                		//edadc, apellidoc, nombrec, domicilioc, generoc, 
	                		partidoc, lemac, idelec); 

	                Menu();
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
