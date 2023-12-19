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

	   static Conect cn = new Conect();
       static Instancia Instancia = new Instancia();
       private ArrayList<Sufragante> sufragantes;
       
       public CSufragante() {
	        cn.conexion();
	        sufragantes = new ArrayList<>();
	    }

       public static void cargarvotante(int dus, String nombres, String apellidos, String domicilios, int edads, int generos ) throws SQLException {
    		  		
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
    		        String validarcan = "SELECT COUNT(du) FROM candidato WHERE du = ?";
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
    		
    			    		
    		     int checkin = 0;   
    		        
    		        
    			sufragante.setDU(dus);
    			sufragante.setNombre(nombres);
    			sufragante.setApellido(apellidos);
    			sufragante.setDomicilio(domicilios);
    			sufragante.setGenero(EGenero.values()[generos - 1].name());
    			sufragante.setEdad(edads);
    			sufragante.setCheckin(checkin);
    			    		
    			
    			PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO sufragante "
    					+ "(DU, NOMBRE, APELLIDO, EDAD, GENERO, DOMICILIO, checkin)"
    					+ " VALUES ( ? , ?, ?, ?, ?,?)");
    			
    			ps.setInt(1, sufragante.getDU());
    			ps.setString(2, sufragante.getNombre());
    			ps.setString(3, sufragante.getApellido());
    			ps.setInt(4, sufragante.getEdad());
    			ps.setString(5, sufragante.getGenero());
    			ps.setString(6, sufragante.getDomicilio());
    			ps.setInt(7, sufragante.getCheckin());

    			
    			ps.execute();

    			System.out.println("SUFRAGANTE CARGADO");
    			cn.cerrar();
 
    		    
    		} catch (Exception e) {
    			System.out.println("NO SE PUDO CARGAR VOTANTE, SE REGRESA AL MENU" + e);
    			Instancia.Menu();
    			e.printStackTrace();
    		}
    			
    		
    	}

public ArrayList<Sufragante> padronvotantesok() throws SQLException {
    Conect cn = new Conect();
    cn.conexion();

    String cargap = "SELECT du, nombre, apellido, edad, genero, domicilio, checkin from sufragante ";

    PreparedStatement statement = cn.conexion().prepareStatement(cargap);
    

    ResultSet rs = statement.executeQuery();

    while (rs.next()) {
        Sufragante sufragante = new Sufragante();
        sufragante.setDU(rs.getInt("DU"));
        sufragante.setNombre(rs.getString("NOMBRE"));
        sufragante.setApellido(rs.getString("APELLIDO"));
        sufragante.setEdad(rs.getInt("EDAD"));
        sufragante.setGenero(rs.getString("GENERO"));
        sufragante.setDomicilio(rs.getString("DOMICILIO"));
        sufragante.setCheckin(rs.getInt("CHECKIN"));

        sufragantes.add(sufragante);
    }

    return sufragantes;
}

public void imprimirPadron(ArrayList<Sufragante> sufragantes) {
//	System.out.println("|       DU | NOMBRE | APELLIDO | EDAD |  GENERO  |    DOMICILIO    |");
//	System.out.println("|----------|--------|----------|------|----------|-----------------|");
for (Sufragante sufragante : sufragantes) {
	System.out.printf("DU " + sufragante.getDU() + " -- NOMBRE: " +  sufragante.getNombre() + " -- APELLIDO: " + 
    sufragante.getApellido() + "EDAD: " +
	sufragante.getEdad() + " -- GENERO: " + sufragante.getGenero() + " -- DOMICILIO " + sufragante.getDomicilio()
	+ " -- VOTO REALIZADO " + sufragante.getCheckin() + "%n");
}
}

public void ValidarSufragante(int dusv) {
	try {
		
		// Consulta para comprobar si el votante ya existe
        String validarsuf = "SELECT COUNT(du) FROM sufragante WHERE du = ?";
        PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
        statementrs.setInt(1, dusv);
        ResultSet rs = statementrs.executeQuery();

        // Si el votante ya existe, lanzamos una excepción
        if (rs.next() && rs.getInt(1) > 0) {
        	System.out.println(" SUFRAGANTE ENCONTRADO");
        	
        	Sufragante sufragante = new Sufragante();
        	sufragante.setDU(dusv);
        	
			String query = "UPDATE sufragante SET checkin =  1 WHERE du = ?";
			PreparedStatement statement = cn.conexion().prepareStatement(query);
	        statement.setInt(1, dusv);
	        ResultSet rs1 = statement.executeQuery();
        
	        
		}else {
        	System.out.println("EL SUFRAGANTE NO EXISTE");
        	
        	Instancia.Menu();
        }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
}
	
}
