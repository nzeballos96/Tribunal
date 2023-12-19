package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Conect;
import Modelo.EGenero;
import Modelo.ExcepcionCiudadanoMenordeEdad;
import Modelo.ExcepcionVotanteReincidente;
import Modelo.ExcepcionVotanteYaCargado;
import Modelo.Instancia;
import Modelo.Sufragante;
import Modelo.Votacion;

public class CVotacion {

	   static Conect cn = new Conect();
       static Instancia Instancia = new Instancia();
       private ArrayList<Votacion> votaciones;
	
	public CVotacion() {
		
		votaciones = new ArrayList<>();
		
	}
	
	
	public void Votar(int dusv, int ducv ) throws SQLException {
		 try {
		        cn.conexion();
		        Votacion votacion = new Votacion();

		        
		        
		        
		        
		        
		        // Consulta para comprobar si el votante ya existe
		        String validarsuf = "SELECT COUNT(du) FROM sufragante WHERE du = ?";
		        PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
		        statementrs.setInt(1, dusv);
		        ResultSet rs = statementrs.executeQuery();

		        // Si el votante ya existe, lanzamos una excepción
		        if (rs.next() && rs.getInt(1) < 0) {
		            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + dusv + " NO EXISTA COMO SUFRAGANTE");
		        }

		        // Consulta para comprobar si el votante es candidato
		        String validarcan = "SELECT COUNT(du) FROM candidato WHERE du = ?";
		        PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
		        statementrc.setInt(1, dusv);
		        ResultSet rc = statementrc.executeQuery();

		        // Si el votante es candidato, lanzamos una excepción
		        if (rc.next() && rc.getInt(1) > 0) {
		            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + dusv + " FIGURA COMO CANDIDATO");
		        }
		

		        // Consulta para comprobar si el votante ya existe
		        String validarvot = "SELECT COUNT(du) FROM sufragante WHERE du = ? AND CHECKIN = 1";
		        PreparedStatement statementv = cn.conexion().prepareStatement(validarvot);
		        statementrs.setInt(1, dusv);
		        ResultSet rsv = statementrs.executeQuery();

		        // Si el votante ya existe, lanzamos una excepción
		        if (rs.next() && rs.getInt(1) > 0) {
		            throw new ExcepcionVotanteReincidente("EL CIUDADANO: " + dusv + " YA EMITIO SU VOTO");
		        }
		        
			    		
		     int id = 0;   
		        
		    votacion.setId(id);    
			votacion.setId_sufragante(dusv);
			votacion.setId_candidato(ducv);
			    		
			
			PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO votacion "
					+ "(IDVOTO, DU_CANDIDATO) "
					+ "VALUES (?, ?)");
			
			ps.setInt(1, votacion.getId());
			ps.setInt(2, votacion.getId_candidato());

			
			ps.execute();
			


			System.out.println("VOTO CARGADO");

						
			cn.cerrar();

		    
		} catch (Exception e) {
			System.out.println("NO SE PUDO CARGAR VOTO, SE REGRESA AL MENU" + e);
			Instancia.Menu();
			e.printStackTrace();
		}
			
		
	}
	
	
	
	}
	
