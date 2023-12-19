package Modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Controlador.CCandidato;
import Controlador.CEleccion;
import Controlador.CSufragante;
import Controlador.CVotacion;

public class Instancia {

	
	CSufragante sufragante  = new CSufragante();
	CCandidato candidato = new CCandidato();
	CVotacion votacion = new CVotacion();
	CEleccion eleccion = new CEleccion();
	public Instancia() {
		
	}
	
	public void Menu(int id) throws SQLException {
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
	        System.out.println("8. --< Acerca de >--");
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
	                 
	                Menu(id);
	                break;
	                
	            case 2:

	            	System.out.println("----BIENVNIDO A AGREGAR CANDIDATO---");
	               System.out.println("INGRESE NOMBRE");
	               String nombrec = scanner.next();

	                System.out.println("INGRESE APELLIDO");
	              String apellidoc = scanner.next();

	                System.out.println("INGRESE DNI");
	                int duc = scanner.nextInt();

	                System.out.println("SELECCIONES UN GENERO");
	                for (int i = 0; i < EGenero.values().length; i++) {
	                    System.out.println(i + 1 + " - " + EGenero.values()[i].name());
	               }
	                int generoc = scanner.nextInt();

	                System.out.println("INGRESE EDAD");
	                int edadc = scanner.nextInt();

	                System.out.println("INGRESE DOMICILIO");
	                String domicilioc = scanner.next();

	                System.out.println("SELECCIONE UN PARTIDO:");
	                for (int i = 0; i < EPartido.values().length; i++) {
	                    System.out.println(i + 1 + " - " + EPartido.values()[i].name());
	                }
	                int partidoc = scanner.nextInt();

	                System.out.println("INGRESE LEMA");
	                String lemac = scanner.next();
	              	            	
	                candidato.cargarcandidato(duc, partidoc, lemac, nombrec, apellidoc, generoc, edadc,domicilioc); 

	                Menu(id);
	                break;
	            case 3:


	            	System.out.println("----BIENVENIDO A VOTAR---");
	            	System.out.println("INGRESE SU DU");
               int dusv = scanner.nextInt();
	            sufragante.ValidarSufragante(dusv);
	               
	            System.out.println("INGRESE UNA OPCION");
	            System.out.println("1 --- VOTAR CANDIDATO");
	            System.out.println("2 --- IMPUGNAR VOTO");
	            System.out.println("3 --- VOTO EN BLANDO");
	            
	            int op = scanner.nextInt();
	            
	            switch(op) {
	            case 1:

	            	
		               System.out.println("<=========================================>");
						ArrayList<Candidato> padronemitido1 = candidato.padroncandidatos();
						System.out.println("PADRON DE VOTANTES CON VOTOS EMITIDOS:");
						candidato.imprimirPadronCandidatos(padronemitido1);
						System.out.println("<=========================================>");
		                              
		               System.out.println("INGRESE DU DE CANDIDATO A VOTAR");
		               int ducs = scanner.nextInt();
		               	          		               
		               votacion.Votar( ducs, dusv);
		               sufragante.ActualizarSufragante(dusv);
		               votacion.sumarvoto(ducs);
		               Menu(id);	            	
	            	break;
	            case 2:
	            	
	            	votacion.impugnarvoto();
	            	sufragante.ActualizarSufragante(dusv);
	            	Menu(id);
	            	break;
	            case 3:	
	            	
	            	votacion.votoblando();
	            	sufragante.ActualizarSufragante(dusv);
	            	Menu(id);
	            	break;
	            	
	             default:
	                System.out.println("VOTO PUESTO COMO BLANCO");
	            	votacion.votoblando();
	            	sufragante.ActualizarSufragante(dusv);	                
	            	Menu(id);
	                break;	
	            }
	            


	                break;
	            case 4:

	      
	            	System.out.println("<=========================================>");
					ArrayList<Sufragante> padronemitido = sufragante.padronvotantesok();
					System.out.println("PADRON DE VOTANTES");
					sufragante.imprimirPadron(padronemitido);
					System.out.println("<=========================================>");
	            	
					Menu(id);
	                break;
	            case 5:
 	
	            	System.out.println("<=========================================>");
					ArrayList<Candidato> padronemitido1 = candidato.padroncandidatos();
					System.out.println("PADRON CANDIDATOS:");
					candidato.imprimirPadronCandidatos(padronemitido1);
					System.out.println("<=========================================>");

					Menu(id);
	                break;
	            case 6:

	            	System.out.println("<=================== VOTOS POR CANDIDATO =======================>");
					ArrayList<Candidato> padronvotos = candidato.votosporcandidato();
					candidato.votosporcandidatos(padronvotos);
					System.out.println("<================================================================>");

					
					System.out.println("<====================== VOTOS POR PARTIDO========================>");
					ArrayList<Candidato> padroVPARTIDOS = candidato.votosporpartido();
					candidato.votosporpartido(padroVPARTIDOS);
					System.out.println("<================================================================>");

					Menu(id);

	                break;

	            case 7:

	            	eleccion.escrutiniofinal(id);
	            	
	            	System.out.println("<=================== VOTOS POR CANDIDATO =======================>");
					ArrayList<Candidato> pavoto = candidato.votosporcandidatofinal(id);
					candidato.votosporcandidatos(pavoto);
					System.out.println("<================================================================>");

					
					System.out.println("<====================== VOTOS POR PARTIDO========================>");
					ArrayList<Candidato> padron = candidato.votosporpartidofinal(id);
					candidato.votosporpartido(padron);
					System.out.println("<================================================================>");

					Menu(id);
	            	
	                break;

	            case 8:

	                JOptionPane.showConfirmDialog(null, "ZEBALLOS NICOLAS, 19/12/2023");
	            	Menu(id);
	            	

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
