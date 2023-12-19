package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Modelo.Conect;
import Modelo.EGenero;
import Modelo.ExcepcionCiudadanoMenordeEdad;
import Modelo.ExcepcionVotanteNoEncontrado;
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
	
	
	public void Votar(int ducv, int dusv ) throws SQLException {	
		
		try {
			cn.conexion();
			Votacion votacion = new Votacion();

			// Consulta para comprobar si el votante existe, si es candidato y si ya ha emitido su voto
			String validarvot = "SELECT COUNT(du) FROM sufragante WHERE du = ? AND CHECKIN = 1 ";
			PreparedStatement statementv = cn.conexion().prepareStatement(validarvot);
			statementv.setInt(1, dusv);
			ResultSet rsv = statementv.executeQuery();

			// Si el votante no existe, lanzamos una excepción
			if (!rsv.next() || rsv.getInt(1) < 0) {
				throw new ExcepcionVotanteReincidente("EL CIUDADANO: " + dusv + " YA EMITIO SU VOTO");
			}

			votacion.setId_candidato(ducv);

			PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO votacion "
					+ "(IDVOTO, DU_CANDIDATO) "
					+ "VALUES (null, ?)");
			//ps.setInt(1, votacion.getId());
			ps.setInt(1, votacion.getId_candidato());

			ps.execute();
			

			System.out.println("VOTO CARGADO");

			
			
			cn.cerrar();
		} catch (Exception e) {
			System.out.println("NO SE PUDO CARGAR VOTO, SE REGRESA AL MENU" + e);
	//		Instancia.Menu();
			e.printStackTrace();
		}

	}
	
	public void impugnarvoto() throws SQLException {

		int du = 99;
		String query = "UPDATE candidato SET votorec = votorec + 1 WHERE du = 99";
		PreparedStatement ps = cn.conexion().prepareStatement(query);

		ps.execute();
	
		votofueradeseleccion(du);
	}
	
public void votoblando() throws SQLException {
	int du = 0;	
	
	String query = "UPDATE candidato SET votorec = votorec + 1 WHERE du = 0";
	PreparedStatement ps = cn.conexion().prepareStatement(query);

	ps.execute();
	
	votofueradeseleccion(du); 
	
	}
	
public void votofueradeseleccion(int du) throws SQLException {

	try {
	cn.conexion();
	Votacion votacion = new Votacion();
	
	votacion.setId_candidato(du);

	PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO votacion "
			+ "(IDVOTO, DU_CANDIDATO) "
			+ "VALUES (null, ?)");
	ps.setInt(1, votacion.getId_candidato());

	ps.execute();
} catch (Exception e) {
	System.out.println("NO SE PUDO CARGAR VOTO, SE REGRESA AL MENU" + e);
	//Instancia.Menu();
	e.printStackTrace();
}
	
}


public void sumarvoto(int ducv) throws SQLException {

	cn.conexion();
	
	Votacion votacion = new Votacion();
	
	votacion.setId_candidato(ducv);
	
	String query = "UPDATE candidato SET votorec = votorec + 1 WHERE du = ?";
	PreparedStatement ps = cn.conexion().prepareStatement(query);
	ps.setInt(1, votacion.getId_candidato());
	
	ps.execute();

}

	}
	
