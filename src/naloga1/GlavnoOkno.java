package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GlavnoOkno extends JFrame {

	private JPanel contentPane;
	
	private ArtikelOkno artikelOkno;
	private PaketOkno paketOkno;
	private PotnikOkno potnikOkno;
	
	private GlavnoOkno glavnoOkno = this;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavnoOkno frame = new GlavnoOkno();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GlavnoOkno() {
		setTitle("Aplikacija");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 581);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDatoteka = new JMenu("Datoteka");
		menuBar.add(mnDatoteka);
		
		JMenuItem mntmUstvariBazo = new JMenuItem("Ustvari Bazo");
		mnDatoteka.add(mntmUstvariBazo);
		mntmUstvariBazo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Baza baza = new Baza();
				baza.kreirajBazo();
			}			
		});
		
		JMenuItem mntmZapri = new JMenuItem("Zapri");
		mnDatoteka.add(mntmZapri);
		mntmZapri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}			
		});
		
		JMenu mnDodaj = new JMenu("Dodaj");
		menuBar.add(mnDodaj);
		
		JMenuItem mntmArtikel = new JMenuItem("Artikel");
		mnDodaj.add(mntmArtikel);
		mntmArtikel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArtikelOkno frame = new ArtikelOkno();
					frame.setVisible(true);
					//frame.nastaviGlavnoOkno(glavnoOkno);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}			
		});
		
		JMenuItem mntmPaket = new JMenuItem("Paket");
		mnDodaj.add(mntmPaket);
		mntmPaket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					PaketOkno frame = new PaketOkno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}			
		});		
		
		JMenuItem mntmPotnik = new JMenuItem("Potnik");
		mnDodaj.add(mntmPotnik);
		mntmPotnik.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					PotnikOkno frame = new PotnikOkno();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}			
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
