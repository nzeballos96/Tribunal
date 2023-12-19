package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Conect;
import Modelo.EGenero;
import Modelo.EPartido;
import Modelo.ExcepcionCiudadanoMenordeEdad;
import Modelo.ExcepcionVotanteYaCargado;
import Modelo.Instancia;
import Modelo.Sufragante;
import Modelo.Candidato;

public class CCandidato {

	   private Scanner scanner;
	   static Conect cn = new Conect();
    static Instancia Instancia = new Instancia();
    private ArrayList<Candidato> candidato;
    
    public CCandidato() {
	        scanner = new Scanner(System.in);
	        cn.conexion();
	        
	    }

public static void cargagarcandidato(int duc, int partidoc, String lemac, int idelec) {
		
	 try {
	        cn.conexion();
	        
	        Candidato candidato = new Candidato();

	        // Consulta para comprobar si el votante ya existe
	        String validarsuf = "SELECT COUNT(du) FROM sufragante WHERE du = ?";
	        PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
	        statementrs.setInt(1, duc);
	        ResultSet rs = statementrs.executeQuery();

	        // Si el votante ya existe, lanzamos una excepción
	        if (rs.next() && rs.getInt(1) < 0) {
	            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + duc + " NO EXISTE COMO SUFRAGANTE, DEBE CARGARLO PRIMERO");
	        }

	        // Consulta para comprobar si el votante es candidato
	        String validarcan = "SELECT COUNT(du_sufragante) FROM candidato WHERE du_sufragante = ?";
	        PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
	        statementrc.setInt(1, duc);
	        ResultSet rc = statementrc.executeQuery();

	        // Si el votante es candidato, lanzamos una excepción
	        if (rc.next() && rc.getInt(1) > 0) {
	            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + duc + " YA FIGURA COMO CANDIDATO");
	        }
	

		    		
	        candidato.setDU(duc);
			candidato.setPartido(EPartido.values()[partidoc - 1].name());
			candidato.setLema(lemac);
			
			    		
			
			PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO candidato (LEMA, PARTIDO, ID_ELECCION, DU_SUFRAGANTE) VALUES (?, ?, ?, ?)");
			
			
			
			ps.setString(1, candidato.getLema());
			ps.setString(2, candidato.getPartido());
			ps.setInt(3, idelec);
			ps.setInt(4, candidato.getDU());
			
			ps.execute();

			System.out.println("CANDIDATO CARGADO");
		

	    
	} catch (Exception e) {
		System.out.println("NO SE PUDO CARGAR VOTANTE, SE REGRESA AL MENU" + e);
		Instancia.Menu();
		e.printStackTrace();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*try {
		cn.conexion();
		Candidato candidato = new Candidato();
		
		String validarsuf =  "select du from sufragante where du = ? ";
		PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
	    statementrs.setInt(1, duc);

	    ResultSet rs = statementrs.executeQuery();
	    
	    if (rs != null) {
	    	
	    	String validarcan =  "select du from candidato where du = ?";
			PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
		    statementrc.setInt(1, duc);

		    ResultSet rc = statementrc.executeQuery();
		    
		    if (rc != null) {
		    	
		    	if (edadc <= 16 ) {
		    		System.out.println("ERROR, NO SE PUEDE CARGAR UN VOTANTE MENOR");
		    	}
		    	else {
		    		
		int votorec = 0;    		
		    		
		candidato.setDU(duc);
		candidato.setNombre(nombrec);
		candidato.setApellido(apellidoc);
		candidato.setDomicilio(domicilioc);
		candidato.setGenero(EGenero.values()[generoc - 1].name());
		candidato.setPartido(EGenero.values()[generoc - 1].name());
		candidato.setLema(lemac);
		candidato.setEdad(edadc);
		
		candidato.setVotorec(votorec);
		    		
		
		PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO candidato "
				+ "(DU, APELLIDO, NOMBRE, GENERO, DOMICILIO, PARTIDO, LEMA, VOTOREC) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
		ps.setInt(1, candidato.getDU());
		ps.setString(3, candidato.getApellido());
		ps.setString(2, candidato.getNombre());		
		ps.setString(4, candidato.getGenero());
		ps.setString(5, candidato.getDomicilio());
		ps.setString(6, candidato.getPartido());
		ps.setString(7, candidato.getLema());
		ps.setInt(8, candidato.getVotorec());

		
		ps.execute();

		System.out.println("CANDIDATO CARGADO");
		cn.cerrar();
		
		    	}
		    	
		    }
		    else {
		    	
		    	throw new ExcepcionVotanteYaCargado("EL VOTANTE DU : " + duc + " YA FIGURA COMO CANDIDATO");
		    	
		    }
	    	
	    }else {
	    	
	    	throw new ExcepcionVotanteYaCargado("EL DU : " + duc + " YA SE ENCUENTA EN NUESTRO SISTEMA COMO VOTANTE");
	    	
	    }
	    
	} catch (Exception e) {
		System.out.println("NO SE PUDO CARGAR CANDIDATO, SE REGRESA AL MENU" + e);
		Instancia.Menu();
		e.printStackTrace();
	}*/
		
	
}
	
}
