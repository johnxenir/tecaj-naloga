package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PaketOkno extends JFrame {

	private JPanel contentPane;
	private GlavnoOkno parent;
	private PaketOkno trenutnoOkno;
	
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
		
		addWindowListener(new WindowAdapter() 
		{
			  public void windowClosing(WindowEvent e)
			  {
				  baza.zapri();
				  parent.setEnabled(true);
			  }
		});
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setBounds(10, 11, 46, 14);
		panel.add(lblModel);
		
		JLabel lblVelikost = new JLabel("Velikost");
		lblVelikost.setBounds(10, 36, 46, 14);
		panel.add(lblVelikost);
		
		JLabel lblSteviloArtiklov = new JLabel("\u0160t. artiklov v paketu");
		lblSteviloArtiklov.setBounds(10, 86, 115, 14);
		panel.add(lblSteviloArtiklov);
		
		JComboBox modelComboBox = new JComboBox();
		modelComboBox.setBounds(94, 8, 100, 20);
		panel.add(modelComboBox);
		modelComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiModele()));
		
		JComboBox velikostComboBox = new JComboBox();
		velikostComboBox.setBounds(94, 33, 100, 20);
		panel.add(velikostComboBox);
		velikostComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiVelikosti()));
		
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
				int stevilo = (int)steviloSpinner.getValue();
				int kolicina = (int)kolicinaSpinner.getValue();
				int result = baza.dodajPaket(model, velikost, stevilo, kolicina);
				if (result == Baza.RESULT_NI_ZALOGE)
				{
					JOptionPane.showMessageDialog(null, "Ni dovolj zaloge artiklov", "Napaka", JOptionPane.ERROR_MESSAGE);
				}
				else if (result == Baza.RESULT_NI_MODELA)
				{
					JOptionPane.showMessageDialog(null, "Model ne obstaja", "Napaka", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					baza.zapri();
					parent.napolniTabelo();
					parent.setEnabled(true);
					trenutnoOkno.dispose();
				}
			}			
		});
		
		trenutnoOkno = this;
		parent = go;
	}
}
