package Vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class Valtacandidato extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField DU;
	private JTextField NOMBRE;
	private JTextField APELLIDO;
	private JTextField EDAD;
	private JTextField DOMICILIO;
	private JTextField LEMA;

	/**
	 * Create the panel.
	 */
	public Valtacandidato() {
		setLayout(null);
		
		JLabel DUUUUU = new JLabel("DU");
		DUUUUU.setBounds(67, 141, 122, 24);
		add(DUUUUU);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(67, 176, 122, 24);
		add(lblNombre);
		
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(67, 211, 122, 24);
		add(lblApellido);
		
		JLabel lblEdad = new JLabel("EDAD");
		lblEdad.setBounds(67, 246, 122, 24);
		add(lblEdad);
		
		JLabel lblGenero = new JLabel("GENERO");
		lblGenero.setBounds(67, 281, 122, 24);
		add(lblGenero);
		
		JLabel lblDomicilio = new JLabel("DOMICILIO");
		lblDomicilio.setBounds(67, 316, 122, 24);
		add(lblDomicilio);
		
		JLabel lblLema = new JLabel("LEMA");
		lblLema.setBounds(67, 351, 122, 24);
		add(lblLema);
		
		JLabel lblPartido = new JLabel("PARTIDO");
		lblPartido.setBounds(67, 386, 122, 24);
		add(lblPartido);
		
		DU = new JTextField();
		DU.setBounds(290, 143, 474, 20);
		add(DU);
		DU.setColumns(10);
		
		NOMBRE = new JTextField();
		NOMBRE.setColumns(10);
		NOMBRE.setBounds(290, 178, 474, 20);
		add(NOMBRE);
		
		APELLIDO = new JTextField();
		APELLIDO.setColumns(10);
		APELLIDO.setBounds(290, 213, 474, 20);
		add(APELLIDO);
		
		EDAD = new JTextField();
		EDAD.setColumns(10);
		EDAD.setBounds(290, 248, 474, 20);
		add(EDAD);
		
		DOMICILIO = new JTextField();
		DOMICILIO.setColumns(10);
		DOMICILIO.setBounds(290, 318, 474, 20);
		add(DOMICILIO);
		
		LEMA = new JTextField();
		LEMA.setColumns(10);
		LEMA.setBounds(290, 353, 474, 20);
		add(LEMA);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
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
				
		        String lema = LEMA.getText();
		        
		        
		        
		        try {
		            Conect conexion = new Conect();
		            PreparedStatement ps = conexion.conexion().prepareStatement("INSERT INTO candidato "
		                    + "(nombre, apellido, dni, genero, edad, domicilio,partido,lema, VotosRecibidos) "
		                    + "VALUES (?,?,?,?,?,?,?,?,?)");

		            ps.setString(1, nombre);
		            ps.setString(2, apellido);
		            ps.setInt(3, du);
		            ps.setString(4, GENERO);
		            ps.setInt(5, edad);
		            ps.setString(6, domicilio);
		            ps.setString(7, partido);
		            ps.setString(8, lema);
		            ps.setInt(9, 0);

		            ps.executeUpdate();

		            JOptionPane.showMessageDialog(null, "Candidato agregado correctamente");
		            
		            conexion.cerrar();
		        } catch (Exception e1) {
		            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR CANDIDATO");
		        }
				
			}
		});
		btnNewButton.setBounds(290, 531, 474, 23);
		add(btnNewButton);
		
		JRadioButton MASCULINO = new JRadioButton("MASCULINO");
		MASCULINO.setBounds(290, 282, 109, 23);
		add(MASCULINO);
		
		JRadioButton FEMENINO = new JRadioButton("FEMENINO");
		FEMENINO.setBounds(483, 282, 109, 23);
		add(FEMENINO);
		
		JRadioButton OTRO = new JRadioButton("OTRO");
		OTRO.setBounds(655, 282, 109, 23);
		add(OTRO);

	}

}
