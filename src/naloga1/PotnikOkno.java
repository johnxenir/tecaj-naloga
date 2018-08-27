package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class PotnikOkno extends JFrame {

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
		setBounds(100, 100, 230, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		Baza baza = new Baza();
		
		JLabel lblPotnik = new JLabel("Potnik");
		lblPotnik.setBounds(10, 11, 46, 14);
		panel.add(lblPotnik);
		
		JLabel lblPaket = new JLabel("Paket");
		lblPaket.setBounds(10, 36, 46, 14);
		panel.add(lblPaket);
		
		JLabel lblKolicina = new JLabel("Kolicina");
		lblKolicina.setBounds(10, 61, 46, 14);
		panel.add(lblKolicina);
		
		JComboBox potnikComboBox = new JComboBox();
		potnikComboBox.setBounds(124, 8, 70, 20);
		panel.add(potnikComboBox);
		potnikComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiPotnike()));
		
		JComboBox paketComboBox = new JComboBox();
		paketComboBox.setBounds(124, 33, 70, 20);
		panel.add(paketComboBox);
		paketComboBox.setModel(new DefaultComboBoxModel<ItemPair>(baza.izberiPakete()));
		
		JSpinner kolicinaSpinner = new JSpinner();
		kolicinaSpinner.setBounds(154, 58, 40, 20);
		panel.add(kolicinaSpinner);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(10, 97, 89, 23);
		panel.add(btnDodaj);
		btnDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int potnik = ((ItemPair)potnikComboBox.getSelectedItem()).getKey();
				int paket = ((ItemPair)paketComboBox.getSelectedItem()).getKey();
				int kolicina = (int)kolicinaSpinner.getValue();
				baza.dodajTovor(potnik, paket, kolicina);
				
				parent.napolniTabelo();
			}			
		});
	}

}
