package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.Conect;
import Modelo.EGenero;
import Modelo.EPartido;
import Modelo.Eleccion;
import Modelo.ExcepcionVotanteYaCargado;
import Modelo.Instancia;
import Modelo.Sufragante;
import Modelo.Votacion;
import Modelo.Candidato;

public class CCandidato {


	   static Conect cn = new Conect();
    static Instancia Instancia = new Instancia();
    private ArrayList<Candidato> candidatos;
    
    public CCandidato() {

	        cn.conexion();
	        Candidato candidato = new Candidato();
	        candidatos = new ArrayList<>();
	    }

public static void cargarcandidato(int duc, int partidoc, String lemac, String nombrec, String apellidoc, int generoc, int edadc, String domicilioc ) throws SQLException {
		
	 try {
	        cn.conexion();
	        
	        Candidato candidato = new Candidato();

	        // Consulta para comprobar si el votante ya existe
	        String validarsuf = "SELECT COUNT(du) FROM sufragante WHERE du = ?";
	        PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
	        statementrs.setInt(1, duc);
	        ResultSet rs = statementrs.executeQuery();

	        // Si el votante ya existe, lanzamos una excepción
	        if (rs.next() && rs.getInt(1) > 0) {
	            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + duc + " EXISTE COMO SUFRAGANTE");
	        }

	        // Consulta para comprobar si el votante es candidato
	        String validarcan = "SELECT COUNT(du) FROM candidato WHERE du = ?";
	        PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
	        statementrc.setInt(1, duc);
	        ResultSet rc = statementrc.executeQuery();

	        // Si el votante es candidato, lanzamos una excepción
	        if (rc.next() && rc.getInt(1) > 0) {
	            throw new ExcepcionVotanteYaCargado("EL CIUDADANO: " + duc + " YA FIGURA COMO CANDIDATO");
	        }
		      
	        
			int votorec = 0;    		
    		
			candidato.setDU(duc);
			candidato.setNombre(nombrec);
			candidato.setApellido(apellidoc);
			candidato.setDomicilio(domicilioc);
			candidato.setGenero(EGenero.values()[generoc - 1].name());
			candidato.setPartido(EPartido.values()[partidoc - 1].name());
			candidato.setLema(lemac);
			candidato.setEdad(edadc);
			candidato.setVotorec(votorec);
			
			candidato.setVotorec(votorec);
			    		
			
			PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO candidato "
					+ "(DU, APELLIDO, NOMBRE, edad, GENERO, DOMICILIO, PARTIDO, LEMA, VOTOREC) VALUES (?, ?, ?, ?, ?, ?, ?, ?,9)");
			
			ps.setInt(1, candidato.getDU());
			ps.setString(3, candidato.getApellido());
			ps.setString(2, candidato.getNombre());
			ps.setInt(4, candidato.getEdad());
			ps.setString(5, candidato.getGenero());
			ps.setString(6, candidato.getDomicilio());
			ps.setString(7, candidato.getPartido());
			ps.setString(8, candidato.getLema());
			ps.setInt(9, candidato.getVotorec());

			ps.execute();

			System.out.println("CANDIDATO CARGADO");
			cn.cerrar();
	    
	} catch (Exception e) {
		System.out.println("NO SE PUDO CARGAR VOTANTE, SE REGRESA AL MENU" + e);
	//	Instancia.Menu();
		e.printStackTrace();
	}
	
	
}

public ArrayList<Candidato> padroncandidatos() throws SQLException {
    cn.conexion();

    String cargap = "SELECT du, nombre, apellido, edad, genero, domicilio, partido, lema from candidato ";

    PreparedStatement statement = cn.conexion().prepareStatement(cargap);
    

    ResultSet rs = statement.executeQuery();

    while (rs.next()) {
        Candidato candidato = new Candidato();
        candidato.setDU(rs.getInt("DU"));
        candidato.setNombre(rs.getString("NOMBRE"));
        candidato.setApellido(rs.getString("APELLIDO"));
        candidato.setEdad(rs.getInt("EDAD"));
        candidato.setGenero(rs.getString("GENERO"));
        candidato.setDomicilio(rs.getString("DOMICILIO"));
        candidato.setPartido(rs.getString("PARTIDO"));
        candidato.setLema(rs.getString("LEMA"));

        candidatos.add(candidato );
    }

    return candidatos;
}
	
public void imprimirPadronCandidatos(ArrayList<Candidato> candidatos) {
for (Candidato candidato : candidatos) {
	System.out.printf("DU " + candidato.getDU() + " -- NOMBRE: " +  candidato.getNombre() + " -- APELLIDO: " + 
			candidato.getApellido() + "EDAD: " +
			candidato.getEdad() + " -- GENERO: " + candidato.getGenero() + " -- DOMICILIO " + candidato.getDomicilio()
	+ " -- PARTIDO " + candidato.getPartido() +  " -- LEMA " + candidato.getLema() + "%n");
}
}

public void ValidarCanditato(int duc) {
	try {
		
		String validarcan = "SELECT COUNT(du) FROM candidato WHERE du = ?";
        PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
        statementrc.setInt(1, duc);
        ResultSet rc = statementrc.executeQuery();

        // Si el votante es candidato, lanzamos una excepción
        if (rc.next() && rc.getInt(1) > 0) {
            System.out.println("CANDIDATO ENCONTRADO");
        }else {
        	System.out.println("EL CANDIDATO NO EXISTE");
        	
   //     	Instancia.Menu();
        }
		
	} catch (Exception e) {
		// TODO: handle exception
	}
}

public ArrayList<Candidato> votosporcandidato() throws SQLException {
    cn.conexion();

    String cargap = "SELECT NOMBRE, APELLIDO, PARTIDO , LEMA, VOTOREC FROM candidato";

    PreparedStatement statement = cn.conexion().prepareStatement(cargap);
    
    ResultSet rs = statement.executeQuery();

    while (rs.next()) {
        Candidato candidato = new Candidato();
        candidato.setNombre(rs.getString("NOMBRE"));
        candidato.setApellido(rs.getString("APELLIDO"));
        candidato.setPartido(rs.getString("PARTIDO"));
        candidato.setLema(rs.getString("LEMA"));
        candidato.setVotorec(rs.getInt("VOTOREC"));

        candidatos.add(candidato);
    }

    return candidatos;
 
}


public void votosporcandidatos(ArrayList<Candidato> candidatos) {

for (Candidato candidato : candidatos) {    
System.out.printf("-- NOMBRE: " +  candidato.getNombre() + " -- APELLIDO: " + candidato.getApellido() + 
" -- PARTIDO " + candidato.getPartido() +  " -- LEMA " + candidato.getLema() +
" -- VOTOS " + candidato.getVotorec()+ "%n");
}
}


public ArrayList<Candidato> votosporpartido() throws SQLException {
    cn.conexion();

    String cargap = "SELECT PARTIDO, SUM(VOTOREC) AS votos from candidato GROUP BY PARTIDO ORDER BY PARTIDO";

    PreparedStatement statement = cn.conexion().prepareStatement(cargap);
    
    ResultSet rs = statement.executeQuery();

    while (rs.next()) {
        Candidato candidato = new Candidato();
        candidato.setPartido(rs.getString("PARTIDO"));
        candidato.setVotorec(rs.getInt("VOTOS"));

        candidatos.add(candidato);
    }

    return candidatos;
}

public void votosporpartido(ArrayList<Candidato> candidatos) {

for (Candidato candidato : candidatos) {    
System.out.printf(" -- PARTIDO " + candidato.getPartido() +
" -- VOTOS " + candidato.getVotorec()+ "%n");
}
}

public ArrayList<Candidato> votosporpartidofinal(int id) throws SQLException {
    cn.conexion();

    Eleccion eleccion = new Eleccion();
    
    eleccion.setId(id);
    
    String cargap = "SELECT PARTIDO, SUM(VOTOREC) AS votos from candidato_eleccion "
    		+ "WHERE ID_ELECCION = ? GROUP BY PARTIDO ORDER BY PARTIDO";

    PreparedStatement statement = cn.conexion().prepareStatement(cargap);
    statement.setInt(1, eleccion.getId());
    ResultSet rs = statement.executeQuery();

    while (rs.next()) {
        Candidato candidato = new Candidato();
        candidato.setPartido(rs.getString("PARTIDO"));
        candidato.setVotorec(rs.getInt("VOTOS"));

        candidatos.add(candidato);
    }

    return candidatos;
}

public void votosporpartidofinal(ArrayList<Candidato> candidatos) {

for (Candidato candidato : candidatos) {    
System.out.printf(" -- PARTIDO " + candidato.getPartido() +
" -- VOTOS " + candidato.getVotorec()+ "%n");
}
}

public ArrayList<Candidato> votosporcandidatofinal(int id) throws SQLException {
    cn.conexion();

    Eleccion eleccion = new Eleccion();
    
    eleccion.setId(id);
    
    String cargap = "SELECT NOMBRE, APELLIDO, PARTIDO , LEMA, VOTOREC FROM candidato_eleccion"
    		+ "WHERE ID_ELECCION = ?  ORDER BY VOTOREC DESC LIMIT 3;";

    PreparedStatement statement = cn.conexion().prepareStatement(cargap);
    statement.setInt(1, eleccion.getId());    
    ResultSet rs = statement.executeQuery();

    while (rs.next()) {
        Candidato candidato = new Candidato();
        candidato.setNombre(rs.getString("NOMBRE"));
        candidato.setApellido(rs.getString("APELLIDO"));
        candidato.setPartido(rs.getString("PARTIDO"));
        candidato.setLema(rs.getString("LEMA"));
        candidato.setVotorec(rs.getInt("VOTOREC"));

        candidatos.add(candidato);
    }

    return candidatos;
 
}


public void votosporcandidatosfinal(ArrayList<Candidato> candidatos) {

for (Candidato candidato : candidatos) {    
System.out.printf("-- NOMBRE: " +  candidato.getNombre() + " -- APELLIDO: " + candidato.getApellido() + 
" -- PARTIDO " + candidato.getPartido() +  " -- LEMA " + candidato.getLema() +
" -- VOTOS " + candidato.getVotorec()+ "%n");
}
}

}
