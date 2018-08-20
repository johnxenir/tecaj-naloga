package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.border.BevelBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class ArtikelOkno extends JFrame {

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
					ArtikelOkno frame = new ArtikelOkno();
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
	public ArtikelOkno() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Artikel");
		setBounds(100, 100, 230, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		Baza baza = new Baza();
		
		JComboBox modelComboBox = new JComboBox();
		modelComboBox.setBounds(110, 7, 70, 20);
		modelComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiModele()));
		
		JSpinner kolicinaSpinner = new JSpinner();
		kolicinaSpinner.setBounds(138, 69, 40, 20);
		panel.setLayout(null);
		panel.add(modelComboBox);
		panel.add(kolicinaSpinner);
		
		JLabel lblModel = new JLabel("Model");
		lblModel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblModel.setBounds(10, 10, 46, 14);
		panel.add(lblModel);
		
		JComboBox velikostComboBox = new JComboBox();
		velikostComboBox.setBounds(110, 38, 70, 20);
		panel.add(velikostComboBox);
		velikostComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiVelikosti()));
		
		JLabel lblVelikost = new JLabel("Velikost");
		lblVelikost.setBounds(10, 41, 46, 14);
		panel.add(lblVelikost);
		
		JLabel lblKolicina = new JLabel("Kolicina");
		lblKolicina.setBounds(10, 72, 46, 14);
		panel.add(lblKolicina);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(10, 117, 89, 23);
		panel.add(btnDodaj);
		btnDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int model = ((ItemPair)modelComboBox.getSelectedItem()).getKey();
				int velikost = ((ItemPair)velikostComboBox.getSelectedItem()).getKey();
				int kolicina = (int)kolicinaSpinner.getValue();
				baza.dodajArtikel(model, velikost, kolicina);				
			}			
		});
	}
	
	public void nastaviGlavnoOkno(GlavnoOkno go)
	{
		parent = go;
	}
}
