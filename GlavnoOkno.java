package naloga1;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

public class GlavnoOkno extends JFrame {

	private JPanel contentPane;
	
	private ArtikelOkno artikelOkno;
	private PaketOkno paketOkno;
	private TovorOkno potnikOkno;
	
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
	public GlavnoOkno() 
	{
		setTitle("Aplikacija");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 581);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnDatoteka = new JMenu("Datoteka");
		menuBar.add(mnDatoteka);
		
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
					glavnoOkno.setEnabled(false);
					frame.setVisible(true);
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
					glavnoOkno.setEnabled(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}			
		});
		
		JMenuItem mntmTovor = new JMenuItem("Tovor");
		mnDodaj.add(mntmTovor);
		mntmTovor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					TovorOkno frame = new TovorOkno(glavnoOkno);
					glavnoOkno.setEnabled(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}			
		});
		
		JMenuItem mntmModel = new JMenuItem("Model");
		mnDodaj.add(mntmModel);
		mntmModel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ModelOkno frame = new ModelOkno(glavnoOkno);
					glavnoOkno.setEnabled(false);
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
					glavnoOkno.setEnabled(false);
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
		
		JButton btnDodajArtikle = new JButton("Dodaj ...");
		btnDodajArtikle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ArtikelOkno frame = new ArtikelOkno(glavnoOkno);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		GroupLayout gl_artikelPanel = new GroupLayout(artikelPanel);
		gl_artikelPanel.setHorizontalGroup(
			gl_artikelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_artikelPanel.createSequentialGroup()
					.addGap(36)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 369, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(btnDodajArtikle)
					.addContainerGap(107, Short.MAX_VALUE))
		);
		gl_artikelPanel.setVerticalGroup(
			gl_artikelPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_artikelPanel.createSequentialGroup()
					.addContainerGap(38, Short.MAX_VALUE)
					.addGroup(gl_artikelPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDodajArtikle))
					.addGap(37))
		);
		
		artikliTable = new JTable();
		artikliTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		artikliTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Model", "Velikost", "Koli\u010Dina na zalogi"
			}
		) {
		    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		}
		);
		artikliTable.getColumnModel().getColumn(2).setPreferredWidth(95);
		scrollPane.setViewportView(artikliTable);
		artikelPanel.setLayout(gl_artikelPanel);
		
		JPanel paketPanel = new JPanel();
		tabbedPane.addTab("Paketi", null, paketPanel, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JButton btnDodajPaket = new JButton("Dodaj ...");
		btnDodajPaket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					PaketOkno frame = new PaketOkno(glavnoOkno);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		GroupLayout gl_paketPanel = new GroupLayout(paketPanel);
		gl_paketPanel.setHorizontalGroup(
			gl_paketPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paketPanel.createSequentialGroup()
					.addGap(48)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDodajPaket, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_paketPanel.setVerticalGroup(
			gl_paketPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_paketPanel.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_paketPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 388, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDodajPaket))
					.addContainerGap(54, Short.MAX_VALUE))
		);
		
		paketiTable = new JTable();
		paketiTable.setEnabled(false);
		paketiTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Model", "Velikost", "\u0160tevilo artiklov", "Koli\u010Dina"
			}
			
		) {
		    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		});
		paketiTable.getColumnModel().getColumn(2).setPreferredWidth(84);
		scrollPane_1.setViewportView(paketiTable);
		paketPanel.setLayout(gl_paketPanel);
		
		JPanel tovorPanel = new JPanel();
		tabbedPane.addTab("Tovor", null, tovorPanel, null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JButton btnDodajTovor = new JButton("Dodaj ...");
		btnDodajTovor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					TovorOkno frame = new TovorOkno(glavnoOkno);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		
		JButton btnOddaj = new JButton("Oddaj");
		btnOddaj.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				/*int potnik = ((ItemPair)tovorComboBox.getSelectedItem()).getKey();
				int paket = ((ItemPair)paketComboBox.getSelectedItem()).getKey();
				int kolicina = (int)kolicinaSpinner.getValue();*/
				
				int tovor_id = potnikiTable.getSelectedRow();
				if (tovor_id == -1)
				{
					JOptionPane.showMessageDialog(null, "Izberite tovor", "Napaka", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int potnik = Integer.parseInt(potnikiTable.getModel().getValueAt(tovor_id, 0).toString());
				
				System.out.println(potnik);
				
				Baza baza = new Baza();
				int result = baza.dodajObstojecTovor(potnik, 0, 0);
				if (result == Baza.RESULT_NI_ZALOGE)
				{
					JOptionPane.showMessageDialog(null, "Ni dovolj zaloge paketov", "Napaka", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Raèun izdan", "Oddajanje", JOptionPane.INFORMATION_MESSAGE);
					baza.zapri();
					napolniTabelo();
				}
			}			
		});
		GroupLayout gl_tovorPanel = new GroupLayout(tovorPanel);
		gl_tovorPanel.setHorizontalGroup(
			gl_tovorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tovorPanel.createSequentialGroup()
					.addGap(45)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_tovorPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnOddaj, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDodajTovor, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
					.addContainerGap(41, Short.MAX_VALUE))
		);
		gl_tovorPanel.setVerticalGroup(
			gl_tovorPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tovorPanel.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_tovorPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_tovorPanel.createSequentialGroup()
							.addComponent(btnDodajTovor)
							.addGap(19)
							.addComponent(btnOddaj)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		
		potnikiTable = new JTable();
		potnikiTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u0160ifra", "Potnik", "Paket", "Koli\u010Dina"
			}
		));
		scrollPane_2.setViewportView(potnikiTable);
		tovorPanel.setLayout(gl_tovorPanel);
		
		File f = new File("nogavice.db");
		if (f.exists())
		{	
			napolniTabelo();
		}
		else
		{
			Baza baza = new Baza();
			int rezultat = baza.kreirajBazo();
			if (rezultat == 1)
			{
				JOptionPane.showMessageDialog(null, "Baza je že ustvarjena", "Napaka", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		artikliTable.setAutoCreateRowSorter(true);
		paketiTable.setAutoCreateRowSorter(true);
		potnikiTable.setAutoCreateRowSorter(true);
		//artikliTable.setModel(nonEditableModel);
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
