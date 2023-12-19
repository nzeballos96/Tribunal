package Controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.Conect;
import Modelo.Eleccion;
import Modelo.ExcepcionVotanteYaCargado;
import Modelo.Instancia;


public class CEleccion {
	   static Conect cn = new Conect();
       static Instancia Instancia = new Instancia();
       private ArrayList<Eleccion> elecciones;
       
       public CEleccion() {
	        cn.conexion();
	        elecciones = new ArrayList<>();
	    }

       public static void cargareleccion(int dia, int mes, int anio, String detalle ) throws SQLException {
    		  		
    	   try {
    	        cn.conexion();
    	        Eleccion eleccion = new Eleccion();

    	        // Consulta para comprobar si el votante ya existe
    	        String validarsuf = "SELECT * FROM eleccion WHERE dia = ? and mes = ? "
    	                + "and anio = ? ;";
    	        PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
    	        statementrs.setInt(1, dia);
    	        statementrs.setInt(2, mes);
    	        statementrs.setInt(3, anio);
    	        ResultSet rs = statementrs.executeQuery();

    	        // Si el votante ya existe, lanzamos una excepción
    	        if (rs.next() && rs.getInt(1) > 0) {
    	            throw new ExcepcionVotanteYaCargado("ELECCION YA EXISTENTEN LA FECHA " + dia + "/" + mes + "/" + anio);
    	        }

    	        int id = 0;
    	        int Status = 0;

    	        eleccion.setId(id);
    	        eleccion.setDia(dia);
    	        eleccion.setMes(mes);
    	        eleccion.setAnio(anio);
    	        eleccion.setDetalle(detalle);
    	        eleccion.setStatus(Status);

    	        PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO eleccion (Id, DIA, MES, ANIO, DETALLE, STATUS)"
    	                + " VALUES (null, ?, ?, ?, ?, ?)");

    	        ps.setInt(1, eleccion.getDia());
    	        ps.setInt(2, eleccion.getMes());
    	        ps.setInt(3, eleccion.getAnio());
    	        ps.setString(4, eleccion.getDetalle());
    	        ps.setInt(5, eleccion.getStatus());

    	        ps.execute();

    	        System.out.println("ELECCION CARGADA");
    	        cn.cerrar();

    	    } catch (Exception e) {
    	        System.out.println("NO SE PUDO CARGAR ELECCION, SE REGRESA AL MENU" + e);
    	        //Instancia.Menu();
    	        e.printStackTrace();
    	    }

    					
    	}

public ArrayList<Eleccion> listaelecciones() throws SQLException {
    cn.conexion();

    String cargap = "SELECT id, dia, mes, anio, detalle, status from eleccion ";

    PreparedStatement statement = cn.conexion().prepareStatement(cargap);
    
    ResultSet rs = statement.executeQuery();

    while (rs.next()) {
        Eleccion eleccion = new Eleccion();
        eleccion.setId(rs.getInt("ID"));
        eleccion.setDia(rs.getInt("DIA"));
        eleccion.setMes(rs.getInt("MES"));
        eleccion.setAnio(rs.getInt("ANIO"));
        eleccion.setDetalle(rs.getString("DETALLE"));
        eleccion.setStatus(rs.getInt("STATUS"));
        
        
        elecciones.add(eleccion);
    }

    return elecciones;
}

public void imprimirELECCIONES(ArrayList<Eleccion> elecciones) {

for (Eleccion eleccion : elecciones) {
	System.out.printf("ID " + eleccion.getId() + " -- DIA: " +  eleccion.getDia() + " -- MES: " + 
			eleccion.getMes() + "AÑO: " +
			eleccion.getAnio() + " -- DETALLE: " + eleccion.getDetalle() + " -- STATUS " + eleccion.getStatus()+ "%n");
}
}


public static void cierrecandidatos() throws SQLException {
		
	   try {
	        cn.conexion();
	        Eleccion eleccion = new Eleccion();

	       
	        PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO candidato_eleccion "
	        		+ "(DU_CANDIDATO, APELLIDO, NOMBRE, EDAD, GENERO, domicilio, PARTIDO, Lema, VOTOREC) "
	        		+ "SELECT DU, APELLIDO, NOMBRE, edad, GENERO, DOMICILIO, PARTIDO, LEMA, VOTOREC FROM candidato;");
	        ps.execute();

	        System.out.println("ELECCION CARGADA");
	        cn.cerrar();

	    } catch (Exception e) {
	        System.out.println("NO SE PUDO CERRAR ELECCION, SE REGRESA AL MENU" + e);
	        //Instancia.Menu();
	        e.printStackTrace();
	    }

					
	}

public static void cierreeleccion(int id) throws SQLException {
	
	   try {
	        cn.conexion();
	        Eleccion eleccion = new Eleccion();

	       
	        eleccion.setId(id);
	        
	        PreparedStatement ps = cn.conexion().prepareStatement("UPDATE candidato_eleccion "
	        		+ "set ID_ELECCION = ? WHERE ID_ELECCION = 0;");
	        
	        ps.setInt(1, eleccion.getId());
	        
	        ps.execute();

	        System.out.println("ELECCION CARGADA");
	        cn.cerrar();

	    } catch (Exception e) {
	        System.out.println("NO SE PUDO CERRAR ELECCION, SE REGRESA AL MENU" + e);
	        //Instancia.Menu();
	        e.printStackTrace();
	    }

					
	}

public static void cierrevotantes() throws SQLException {
	
	   try {
	        cn.conexion();
	        Eleccion eleccion = new Eleccion();

	       
	        PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO sufragante_eleccion "
	        		+ "(CANTIDA_SUFRAGANTE, CANTIDA_VOTOS, SUFRAGANTES_FALTANTES)"
	        		+ " SELECT COUNT(DU) AS total_sufragantes, SUM(checkin)"
	        		+ " AS sufragantes_con_checkin, COUNT(DU) - SUM(checkin)"
	        		+ " AS sufragantes_sin_checkin FROM sufragante;");
	        ps.execute();

	        System.out.println("ELECCION CARGADA");
	        cn.cerrar();

	    } catch (Exception e) {
	        System.out.println("NO SE PUDO CERRAR ELECCION, SE REGRESA AL MENU" + e);
	        //Instancia.Menu();
	        e.printStackTrace();
	    }

					
	}


public static void cierrevotan(int id) throws SQLException {
	
	   try {
	        cn.conexion();
	        Eleccion eleccion = new Eleccion();

	       
	        eleccion.setId(id);
	        
	        PreparedStatement ps = cn.conexion().prepareStatement("UPDATE sufragante_eleccion  "
	        		+ "set ID_ELECCION = ? WHERE ID_ELECCION = 0;");
	        
	        ps.setInt(1, eleccion.getId());
	        
	        ps.execute();

	        System.out.println("ELECCION CARGADA");
	        cn.cerrar();

	    } catch (Exception e) {
	        System.out.println("NO SE PUDO CERRAR ELECCION, SE REGRESA AL MENU" + e);
	        //Instancia.Menu();
	        e.printStackTrace();
	    }

					
	}


public static void cierreeleccionfecha(int id) throws SQLException {
	
	   try {
	        cn.conexion();
	        Eleccion eleccion = new Eleccion();

	       
	        eleccion.setId(id);
	        
	        PreparedStatement ps = cn.conexion().prepareStatement("UPDATE eleccion  "
	        		+ "set Status = 1 WHERE ID = ?;");
	        
	        ps.setInt(1, eleccion.getId());
	        
	        ps.execute();

	        System.out.println("ELECCION CERRADA");
	        cn.cerrar();

	    } catch (Exception e) {
	        System.out.println("NO SE PUDO CERRAR ELECCION, SE REGRESA AL MENU" + e);
	        //Instancia.Menu();
	        e.printStackTrace();
	    }

					
	}

public static void LIMPIARVOTOS() throws SQLException {
	
	   try {
	        cn.conexion();
	        Eleccion eleccion = new Eleccion();
	        
	        PreparedStatement ps = cn.conexion().prepareStatement("UPDATE candidato  "
	        		+ "set votorec = 0 WHERE votorec <> 0;");
	        
	        ps.execute();

	        System.out.println("CANDIDATO LIMPIO");
	        cn.cerrar();

	    } catch (Exception e) {
	        System.out.println("NO SE PUDO CERRAR ELECCION, SE REGRESA AL MENU" + e);
	        //Instancia.Menu();
	        e.printStackTrace();
	    }
	
	}

public static void LIMPIARCHECK() throws SQLException {
	
	   try {
	        cn.conexion();
	        Eleccion eleccion = new Eleccion();
	        
	        PreparedStatement ps = cn.conexion().prepareStatement("UPDATE SUFRAGANTE  "
	        		+ "set CHECKIN = 0 WHERE CHECKIN <> 0;");
	        
	        ps.execute();

	        System.out.println("CANDIDATO LIMPIO");
	        cn.cerrar();

	    } catch (Exception e) {
	        System.out.println("NO SE PUDO CERRAR ELECCION, SE REGRESA AL MENU" + e);
	        //Instancia.Menu();
	        e.printStackTrace();
	    }
	
	}


public void escrutiniofinal(int id) throws SQLException {

cierrecandidatos();
cierreeleccion(id);
cierreeleccionfecha(id);
cierrevotan(id);
cierrevotantes();
LIMPIARVOTOS();
LIMPIARCHECK();
	
}


public Eleccion buscar(int id) {
    Eleccion eleccion = null;
    try {
        Conect cn = new Conect();
        cn.conexion();
        String buscarresto = "SELECT * FROM eleccion WHERE Id = ?";

        PreparedStatement statement = cn.conexion().prepareStatement(buscarresto);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            eleccion = new Eleccion();
            eleccion.setId(rs.getInt("Id"));
            eleccion.setDia(rs.getInt("dia"));
            eleccion.setMes(rs.getInt("mes"));
            eleccion.setAnio(rs.getInt("anio"));
            eleccion.setDetalle(rs.getString("detalle"));
            eleccion.setStatus(rs.getInt("Status"));
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar el restaurante: " + e.getMessage());
    }

    return eleccion;
}

public Eleccion buscarESTATUS(int id) {
    Eleccion eleccion = null;
    try {
        Conect cn = new Conect();
        cn.conexion();
        String buscarresto = "SELECT * FROM eleccion WHERE Id = ? AND STATUS = 1 ";

        PreparedStatement statement = cn.conexion().prepareStatement(buscarresto);
        statement.setInt(1, id);

        ResultSet rs = statement.executeQuery();

        if (rs ==null) {
           System.out.println("LA ELECCION YA SE CERRO"); 
        }
    } catch (SQLException e) {
        System.out.println("Error al buscar el restaurante: " + e.getMessage());
    }

    return eleccion;
}


}


