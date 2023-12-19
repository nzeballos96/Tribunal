package Vista;

import javax.swing.JPanel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Conect;

public class Vprovisorio extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable CANDIDATO;
	private JTable PARTIDO;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public Vprovisorio() throws SQLException {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VOTOS POR CANDIDATO");
		lblNewLabel.setBounds(258, 80, 399, 14);
		add(lblNewLabel);
		
		CANDIDATO = new JTable();
		CANDIDATO.setBounds(210, 115, 447, 182);
		add(CANDIDATO);
		
		JLabel lblVotosPorPartido = new JLabel("VOTOS POR PARTIDO");
		lblVotosPorPartido.setBounds(210, 344, 399, 14);
		add(lblVotosPorPartido);
		
		PARTIDO = new JTable();
		PARTIDO.setBounds(210, 404, 447, 182);
		add(PARTIDO);

		mostrarPARTIDO();
	}
	
	public void mostrarPARTIDO() throws SQLException {
        String sqlmc = "SELECT PARTIDO, SUM(VOTOREC) AS votos from candidato GROUP BY PARTIDO ORDER BY PARTIDO";
        //Statement st;
        Conect conexion = new Conect();
        //System.out.println(sqlmc);

        DefaultTableModel modelvotante = new DefaultTableModel();
        modelvotante.addColumn("PARTIDO");
        modelvotante.addColumn("VOTOS");
 
        PARTIDO.setModel(modelvotante);

        String[] datos = new String[7];
        try {
            PreparedStatement ps = conexion.conexion().prepareStatement(sqlmc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                
                modelvotante.addRow(datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR EN CARGA" + e.toString());
        }
	
}

}
