package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GlavnoOkno extends JFrame {

	private JPanel contentPane;
	
	private ArtikelOkno artikelOkno;
	private PaketOkno paketOkno;
	private PotnikOkno potnikOkno;
	
	private GlavnoOkno glavnoOkno = this;
	private JTable artikliTable;
	private JTable paketiTable;
	private JTable potnikiTable;
	
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
		setBounds(100, 100, 618, 581);
		
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
					ArtikelOkno frame = new ArtikelOkno(glavnoOkno);
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
					PaketOkno frame = new PaketOkno(glavnoOkno);
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
					PotnikOkno frame = new PotnikOkno(glavnoOkno);
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
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel artikelPanel = new JPanel();
		tabbedPane.addTab("Artikli", null, artikelPanel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_artikelPanel = new GroupLayout(artikelPanel);
		gl_artikelPanel.setHorizontalGroup(
			gl_artikelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_artikelPanel.createSequentialGroup()
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(431, Short.MAX_VALUE))
		);
		gl_artikelPanel.setVerticalGroup(
			gl_artikelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_artikelPanel.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		
		artikliTable = new JTable();
		artikliTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Model", "Velikost", "Koli\u010Dina na zalogi"
			}
		));
		artikliTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		scrollPane.setViewportView(artikliTable);
		artikelPanel.setLayout(gl_artikelPanel);
		
		JPanel paketPanel = new JPanel();
		tabbedPane.addTab("Paketi", null, paketPanel, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_paketPanel = new GroupLayout(paketPanel);
		gl_paketPanel.setHorizontalGroup(
			gl_paketPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paketPanel.createSequentialGroup()
					.addGap(48)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(336, Short.MAX_VALUE))
		);
		gl_paketPanel.setVerticalGroup(
			gl_paketPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paketPanel.createSequentialGroup()
					.addGap(41)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(54, Short.MAX_VALUE))
		);
		
		paketiTable = new JTable();
		paketiTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Naziv", "\u0160tevilo artiklov", "Model", "Velikost", "Koli\u010Dina"
			}
		));
		paketiTable.getColumnModel().getColumn(1).setPreferredWidth(84);
		scrollPane_1.setViewportView(paketiTable);
		paketPanel.setLayout(gl_paketPanel);
		
		JPanel potnikPanel = new JPanel();
		tabbedPane.addTab("Potniki", null, potnikPanel, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_potnikPanel = new GroupLayout(potnikPanel);
		gl_potnikPanel.setHorizontalGroup(
			gl_potnikPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_potnikPanel.createSequentialGroup()
					.addGap(45)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(375, Short.MAX_VALUE))
		);
		gl_potnikPanel.setVerticalGroup(
			gl_potnikPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_potnikPanel.createSequentialGroup()
					.addGap(41)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		
		potnikiTable = new JTable();
		potnikiTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Potnik", "Paket", "Koli\u010Dina"
			}
		));
		scrollPane_2.setViewportView(potnikiTable);
		potnikPanel.setLayout(gl_potnikPanel);
		
		napolniTabelo();
	}
	
	public void napolniTabelo()
	{
		Baza baza = new Baza();
		DefaultTableModel model;
		Vector<String[]> artikli = baza.pokaziArtikle();
		model = (DefaultTableModel)artikliTable.getModel();
		model.setRowCount(0);
		for (String[] vrstica : artikli)
		{
			model.addRow(vrstica);
		}
		model = null;
		Vector<String[]> paketi = baza.pokaziPakete();
		model = (DefaultTableModel)paketiTable.getModel();
		model.setRowCount(0);
		for (String[] vrstica : paketi)
		{
			model.addRow(vrstica);
		}
		model = null;
		Vector<String[]> potniki = baza.pokaziPotnike();
		model = (DefaultTableModel)potnikiTable.getModel();
		model.setRowCount(0);
		for (String[] vrstica : potniki)
		{
			model.addRow(vrstica);
		}
	}
}
