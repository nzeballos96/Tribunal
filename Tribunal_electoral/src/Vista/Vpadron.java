package Vista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Conect;

public class Vpadron extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable jmodelvotante;
	 
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public Vpadron() throws SQLException {
		setLayout(null);
		mostrarv();  
		jmodelvotante = new JTable();
		jmodelvotante.setBounds(54, 123, 819, 496);
		add(jmodelvotante);

	}

	public void mostrarv() throws SQLException {
        String sqlmc = "SELECT nombre, apellido, du, genero, edad, domicilio, checkin FROM sufragante";
        //Statement st;
        Conect conexion = new Conect();
        //System.out.println(sqlmc);

        DefaultTableModel modelvotante = new DefaultTableModel();
        modelvotante.addColumn("NOMBRE");
        modelvotante.addColumn("APELLIDO");
        modelvotante.addColumn("DNI");
        modelvotante.addColumn("GENERO");
        modelvotante.addColumn("EDAD");
        modelvotante.addColumn("DOMICILIO");
        modelvotante.addColumn("VOTO");
        jmodelvotante.setModel(modelvotante);

        String[] datos = new String[7];
        try {
            PreparedStatement ps = conexion.conexion().prepareStatement(sqlmc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                
                modelvotante.addRow(datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN CARGA" + e.toString());
        }
	
}
}
