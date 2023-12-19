package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Modelo.Conect;
import Modelo.EGenero;
import Modelo.Elecciones;
import Modelo.ExcepcionCiudadanoMenordeEdad;
import Modelo.Instancia;
import Modelo.ExcepcionVotanteYaCargado;
import Modelo.Sufragante;

public class CSufragante {

	   private Scanner scanner;
	   static Conect cn = new Conect();
       static Instancia Instancia = new Instancia();
       private ArrayList<Sufragante> sufragante;
       
       public CSufragante() {
	        scanner = new Scanner(System.in);
	        cn.conexion();
	        
	    }

public static void cargarvotante(int dus, String nombres, String apellidos, String domicilios, int edads, int generos ) {
	
	/*try {
		cn.conexion();
		Sufragante sufragante = new Sufragante();
		
		String validarsuf =  "select count(du) from sufragante where du = ? ";
		PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
	    statementrs.setInt(1, dus);

	    ResultSet rs = statementrs.executeQuery();
	    int ver = 1;
	    if (rs.equals(ver)) {
	    	
	    	String validarcan =  "select count(du_sufragante)  from candidato where du_sufragante = ?";
			PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
		    statementrc.setInt(1, dus);

		    ResultSet rc = statementrc.executeQuery();
		      
		    if (rc.equals(ver)) {
		    	
		    	if (edads <= 16 ) {
		    		System.out.println("ERROR, NO SE PUEDE CARGAR UN VOTANTE MENOR");
		    	}
		    	else {
		    		
		//int checkin = 0;    */
	
	
	 try {
	        cn.conexion();
	        Sufragante sufragante = new Sufragante();

	        // Consulta para comprobar si el votante ya existe
	        String validarsuf = "SELECT COUNT(du) FROM sufragante WHERE du = ?";
	        PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
	        statementrs.setInt(1, dus);
	        ResultSet rs = statementrs.executeQuery();

	        // Si el votante ya existe, lanzamos una excepción
	        if (rs.next() && rs.getInt(1) > 0) {
	            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + dus + " YA EXISTE COMO SUFRAGANTE");
	        }

	        // Consulta para comprobar si el votante es candidato
	        String validarcan = "SELECT COUNT(du_sufragante) FROM candidato WHERE du_sufragante = ?";
	        PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
	        statementrc.setInt(1, dus);
	        ResultSet rc = statementrc.executeQuery();

	        // Si el votante es candidato, lanzamos una excepción
	        if (rc.next() && rc.getInt(1) > 0) {
	            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + dus + " FIGURA COMO CANDIDATO");
	        }
	
	        if (edads <= 16) {
	            throw new ExcepcionCiudadanoMenordeEdad("EL CIUDADANO: " + dus + " ES MENOR DE EDAD");
	        }
	
		    		
		sufragante.setDU(dus);
		sufragante.setNombre(nombres);
		sufragante.setApellido(apellidos);
		sufragante.setDomicilio(domicilios);
		sufragante.setGenero(EGenero.values()[generos - 1].name());
		sufragante.setEdad(edads);
		//sufragante.setCheckin(checkin);
		    		
		
		/*PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO sufragante "
				+ "(DU, APELLIDO, NOMBRE, GENERO, DOMICILIO, CHECKIN)"
				+ " VALUES (? , ? , ?, ?, ?, ?)");*/
		
		PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO sufragante "
				+ "(DU, NOMBRE, APELLIDO, EDAD, GENERO, DOMICILIO)"
				+ " VALUES ( ? , ?, ?, ?, ?,?)");
		
		ps.setInt(1, sufragante.getDU());
		ps.setString(2, sufragante.getNombre());
		ps.setString(3, sufragante.getApellido());
		ps.setInt(4, sufragante.getEdad());
		ps.setString(5, sufragante.getGenero());
		ps.setString(6, sufragante.getDomicilio());
		//ps.setInt(6, sufragante.getCheckin());

		
		ps.execute();

		System.out.println("SUFRAGANTE CARGADO");
		cn.cerrar();
		
/*		    	}
		    	
		    }
		    else {
		    	
		    	throw new ExcepcionVotanteYaCargado("EL VOTANTE DU : " + dus + " FIGURA COMO CANDIDATO");
		    	
		    }
	    	
	    }else {
	    	
	    	throw new ExcepcionVotanteYaCargado("EL VOTANTE DU : " + dus + " YA SE ENCUENTA EN NUESTRO SISTEMA");
	    	
	    }*/
	    
	} catch (Exception e) {
		System.out.println("NO SE PUDO CARGAR VOTANTE, SE REGRESA AL MENU" + e);
		Instancia.Menu();
		e.printStackTrace();
	}
		
	
}
	
	
}
