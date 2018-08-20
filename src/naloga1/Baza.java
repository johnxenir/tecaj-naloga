package naloga1;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;


public class Baza 
{
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
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id, ime FROM model"));
        try {
			while (rezultat.next())
			{
				imenaModelov.add(new ItemPair(rezultat.getInt("id"), rezultat.getString("ime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaModelov;
	}
	
	public Vector<ItemPair> izberiVelikosti()
	{
		Vector<ItemPair> imenaModelov = new Vector<ItemPair>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id, ime FROM model"));
        try {
			while (rezultat.next())
			{
				imenaModelov.add(new ItemPair(rezultat.getInt("id"), rezultat.getString("ime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaModelov;
	}
	
	public Vector<ItemPair> izberiPakete()
	{
		Vector<ItemPair> imenaModelov = new Vector<ItemPair>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id, naziv FROM paket"));
        try {
			while (rezultat.next())
			{
				imenaModelov.add(new ItemPair(rezultat.getInt("id"), rezultat.getString("naziv")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaModelov;
	}
	
	public Vector<ItemPair> izberiPotnike()
	{
		Vector<ItemPair> imenaModelov = new Vector<ItemPair>();
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id, ime FROM potnik"));
        try {
			while (rezultat.next())
			{
				imenaModelov.add(new ItemPair(rezultat.getInt("id"), rezultat.getString("ime")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imenaModelov;
	}	
	
	public void dodajArtikel(int model, int velikost, int kolicina)
	{
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id, kolicina "
				+ "FROM nogavica "
				+ "WHERE id_model = %d AND id_velikost = %d", model, velikost));
		
		try 
		{
			if (rezultat.next())
			{
				int k = rezultat.getInt("kolicina");
				int id = rezultat.getInt("id");
				dodajOdstraniArtikle(id, k, kolicina);
			}
			else
			{
				dodajNovArtikel(model, velikost, kolicina);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void dodajPaket(int model, int velikost, String naziv, int stevilo, int kolicina)
	{
		ResultSet rezultat = izberiIzBaze(String.format("SELECT id, kolicina "
				+ "FROM nogavica "
				+ "WHERE id_model = %d AND id_velikost = %d", model, velikost));
		
		try 
		{
			if (rezultat.next())
			{
				int k = rezultat.getInt("kolicina");
				int id = rezultat.getInt("id");
				if (k < kolicina)
				{
					System.out.print("ni dovolj zaloge");
					return;
				}
				
				ResultSet rezultat1 = izberiIzBaze(String.format("SELECT id, kolicina "
						+ "FROM paket "
						+ "WHERE id_nogavice = %d", id));
				
				if (rezultat1.next())
				{
					int k1 = rezultat1.getInt("kolicina");
					int id1 = rezultat.getInt("id");
					dodajObstojecPaket(id1, k1, kolicina);
				}
				else
				{
					dodajOdstraniArtikle(id, k, -kolicina);
				}
			}
			else
			{
				//dodajNovPaket(model, velikost, kolicina);
				System.out.println("Model ne obstaja");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void dodajTovor(int potnik, int paket, int kolicina)
	{/*
		ResultSet rezultat = izberiIzBaze(String.format("SELECT kolicina "
				+ "FROM paket "
				+ "WHERE id = %d", paket));
		
		try 
		{
			if (rezultat.next())
			{
				int k = rezultat.getInt("kolicina");
				if (k < kolicina)
				{
					System.out.print("ni dovolj zaloge");
					return;
				}
				dodajObstojecPaket(id, k, kolicina);
				
				dodajOdstraniPakete(id, k, kolicina);
			}
			else
			{
				dodajNovArtikel(model, velikost, kolicina);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/	
	}
	
	public void dodajPotnika(String ime)
	{
		posodobiBazo(String.format("INSERT INTO potnik (ime) VALUES (%s);", ime));
	}
	
	public void dodajNovModel(String ime, int barva, int vzorec)
	{
		posodobiBazo(String.format("INSERT INTO model (ime, id_barva, id_vzorec) VALUES (%s, %d, %d);", ime, barva, vzorec));
	}
	
	public void dodajNovoVelikost(int model, int v1, int v2)
	{
		posodobiBazo(String.format("INSERT INTO nogavice (id_model, velikost_od, velikost_do, kolicina) VALUES (%d, %d, %d, %d);", model, v1, v2, 0));
	}
	
	public void dodajObstojecPaket(int id, int zaloga, int dodano) throws Exception
	{
		if (zaloga + dodano < 0)
		{
			System.out.println("Ni dovolj zaloge!");
			return;
		}
		posodobiBazo(String.format("UPDATE paket SET kolicina = %d WHERE id = %d", zaloga + dodano, id));		
	}
	
	public void dodajNovPaket(String naziv, int stevilo, int kolicina, int nogavice)
	{
		posodobiBazo(String.format("INSERT INTO paket (naziv, stevilo, kolicina, id_nogavice) VALUES (%d, %d, %d, %d);", naziv, stevilo, kolicina, nogavice));
	}	
	
	public void dodajNovArtikel(int model, int velikost, int kolicina)
	{
		posodobiBazo(String.format("INSERT INTO nogavice (id_model, id_velikost, kolicina) VALUES (%d, %d, %d);", model, velikost, kolicina));
	}
	
	public void dodajOdstraniArtikle(int id, int zaloga, int dodano)
	{
		if (zaloga + dodano < 0)
		{
			System.out.println("Ni dovolj zaloge!");
			return;
		}
		posodobiBazo(String.format("UPDATE nogavice SET kolicina = %d WHERE id = %d", zaloga + dodano, id));		
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
	
	public void kreirajBazo()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:nogavice.db");			
			stmt = c.createStatement();
			
			String sqlUkaz = "CREATE TABLE Nogavice (\r\n" + 
					"	id_nogavice integer PRIMARY KEY AUTOINCREMENT,\r\n" + 
					"	id_model integer,\r\n" + 
					"	velikost_od integer,\r\n" + 
					"	velikost_do integer,\r\n" + 
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
					"\r\n";
			stmt.executeUpdate(sqlUkaz);
		}
		catch (Exception e)
		{
			System.err.println(e);
			System.exit(0);
		}
		
	}

}
