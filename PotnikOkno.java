package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PotnikOkno extends JFrame {

	private JPanel contentPane;
	private GlavnoOkno parent;
	private JTextField imeTextField;
	private PotnikOkno trenutnoOkno;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PotnikOkno frame = new PotnikOkno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public PotnikOkno(GlavnoOkno go) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Potnik");
		setBounds(100, 100, 371, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		addWindowListener(new WindowAdapter() 
		{
		  public void windowClosing(WindowEvent e)
		  {
		    parent.setEnabled(true);
		  }
		});
		
		Baza baza = new Baza();
		
		JLabel lblIme = new JLabel("Ime potnika:");
		lblIme.setBounds(10, 11, 184, 14);
		panel.add(lblIme);
		
		imeTextField = new JTextField();
		imeTextField.setBounds(10, 36, 184, 20);
		panel.add(imeTextField);
		imeTextField.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(224, 35, 89, 23);
		panel.add(btnDodaj);
		
		JLabel lblSeznamPotnikov = new JLabel("Seznam potnikov:");
		lblSeznamPotnikov.setBounds(10, 67, 184, 14);
		panel.add(lblSeznamPotnikov);
		
		JComboBox potnikiComboBox = new JComboBox();
		potnikiComboBox.setBounds(10, 96, 184, 20);
		potnikiComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiPotnike()));
		panel.add(potnikiComboBox);
		
		JButton btnOdstrani = new JButton("Odstrani");
		btnOdstrani.setBounds(224, 95, 89, 23);
		panel.add(btnOdstrani);
		btnDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ime = imeTextField.getText();
				if (ime.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Vnesite veljavno ime", "Napaka", JOptionPane.ERROR_MESSAGE);
					return;
				}
				baza.dodajPotnika(ime);
				baza.zapri();
				parent.napolniTabelo();
				parent.setEnabled(true);
				trenutnoOkno.dispose();
				JOptionPane.showMessageDialog(null, "Potnik '" + ime + "' je bil uspešno dodan", "Dodaj", JOptionPane.INFORMATION_MESSAGE);
			}			
		});
		btnOdstrani.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id = ((ItemPair)potnikiComboBox.getSelectedItem()).getKey();
				String ime = ((ItemPair)potnikiComboBox.getSelectedItem()).getValue();
				int result = baza.izbrisiPotnika(id);

				baza.zapri();
				parent.napolniTabelo();
				parent.setEnabled(true);
				trenutnoOkno.dispose();
				if (result == Baza.RESULT_V_UPORABI)
				{
					JOptionPane.showMessageDialog(null, "Potnik '" + ime + "' je v uporabi", "Napaka", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Potnik '" + ime + "' je bil uspešno izbrisan", "Odstrani", JOptionPane.INFORMATION_MESSAGE);
				}
			}			
		});
		trenutnoOkno = this;
		parent = go;
	}
}
