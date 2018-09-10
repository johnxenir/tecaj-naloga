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
import javax.swing.JButton;

public class PotnikOkno extends JFrame {

	private JPanel contentPane;
	private GlavnoOkno parent;
	private JTextField imeTextField;

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
		setBounds(100, 100, 230, 150);
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
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(10, 11, 46, 14);
		panel.add(lblIme);
		
		imeTextField = new JTextField();
		imeTextField.setBounds(10, 36, 184, 20);
		panel.add(imeTextField);
		imeTextField.setColumns(10);
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.setBounds(10, 67, 89, 23);
		panel.add(btnDodaj);
		btnDodaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String ime = imeTextField.getText();
				baza.dodajPotnika(ime);
				/*if (result == 1)
				{
					JOptionPane.showMessageDialog(null, "Ni dovolj zaloge paketov", "Napaka", JOptionPane.ERROR_MESSAGE);
				}*/
				baza.zapri();
				parent.napolniTabelo();
			}			
		});		
		
		parent = go;
	}
}
