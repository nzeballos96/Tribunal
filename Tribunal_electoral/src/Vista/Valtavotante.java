package Vista;

import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import Controlador.CSufragante;
import Modelo.Conect;
import Modelo.EGenero;
import Modelo.ExcepcionCiudadanoMenordeEdad;
import Modelo.ExcepcionVotanteYaCargado;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Valtavotante extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField NOMBRE;
	private JTextField APELLIDO;
	private JTextField EDAD;
	private JTextField DOMICILIO;
	private JTextField DU;
	
	
	CSufragante sufragante  = new CSufragante();

	/**
	 * Create the panel.
	 */
	public Valtavotante() {
		setLayout(null);
		
		NOMBRE = new JTextField();
		NOMBRE.setBounds(433, 184, 254, 20);
		add(NOMBRE);
		NOMBRE.setColumns(10);
		
		APELLIDO = new JTextField();
		APELLIDO.setColumns(10);
		APELLIDO.setBounds(433, 224, 254, 20);
		add(APELLIDO);
		
		EDAD = new JTextField();
		EDAD.setColumns(10);
		EDAD.setBounds(433, 270, 254, 20);
		add(EDAD);
		
		DOMICILIO = new JTextField();
		DOMICILIO.setColumns(10);
		DOMICILIO.setBounds(433, 323, 254, 20);
		add(DOMICILIO);
		
		DU = new JTextField();
		DU.setColumns(10);
		DU.setBounds(433, 139, 254, 20);
		add(DU);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setBounds(96, 187, 237, 14);
		add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(96, 224, 237, 14);
		add(lblApellido);
		
		JLabel lblEdad = new JLabel("EDAD");
		lblEdad.setBounds(96, 273, 237, 14);
		add(lblEdad);
		
		JLabel lblDomicilio = new JLabel("DOMICILIO");
		lblDomicilio.setBounds(96, 323, 237, 14);
		add(lblDomicilio);
		
		JLabel lblGenero = new JLabel("GENERO");
		lblGenero.setBounds(96, 384, 237, 14);
		add(lblGenero);
		
		JLabel lblDu = new JLabel("DU");
		lblDu.setBounds(96, 142, 237, 14);
		add(lblDu);
		
		JRadioButton MASCULINO = new JRadioButton("MACULINO");
		MASCULINO.setBounds(313, 380, 109, 23);
		add(MASCULINO);
		
		JRadioButton FEMENINO = new JRadioButton("FEMENINO");
		FEMENINO.setBounds(498, 380, 109, 23);
		add(FEMENINO);
		
		JRadioButton OTRO = new JRadioButton("OTRO");
		OTRO.setBounds(699, 380, 109, 23);
		add(OTRO);
		
		JButton CARGAVOT = new JButton("CARGAR SUFRAGANTE");
		CARGAVOT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = NOMBRE.getText();
		        String apellido = APELLIDO.getText();
		        int du = Integer.parseInt(DU.getText());
		        String GENERO;
		        if (MASCULINO.isSelected() == true) {
		            GENERO = "MASCULINO";
		        } else if (FEMENINO.isSelected() == true) {
		            GENERO = "FEMENINO";
		        } else {
		            GENERO = "OTRO";
		        }
		        int edad = Integer.parseInt(EDAD.getText());
		        String domicilio = DOMICILIO.getText();
		        int votorealizado = 0;

		        try {
		            Conect cn = new Conect();
		            
		            
		         // Consulta para comprobar si el votante ya existe
    		        String validarsuf = "SELECT COUNT(du) FROM sufragante WHERE du = ?";
    		        PreparedStatement statementrs = cn.conexion().prepareStatement(validarsuf);
    		        statementrs.setInt(1, du);
    		        ResultSet rs = statementrs.executeQuery();

    		        // Si el votante ya existe, lanzamos una excepción
    		        if (rs.next() && rs.getInt(1) > 0) {
    		        	JOptionPane.showMessageDialog(null , "EL CIUDADANO: " + du + " YA EXISTE COMO SUFRAGANTE");
    		        }

    		        // Consulta para comprobar si el votante es candidato
    		        String validarcan = "SELECT COUNT(du) FROM candidato WHERE du = ?";
    		        PreparedStatement statementrc = cn.conexion().prepareStatement(validarcan);
    		        statementrc.setInt(1, du);
    		        ResultSet rc = statementrc.executeQuery();

    		        // Si el votante es candidato, lanzamos una excepción
    		        if (rc.next() && rc.getInt(1) > 0) {
    		        	JOptionPane.showMessageDialog(null ,"EL CIUDADANO: " + du + " FIGURA COMO CANDIDATO");
    		        }
    		
    		        if (edad <= 16) {
    		        	JOptionPane.showMessageDialog(null ,"EL CIUDADANO: " + du + " ES MENOR DE EDAD");
    		        }
		            
		            
		            cn.conexion();
		            PreparedStatement ps = cn.conexion().prepareStatement("INSERT INTO sufragante "
	    					+ "(DU, NOMBRE, APELLIDO, EDAD, GENERO, DOMICILIO, checkin)"
	    					+ " VALUES ( ? , ?, ?, ?, ?,?,?)");

		            
		            ps.setInt(1, du);
		            ps.setString(2, nombre);
		            ps.setString(3, apellido);
		            ps.setInt(4, edad);
		            ps.setString(5, GENERO);
		            ps.setString(6, domicilio);
		            ps.setInt(7, 0);

		            ps.executeUpdate();

		            JOptionPane.showMessageDialog(null, "Sufragante agregado correctamente");
		           
		            cn.cerrar();
		        } catch (Exception e1) {
		            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR SUFRAGANTE" + e1);
		        }
				
				
				
			}
		});
		CARGAVOT.setBounds(433, 480, 254, 23);
		add(CARGAVOT);

	}
}
