package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Modelo.Conect;
import Modelo.EGenero;
import Modelo.Sufragante;

public class CSufragante {

	   private Scanner scanner;
	   static Conect cn = new Conect();
       
	    public CSufragante() {
	        scanner = new Scanner(System.in);
	        cn.conexion();
	    }

public static void cargarvotante(int du, String nombre, String apellido, String domicilio, int edad, EGenero genero ) {
	
	try {
		cn.conexion();
		String validarsuf =  "select du from sufrangante where du = ?";
		PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
	    statementrs.setInt(1, du);

	    ResultSet rs = statementrs.executeQuery();
	    
	    if (rs== null) {
	    	
	    	String validarcan =  "select du from candidato where du = ?";
			PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
		    statementrc.setInt(1, du);

		    ResultSet rc = statementrc.executeQuery();
		    
		    if (rc == null) {
		    	
		    }
		    else {
		    	
		    }
	    	
	    }else {
	    	
	    }
	    
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	
}
	
	
}
