package naloga1;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class Baza 
{
	public static final int RESULT_OK = 0;
	public static final int DATABASE_ERROR = 1;
	public static final int RESULT_NI_ZALOGE = 2;
	public static final int RESULT_NI_MODELA = 3;
	public static final int RESULT_NI_PAKETA = 4;
	public static final int RESULT_V_UPORABI = 5;
	
	private Connection c = null;
	private Statement stmt = null;

	public Baza() 
	{
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:nogavice.db");	
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public Vector<ItemPair> izberiModele()
	{
		Vector<ItemPair> imenaModelov = new Vector<ItemPair>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_model, ime FROM model"));
        try {
			while (rezultat.next())
			{
				imenaModelov.add(new ItemPair(rezultat.getInt("id_model"), rezultat.getString("ime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaModelov;
	}
	
	public Vector<ItemPair> izberiVelikosti()
	{
		Vector<ItemPair> imenaModelov = new Vector<ItemPair>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_velikost, opis FROM velikost"));
        try {
			while (rezultat.next())
			{
				imenaModelov.add(new ItemPair(rezultat.getInt("id_velikost"), rezultat.getString("opis")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaModelov;
	}
	
	public Vector<ItemPair> izberiPakete()
	{
		Vector<ItemPair> imenaPaketov = new Vector<ItemPair>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT p.id_paket, m.ime, v.opis, p.stevilo "
				+ "FROM paket p, nogavice n, model m, velikost v "
				+ "WHERE p.id_nogavice = n.id_nogavice AND n.id_model = m.id_model AND n.id_velikost = v.id_velikost"));
        try {
			while (rezultat.next())
			{
				String model = rezultat.getString("ime");
				String velikost = rezultat.getString("opis");
				String stevilo = rezultat.getString("stevilo");
				imenaPaketov.add(new ItemPair(rezultat.getInt("id_paket"), String.format("%s %s %sx", model, velikost, stevilo)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaPaketov;
	}
	
	public Vector<ItemPair> izberiPotnike()
	{
		Vector<ItemPair> imenaPotnikov = new Vector<ItemPair>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_potnik, ime FROM potnik"));
        try {
			while (rezultat.next())
			{
				imenaPotnikov.add(new ItemPair(rezultat.getInt("id_potnik"), rezultat.getString("ime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaPotnikov;
	}
	
	public Vector<String[]> pokaziArtikle()
	{
		Vector<String[]> artikli = new Vector<String[]>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT m.ime, v.opis, n.kolicina "
				+ "FROM model m, velikost v, nogavice n "
				+ "WHERE n.id_model = m.id_model and n.id_velikost = v.id_velikost"));

        try {
			while (rezultat.next())
			{
				String ime = rezultat.getString("ime");
				String opis = rezultat.getString("opis");
				String kolicina = rezultat.getString("kolicina");
				artikli.add(new String[] { ime, opis, kolicina });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artikli;
	}
	
	public Vector<String[]> pokaziPakete()
	{
		Vector<String[]> artikli = new Vector<String[]>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT m.ime, v.opis, p.stevilo, p.kolicina "
				+ "FROM paket p, model m, velikost v, nogavice n "
				+ "WHERE p.id_nogavice = n.id_nogavice and n.id_model = m.id_model and n.id_velikost = v.id_velikost"));

        try {
			while (rezultat.next())
			{
				String stevilo = rezultat.getString("stevilo");
				String model = rezultat.getString("ime");
				String velikost = rezultat.getString("opis");
				String kolicina = rezultat.getString("kolicina");
				artikli.add(new String[] { model, velikost, stevilo, kolicina });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artikli;
	}
	
	public Vector<String[]> pokaziPotnike()
	{
		Vector<String[]> artikli = new Vector<String[]>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT t.id_tovor as sifra, o.ime, m.ime as model, v.opis, p.stevilo, t.kolicina "
				+ "FROM potnik o, tovor t, paket p, nogavice n, model m, velikost v "
				+ "WHERE p.id_paket = t.id_paket and o.id_potnik = t.id_potnik AND "
				+ "p.id_nogavice = n.id_nogavice AND n.id_model = m.id_model AND n.id_velikost = v.id_velikost "
				+ "AND t.kolicina > 0"));

        try {
			while (rezultat.next())
			{
				String id = rezultat.getString("sifra");
				String potnik = rezultat.getString("ime");
				String model = rezultat.getString("model");
				String velikost = rezultat.getString("opis");
				String stevilo = rezultat.getString("stevilo");
				String kolicina = rezultat.getString("kolicina");
				artikli.add(new String[] { id, potnik, String.format("%s %s %sx", model, velikost, stevilo), kolicina });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artikli;
	}
	
	public int dodajArtikel(int model, int velikost, int kolicina)
	{
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_nogavice, kolicina "
				+ "FROM nogavice "
				+ "WHERE id_model = %d AND id_velikost = %d", model, velikost));
		
		try 
		{
			if (rezultat.next())
			{
				int k = rezultat.getInt("kolicina");
				int id = rezultat.getInt("id_nogavice");
				if (k + kolicina < 0)
				{
					System.out.println("ni dovolj zaloge");
					return RESULT_NI_ZALOGE;
				}
				dodajObstojecArtikel(id, k, kolicina);
			}
			else
			{
				if (kolicina < 0)
				{
					System.out.println("ni dovolj zaloge");
					return RESULT_NI_ZALOGE;
				}					
				dodajNovArtikel(model, velikost, kolicina);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RESULT_OK;
	}
	
	public int dodajPaket(int model, int velikost, int stevilo, int kolicina)
	{
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_nogavice, kolicina "
				+ "FROM nogavice "
				+ "WHERE id_model = %d AND id_velikost = %d", model, velikost));
		
		try 
		{
			if (rezultat.next())
			{
				int k = rezultat.getInt("kolicina");
				int id = rezultat.getInt("id_nogavice");
				if (k < kolicina * stevilo)
				{
					System.out.print("ni dovolj zaloge");
					return RESULT_NI_ZALOGE;
				}
				
				ResultSet rezultat1 = izberiIzBaze(String.format("SELECT id_paket, kolicina "
						+ "FROM paket "
						+ "WHERE id_nogavice = %d AND stevilo = %d", id, stevilo));
				
				if (rezultat1.next())
				{
					int k1 = rezultat1.getInt("kolicina");
					int id1 = rezultat1.getInt("id_paket");
					dodajObstojecPaket(id1, k1, kolicina);
				}
				else
				{
					dodajNovPaket(stevilo, kolicina, id);
				}
				dodajObstojecArtikel(id, k, - kolicina * stevilo);
			}
			else
			{
				System.out.println("Model ne obstaja");
				return RESULT_NI_MODELA;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RESULT_OK;
	}
	
	public int dodajTovor(int potnik, int paket, int kolicina)
	{
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_paket, kolicina "
				+ "FROM paket "
				+ "WHERE id_paket = %d", paket));
		
		try 
		{
			if (rezultat.next())
			{
				int id = rezultat.getInt("id_paket");
				int k = rezultat.getInt("kolicina");
				if (k < kolicina)
				{
					System.out.print("ni dovolj zaloge");
					return RESULT_NI_ZALOGE;
				}
				
				ResultSet rezultat1 = izberiIzBaze(String.format("SELECT id_tovor, kolicina "
						+ "FROM tovor "
						+ "WHERE id_paket = %d AND id_potnik = %d", id, potnik));
				
				if (rezultat1.next())
				{
					int k1 = rezultat1.getInt("kolicina");
					int id1 = rezultat1.getInt("id_tovor");
					if ( k1 + kolicina < 0)
					{
						System.out.println("ni dovolj zaloge");
						return RESULT_NI_ZALOGE;
					}
					dodajObstojecTovor(id1, k1, kolicina);
				}
				else
				{
					if (kolicina < 0)
					{
						System.out.println("ni dovolj zaloge");
						return RESULT_NI_ZALOGE;
					}
					dodajNovTovor(potnik, id, kolicina);					
				}
				dodajObstojecPaket(id, k, - kolicina);
			}
			else
			{
				System.out.println("Paket ne obstaja");
				return RESULT_NI_PAKETA;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RESULT_OK;
	}
	
	public int dodajObstojecTovor(int tovor, int zaloga, int dodano)
	{
		if (zaloga + dodano < 0)
		{
			System.out.println("Ni dovolj zaloge!");
			return RESULT_NI_ZALOGE;
		}
		posodobiBazo(String.format("UPDATE tovor SET kolicina = %d WHERE id_tovor = %d", zaloga + dodano, tovor));
		return RESULT_OK;
	}
	
	private void dodajNovTovor(int potnik, int paket, int kolicina) 
	{
		posodobiBazo(String.format("INSERT INTO tovor (id_potnik, id_paket, kolicina) VALUES (%d, %d, %d);", potnik, paket, kolicina));
	}

	public void dodajPotnika(String ime)
	{
		posodobiBazo(String.format("INSERT INTO potnik (ime) VALUES ('%s');", ime));
	}
	
	public void dodajModel(String ime)
	{
		posodobiBazo(String.format("INSERT INTO model (ime, id_barva, id_vzorec) VALUES ('%s', null, null);", ime));
	}
	
	public void dodajNovoVelikost(int model, int v1, int v2)
	{
		posodobiBazo(String.format("INSERT INTO nogavice (id_model, velikost_od, velikost_do, kolicina) VALUES (%d, %d, %d, %d);", model, v1, v2, 0));
	}
	
	public int dodajObstojecPaket(int paket, int zaloga, int dodano)
	{
		if (zaloga + dodano < 0)
		{
			System.out.println("Ni dovolj zaloge!");
			return RESULT_NI_ZALOGE;
		}
		posodobiBazo(String.format("UPDATE paket SET kolicina = %d WHERE id_paket = %d", zaloga + dodano, paket));
		return RESULT_OK;
	}
	
	public void dodajNovPaket(int stevilo, int kolicina, int nogavice)
	{
		posodobiBazo(String.format("INSERT INTO paket (naziv, stevilo, kolicina, id_nogavice) VALUES (null, %d, %d, %d);", stevilo, kolicina, nogavice));
	}	
	
	public void dodajNovArtikel(int model, int velikost, int kolicina)
	{
		posodobiBazo(String.format("INSERT INTO nogavice (id_model, id_velikost, kolicina) VALUES (%d, %d, %d);", model, velikost, kolicina));
	}
	
	public int dodajObstojecArtikel(int id, int zaloga, int dodano)
	{
		if (zaloga + dodano < 0)
		{
			System.out.println("Ni dovolj zaloge!");
			return RESULT_NI_ZALOGE;
		}
		posodobiBazo(String.format("UPDATE nogavice SET kolicina = %d WHERE id_nogavice = %d", zaloga + dodano, id));
		return RESULT_OK;
	}
	
	public int izbrisiModel(int model)
	{
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_nogavice "
				+ "FROM nogavice "
				+ "WHERE id_model = %d", model));
		try
		{
			if (rezultat.next())
			{
				return RESULT_V_UPORABI;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		posodobiBazo(String.format("DELETE FROM model WHERE id_model = %d", model));
		return RESULT_OK;
	}
	
	public int izbrisiPotnika(int potnik)
	{
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id_tovor "
				+ "FROM tovor "
				+ "WHERE id_potnik = %d", potnik));
		try
		{
			if (rezultat.next())
			{
				return RESULT_V_UPORABI;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		posodobiBazo(String.format("DELETE FROM potnik WHERE id_potnik = %d", potnik));
		return RESULT_OK;
	}
	
	public void izbrisiArtikle(int artikel)
	{
		posodobiBazo(String.format("DELETE FROM nogavice WHERE id_nogavice = %d", artikel));
	}
	
	public void izbrisiPakete(int paket)
	{
		posodobiBazo(String.format("DELETE FROM paket WHERE id_paket = %d", paket));
	}
	
	public void izbrisiTovore(int tovor)
	{
		posodobiBazo(String.format("DELETE FROM tovor WHERE id_tovor = %d", tovor));
	}
	
	public ResultSet izberiIzBaze(String sqlUkaz)
	{
		ResultSet result = null;
		try
		{
			stmt = c.createStatement();
			result = stmt.executeQuery(sqlUkaz);
		}
		catch (Exception e)
		{
			System.err.println(e);
			System.exit(0);
		}
		return result;
	}
	
	public void posodobiBazo(String sqlUkaz)
	{
		try
		{
			stmt = c.createStatement();
			stmt.executeUpdate(sqlUkaz);
		}
		catch (Exception e)
		{
			System.err.println(e);
			System.exit(0);
		}	
	}
	
	public void zapri()
	{
		try {
			stmt.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int kreirajBazo()
	{
		try
		{/*
			File f = new File("nogavice.db");
			if (f.exists())
			{
				return 1;
			}
			*/
			stmt = c.createStatement();			
			
			String sqlUkaz = "CREATE TABLE Nogavice (\r\n" + 
					"	id_nogavice integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	id_model integer,\r\n" + 
					"	id_velikost integer,\r\n" + 
					"	kolicina integer\r\n" + 
					");\r\n" + 
					"\r\n" + 
					"CREATE TABLE Model (\r\n" + 
					"	id_model integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	ime string,\r\n" + 
					"	id_barva integer,\r\n" + 
					"	id_vzorec integer\r\n" + 
					");\r\n" + 
					"\r\n" + 
					"CREATE TABLE Barva (\r\n" + 
					"	id_barva integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	ime string,\r\n" + 
					"	koda string\r\n" + 
					");\r\n" + 
					"\r\n" + 
					"CREATE TABLE Vzorec (\r\n" + 
					"	id_vzorec integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	opis string,\r\n" + 
					"	slika blob\r\n" + 
					");\r\n" + 
					"\r\n" + 
					"CREATE TABLE Paket (\r\n" + 
					"	id_paket integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	naziv string,\r\n" + 
					"	stevilo integer,\r\n" + 
					"	kolicina integer,\r\n" + 
					"	id_nogavice integer\r\n" + 
					");\r\n" + 
					"\r\n" + 
					"CREATE TABLE Potnik (\r\n" + 
					"	id_potnik integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	ime string\r\n" + 
					");\r\n" + 
					"\r\n" + 
					"CREATE TABLE Tovor (\r\n" + 
					"	id_tovor integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	id_potnik integer,\r\n" + 
					"	id_paket integer,\r\n" + 
					"	kolicina integer\r\n" + 
					");\r\n" + 
					"\r\n" + 
					"CREATE TABLE Velikost (\r\n" + 
					"	id_velikost integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	od integer,\r\n" + 
					"	do integer,\r\n" + 
					"	opis string\r\n" + 
					");\r\n" + 
					"";
			
			stmt.executeUpdate(sqlUkaz);
			
			for (int i = 38; i < 48; i++)
			{
				posodobiBazo(String.format("INSERT INTO velikost (od, do, opis) VALUES (%d, %d, %s);", i, i+1, "'"+i+"/"+(i+1)+"'"));
			}
			
		}
		catch (Exception e)
		{
			System.err.println(e);
			System.exit(0);
		}
		return RESULT_OK;		
	}
	
}
