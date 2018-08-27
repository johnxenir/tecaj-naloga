package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PaketOkno extends JFrame {

	private JPanel contentPane;
	private GlavnoOkno parent;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaketOkno frame = new PaketOkno();
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
	public PaketOkno(GlavnoOkno go) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Paket");
		setBounds(100, 100, 231, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Baza baza = new Baza();
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(10, 11, 46, 14);
		panel.add(lblModel);
		
		JLabel lblVelikost = new JLabel("Velikost");
		lblVelikost.setBounds(10, 36, 46, 14);
		panel.add(lblVelikost);
		
		JLabel lblNaziv = new JLabel("Naziv");
		lblNaziv.setBounds(10, 61, 46, 14);
		panel.add(lblNaziv);
		
		JLabel lblSteviloArtiklov = new JLabel("Stevilo artiklov");
		lblSteviloArtiklov.setBounds(10, 86, 70, 14);
		panel.add(lblSteviloArtiklov);
		
		JComboBox modelComboBox = new JComboBox();
		modelComboBox.setBounds(124, 8, 70, 20);
		panel.add(modelComboBox);
		modelComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiModele()));
		
		JComboBox velikostComboBox = new JComboBox();
		velikostComboBox.setBounds(124, 33, 70, 20);
		panel.add(velikostComboBox);
		velikostComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiVelikosti()));
		
		JTextArea nazivTextArea = new JTextArea();
		nazivTextArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
		nazivTextArea.setBounds(88, 61, 106, 16);
		panel.add(nazivTextArea);
		
		JSpinner steviloSpinner = new JSpinner();
		steviloSpinner.setBounds(154, 83, 40, 20);
		panel.add(steviloSpinner);
		
		JSpinner kolicinaSpinner = new JSpinner();
		kolicinaSpinner.setBounds(154, 108, 40, 20);
		panel.add(kolicinaSpinner);
		
		JLabel lblKolicina = new JLabel("Kolicina");
		lblKolicina.setBounds(10, 111, 46, 14);
		panel.add(lblKolicina);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(10, 146, 89, 23);
		panel.add(btnDodaj);
		btnDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int model = ((ItemPair)modelComboBox.getSelectedItem()).getKey();
				int velikost = ((ItemPair)velikostComboBox.getSelectedItem()).getKey();
				String naziv = nazivTextArea.getText();
				int stevilo = (int)steviloSpinner.getValue();
				int kolicina = (int)kolicinaSpinner.getValue();
				baza.dodajPaket(model, velikost, naziv, stevilo, kolicina);
				
				parent.napolniTabelo();
			}			
		});
		
		parent = go;
	}
}
