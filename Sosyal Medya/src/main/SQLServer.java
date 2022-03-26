package main;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import models.Arkadas;
import models.Kullanici;
import models.Mesaj;
import models.Post;

public class SQLServer {

	private static String dbName;
	private static String dbUser;
	private static String dbPassword;
	private static String connectionUrl;

	private static Connection con;
	private static Statement stmt;
	private static PreparedStatement ps;

//	private static Kullanici aktif;

	private static final Image defaultHesapFotosu = new Image("resources/images/user.png");

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------dbConnect--------------------------------------------------------------

	public static boolean dbConnect() {

		connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=" + dbName + ";user=" + dbUser + ";password="
				+ dbPassword;

		try {
			con = DriverManager.getConnection(connectionUrl);
			stmt = con.createStatement();
			return true;
		} catch (SQLException ex) {

			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------addBegeni---------------------------------------------------------------

	public static boolean addBegeni(String KullaniciID, String PostID) {

		try {
			ps = con.prepareStatement(
					"INSERT INTO Begeniler (PostID,KullaniciID,Tarih) VALUES(?,?,CAST( GETDATE() AS Date )) ");
			ps.setString(1, PostID);
			ps.setString(2, KullaniciID);
			ps.executeUpdate();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	--------------------------------------------------------------arkadsKabul------------------------------------------------------------

	public static boolean arkadsKabul(String KullaniciID, String aktif) {

		try {
			ps = con.prepareStatement(
					"UPDATE Arkadaslar SET Durum = 'KABUL' WHERE (TalepEdenID = ? AND TalepEdilenID = ?) OR (TalepEdilenID = ? AND TalepEdenID = ?)");
			ps.setString(1, KullaniciID);
			ps.setString(2, aktif);
			ps.setString(3, KullaniciID);
			ps.setString(4, aktif);
			ps.executeUpdate();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------arkadsEkle---------------------------------------------------------------

	public static boolean arkadsEkle(String KullaniciID, String aktif) {

		try {
			ps = con.prepareStatement(
					"INSERT INTO ARKADASLAR (ArkadaslikID,TalepEdenID,TalepEdilenID,Tarih,Durum) VALUES(?,?,?,CAST( GETDATE() AS Date ),'BEKLIYOR')");
			ps.setString(1, aktif + KullaniciID);
			ps.setString(2, aktif);
			ps.setString(3, KullaniciID);
			ps.executeUpdate();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------arkadsRed---------------------------------------------------------------

	public static boolean arkadsRed(String KullaniciID, String aktif) {

		try {
			ps = con.prepareStatement(
					"UPDATE Arkadaslar SET Durum = 'RED' WHERE (TalepEdenID = ? AND TalepEdilenID = ?) OR (TalepEdilenID = ? AND TalepEdenID = ?)");
			ps.setString(1, KullaniciID);
			ps.setString(2, aktif);
			ps.setString(3, KullaniciID);
			ps.setString(4, aktif);
			ps.executeUpdate();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------arkadsSil---------------------------------------------------------------

	public static boolean arkadsSil(String KullaniciID, String aktif) {

		try {
			ps = con.prepareStatement(
					"DELETE FROM Arkadaslar WHERE (TalepEdenID = ? AND TalepEdilenID = ?) OR (TalepEdilenID = ? AND TalepEdenID = ?)");
			ps.setString(1, KullaniciID);
			ps.setString(2, aktif);
			ps.setString(3, KullaniciID);
			ps.setString(4, aktif);
			ps.executeUpdate();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------begenildiMi-----------------------------------------------------------------

	public static boolean begenildiMi(String KullaniciID, String PostID) {

		ResultSet rs;

		try {

			ps = con.prepareStatement("SELECT TOP 1 1 FROM Begeniler WHERE KullaniciID = ? AND PostID = ?");
			ps.setString(1, KullaniciID);
			ps.setString(2, PostID);
			rs = ps.executeQuery();

			return rs.isBeforeFirst();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------checkAdmin-------------------------------------------------------------

	public static boolean checkAdmin(String KullaniciID) {

		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT TOP 1 1 FROM Adminler WHERE KullaniciID = ?");
			ps.setString(1, KullaniciID);
			rs = ps.executeQuery();

			return rs.isBeforeFirst();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------checkAdminSifre-------------------------------------------------------------

	public static boolean checkAdminSifre(String KullaniciID, String Sifre) {

		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT TOP 1 1 FROM Adminler WHERE KullaniciID = ? AND Sifre = ?");
			ps.setString(1, KullaniciID);
			ps.setString(2, Sifre);
			rs = ps.executeQuery();

			return rs.isBeforeFirst();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------checkKullaniciID-------------------------------------------------------

	public static boolean checkKullaniciID(String KullaniciID) {

		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT TOP 1 1 FROM Kullanicilar WHERE KullaniciID = ?");
			ps.setString(1, KullaniciID);
			rs = ps.executeQuery();

			return rs.isBeforeFirst();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------checkKullaniciSifre---------------------------------------------------------

	public static boolean checkKullaniciSifre(String KullaniciID, String Sifre) {

		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT TOP 1 1 FROM Kullanicilar WHERE KullaniciID = ? AND Sifre = ?");
			ps.setString(1, KullaniciID);
			ps.setString(2, Sifre);
			rs = ps.executeQuery();

			return rs.isBeforeFirst();

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------deleteBegeni------------------------------------------------------------

	public static boolean deleteBegeni(String KullaniciID, String PostID) {

		try {
			ps = con.prepareStatement("DELETE FROM Begeniler WHERE KullaniciID = ? AND PostID = ? ");
			ps.setString(1, KullaniciID);
			ps.setString(2, PostID);
			ps.executeUpdate();

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}
//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------hesapEkle-------------------------------------------------------------------

	public static boolean hesapEkle(String KullaniciID, String Ad, String Soyad, String Sifre,
			java.sql.Date dogumTarihi, String Telefon, InputStream Foto, int Cinisyet) {

		try {
			ps = con.prepareStatement(
					"INSERT INTO Kullanicilar (KullaniciID,Ad,Soyad,Telefon,Sifre,DogumTarihi,Cinsiyet,Foto) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, KullaniciID);
			ps.setString(2, Ad);
			ps.setString(3, Soyad);
			ps.setString(4, Telefon);
			ps.setString(5, Sifre);
			ps.setDate(6, dogumTarihi);
			ps.setInt(7, Cinisyet);

			if (Foto != null)
				ps.setBinaryStream(8, Foto, Foto.available());
			else
				ps.setBinaryStream(8, null, 0);

			ps.executeUpdate();
			return true;

		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------getArkadaslar------------------------------------------------------

//	public static List<Arkadas> getArkadaslar(String aktif) {
//
//		Image f;
//		ResultSet rs;
//		List<Arkadas> arkadaslar = new ArrayList<>();
//
//		try {
//
//			ps = con.prepareStatement(
//					"SELECT KullaniciID,Foto FROM Kullanicilar WHERE KullaniciID IN ((SELECT TalepEdilenID FROM Arkadaslar WHERE TalepEdenID = ? AND Durum = 'KABUL') UNION (SELECT TalepEdenID FROM Arkadaslar WHERE TalepEdilenID =  ? AND Durum = 'KABUL' )) ");
//			ps.setString(1, aktif);
//			ps.setString(2, aktif);
//
//			rs = ps.executeQuery();
//
//			while (rs.next()) {
//
//				try {
//					f = new Image(rs.getBinaryStream("Foto"));
//				} catch (NullPointerException ex) {
//					f = defaultHesapFotosu;
//				}
//
//				arkadaslar.add(new Arkadas(f, rs.getString("KullaniciID")));
//
//			}
//
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//
//		return arkadaslar;
//
//	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getArkadaslar----------------------------------------------------------

	public static ObservableList<Arkadas> getArkadaslar(String aktif) {

		ObservableList<Arkadas> ls = FXCollections.observableArrayList();
		ResultSet rs;
		Image f = null;

		try {

			ps = con.prepareStatement(
					"SELECT KullaniciID,Tarih,Foto FROM ( ((SELECT KullaniciID,Foto FROM Kullanicilar) AS K INNER JOIN (SELECT TalepEdenID,TalepEdilenID,Tarih FROM Arkadaslar WHERE DURUM = 'KABUL' AND (TalepEdenID =  ? OR TalepEdilenID = ? ) ) AS ark ON (K.KullaniciID = ark.TalepEdenID OR K.KullaniciID = ark.TalepEdilenID ) ) ) WHERE KullaniciID <> ?");
			ps.setString(1, aktif);
			ps.setString(2, aktif);
			ps.setString(3, aktif);
			rs = ps.executeQuery();

			while (rs.next()) {

				java.util.Date tarih = new java.util.Date(rs.getDate("Tarih").getTime());
				LocalDate Tarih = tarih.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				try {
					f = new Image(rs.getBinaryStream("Foto"));
				} catch (NullPointerException ex) {
					f = defaultHesapFotosu;
				}

				Arkadas a = new Arkadas(f, rs.getString("KullaniciID"), Tarih);

				ls.add(a);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ls;
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getKullanicilar--------------------------------------------------------

	public static ObservableList<Kullanici> getKullanicilar() {

		ObservableList<Kullanici> ls = FXCollections.observableArrayList();
		ResultSet rs;
		Image f;

		try {

			String getKullanicilar = "SELECT * FROM Kullanicilar";

			rs = stmt.executeQuery(getKullanicilar);

			while (rs.next()) {

				java.util.Date dogumTarihi = new java.util.Date(rs.getDate("DogumTarihi").getTime());
				LocalDate LocalDogumTarihi = dogumTarihi.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				try {
					f = new Image(rs.getBinaryStream("Foto"));
				} catch (NullPointerException ex) {
					f = defaultHesapFotosu;
				}

				Kullanici kullanici = new Kullanici(rs.getString("KullaniciID"), rs.getString("Ad"),
						rs.getString("Soyad"), LocalDogumTarihi, rs.getString("Telefon"), f, rs.getInt("Cinsiyet"),
						rs.getInt("PostSayisi"), rs.getInt("TakipSayisi"), rs.getInt("ArkadasSayisi"));

				ls.add(kullanici);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ls;
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getKullanici-----------------------------------------------------------

	public static Kullanici getKullanici(String KullaniciID) {
		Kullanici kullanici = null;
		ResultSet rs;
		Image f;

		try {

			ps = con.prepareStatement("SELECT * FROM Kullanicilar WHERE KullaniciID = ?");
			ps.setString(1, KullaniciID);

			rs = ps.executeQuery();

			rs.next();

			java.util.Date dogumTarihi = new java.util.Date(rs.getDate("DogumTarihi").getTime());
			LocalDate LocalDogumTarihi = dogumTarihi.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			try {
				f = new Image(rs.getBinaryStream("Foto"));
			} catch (NullPointerException ex) {
				f = defaultHesapFotosu;
			}

			kullanici = new Kullanici(rs.getString("KullaniciID"), rs.getString("Ad"), rs.getString("Soyad"),
					LocalDogumTarihi, rs.getString("Telefon"), f, rs.getInt("Cinsiyet"), rs.getInt("PostSayisi"),
					rs.getInt("TakipSayisi"), rs.getInt("ArkadasSayisi"));

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return kullanici;
	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getPostlar------------------------------------------------------------------

	public static List<Post> getPostlar(String aktif) {

		Image f;
		Image KullaniciFoto;
		boolean begenildiMi;
		ResultSet rs;
		List<Post> postlar = new ArrayList<>();

		try {

			ps = con.prepareStatement(
					"SELECT * FROM Postlar WHERE KullaniciID IN  ((SELECT TalepEdilenID FROM Arkadaslar WHERE TalepEdenID = ? AND Durum = 'KABUL') UNION (SELECT TalepEdenID FROM Arkadaslar WHERE TalepEdilenID = ? AND Durum = 'KABUL' ))");
			ps.setString(1, aktif);
			ps.setString(2, aktif);
			rs = ps.executeQuery();

			while (rs.next()) {

				java.util.Date dogumTarihi = new java.util.Date(rs.getDate("Tarih").getTime());
				LocalDate LocalDogumTarihi = dogumTarihi.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				try {
					f = new Image(rs.getBinaryStream("Foto"));
				} catch (NullPointerException ex) {
					f = null;
				}

				KullaniciFoto = getKullaniciFoto(rs.getString("KullaniciID"));
				begenildiMi = begenildiMi(main.Start.AktifKullanici.getKullaniciAdi(), rs.getString("PostID"));

				Post p = new Post(rs.getString("PostID"), rs.getString("KullaniciID"), LocalDogumTarihi,
						rs.getString("Metin"), f, KullaniciFoto, rs.getInt("BegeniSayisi"), begenildiMi);

				postlar.add(p);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return postlar;

	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getMesajlar----------------------------------------------------------

	public static ObservableList<Mesaj> getMesajlar(String aktif, String ArkadasID) {

		ObservableList<Mesaj> ls = FXCollections.observableArrayList();
		ResultSet rs;
		Image f = null;

		try {

			ps = con.prepareStatement(
					"SELECT MesajID,GonderenID,AlanID,Metin FROM Mesajlar WHERE (GonderenID = ? AND AlanID = ?) OR (GonderenID = ? AND AlanID = ?)");
			ps.setString(1, aktif);
			ps.setString(2, ArkadasID);
			ps.setString(3, ArkadasID);
			ps.setString(4, aktif);
			rs = ps.executeQuery();

			while (rs.next()) {

				if (rs.getString("GonderenID").equals(aktif)) {
					f = SQLServer.getKullaniciFoto(aktif);
				} else {
					f = SQLServer.getKullaniciFoto(ArkadasID);
				}

				Mesaj a = new Mesaj(rs.getString("MesajID"), rs.getString("GonderenID"), rs.getString("AlanID"),
						rs.getString("Metin"), f);

				ls.add(a);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ls;
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getTalepler------------------------------------------------------------

	public static ObservableList<Arkadas> getTalepler(String aktif) {

		ObservableList<Arkadas> ls = FXCollections.observableArrayList();
		ResultSet rs;
		Image f = null;

		try {

			ps = con.prepareStatement(
					"SELECT KullaniciID,Tarih,Foto,ArkadaslikID,Durum FROM ( ((SELECT KullaniciID,Foto FROM Kullanicilar) AS K INNER JOIN (SELECT ArkadaslikID,TalepEdenID,TalepEdilenID,Tarih,Durum FROM Arkadaslar WHERE DURUM <> 'KABUL' AND (TalepEdenID =  ? OR TalepEdilenID = ? ) ) AS ark ON (K.KullaniciID = ark.TalepEdenID OR K.KullaniciID = ark.TalepEdilenID ) ) ) WHERE KullaniciID <> ?");
			ps.setString(1, aktif);
			ps.setString(2, aktif);
			ps.setString(3, aktif);
			rs = ps.executeQuery();

			while (rs.next()) {

				java.util.Date tarih = new java.util.Date(rs.getDate("Tarih").getTime());
				LocalDate Tarih = tarih.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

				try {
					f = new Image(rs.getBinaryStream("Foto"));
				} catch (NullPointerException ex) {
					f = defaultHesapFotosu;
				}

				Arkadas a = new Arkadas(f, rs.getString("KullaniciID"), Tarih, rs.getString("Durum"));
				a.setArkadaslikID(rs.getString("ArkadaslikID"));

				ls.add(a);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ls;
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getArama---------------------------------------------------------------

	public static ObservableList<Arkadas> getArama(String aktif) {

		ObservableList<Arkadas> ls = FXCollections.observableArrayList();
		ResultSet rs;
		Image f = null;

		try {

			ps = con.prepareStatement(
					"SELECT KullaniciID,Foto FROM Kullanicilar WHERE KullaniciID NOT IN (SELECT TalepEdenID FROM Arkadaslar WHERE TalepEdilenID = ? UNION SELECT  TalepEdilenID FROM Arkadaslar WHERE TalepEdenID = ? ) AND KullaniciID <> ?");
			ps.setString(1, aktif);
			ps.setString(2, aktif);
			ps.setString(3, aktif);
			rs = ps.executeQuery();

			while (rs.next()) {

				try {
					f = new Image(rs.getBinaryStream("Foto"));
				} catch (NullPointerException ex) {
					f = defaultHesapFotosu;
				}
				Arkadas a = new Arkadas(f, rs.getString("KullaniciID"));
				ls.add(a);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ls;
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------Ara--------------------------------------------------------------------

	public static ObservableList<Arkadas> Ara(String aranan, String aktif) {

		ObservableList<Arkadas> ls = FXCollections.observableArrayList();
		ResultSet rs;
		Image f = null;

		try {

			ps = con.prepareStatement(
					"SELECT KullaniciID,Foto FROM Kullanicilar WHERE KullaniciID LIKE ('%' + ? + '%') AND KullaniciID <> ?");
			ps.setString(1, aranan);
			ps.setString(2, aktif);
			rs = ps.executeQuery();

			while (rs.next()) {

				try {
					f = new Image(rs.getBinaryStream("Foto"));
				} catch (NullPointerException ex) {
					f = defaultHesapFotosu;
				}
				Arkadas a = new Arkadas(f, rs.getString("KullaniciID"));
				ls.add(a);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return ls;
	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------getKullaniciFoto------------------------------------------------------------

	public static Image getKullaniciFoto(String KullaniciID) {

		Image f;
		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT Foto FROM Kullanicilar WHERE KullaniciID = ?");
			ps.setString(1, KullaniciID);
			rs = ps.executeQuery();
			rs.next();

			try {
				f = new Image(rs.getBinaryStream("Foto"));
			} catch (NullPointerException | SQLException ex) {
				f = defaultHesapFotosu;
			}

			return f;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return defaultHesapFotosu;
		}
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------KullaniciSil-----------------------------------------------------------

	public static boolean KullaniciSil(String KullaniciID) {

		try {
			ps = con.prepareStatement("DELETE FROM Kullanicilar WHERE KullaniciID = ?");
			ps.setString(1, KullaniciID);
			ps.executeUpdate();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------postEkle----------------------------------------------------------------

	public static boolean postEkle(String KullaniciID, String PostID, String Metin, InputStream Foto) {

		try {
			ps = con.prepareStatement(
					"INSERT INTO Postlar (PostID,KullaniciID,Tarih,Metin,Foto) VALUES(?,?,CAST( GETDATE() AS Date ),?,?) ");
			ps.setString(1, PostID);
			ps.setString(2, KullaniciID);
			ps.setString(3, Metin);
			ps.setBinaryStream(4, Foto);
			ps.executeUpdate();

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------mesajEkle----------------------------------------------------------------

	public static boolean mesajEkle(Mesaj m) {

		try {
			ps = con.prepareStatement(
					"INSERT INTO Mesajlar (MesajID,GonderenID,AlanID,Zaman,Metin) VALUES(? + CAST(GETDATE() AS NVARCHAR(50)),?,?,GETDATE(),?) ");
			ps.setString(1, m.getGonderenID() + m.getAlanID());
			ps.setString(2, m.getGonderenID());
			ps.setString(3, m.getAlanID());
			ps.setString(4, m.getMetin());
			ps.executeUpdate();

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------updateAdminSifre------------------------------------------------------------

	public static boolean updateAdminSifre(String KullaniciID, String Sifre) {

		try {
			ps = con.prepareStatement("UPDATE Adminler SET Sifre = ?  WHERE KullaniciID = ? ");
			ps.setString(2, KullaniciID);
			ps.setString(1, Sifre);
			ps.executeUpdate();

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------updateKullanici--------------------------------------------------------

	public static boolean updateKullanici(String KullaniciID, String updatedKullaniciID, String Ad, String Soyad,
			String Telefon, InputStream Foto) {

		try {

			if (Foto == null) {
				ps = con.prepareStatement(
						"UPDATE Kullanicilar SET KullaniciID = ?,Ad = ?,Soyad = ?,Telefon = ? WHERE KullaniciID = ?");

				ps.setString(1, updatedKullaniciID);
				ps.setString(2, Ad);
				ps.setString(3, Soyad);
				ps.setString(4, Telefon);
				ps.setString(5, KullaniciID);

				ps.executeUpdate();

			} else {
				ps = con.prepareStatement(
						"UPDATE Kullanicilar SET KullaniciID = ?,Ad = ?,Soyad = ?,Telefon = ?,Foto = ? WHERE KullaniciID = ?");

				ps.setString(1, updatedKullaniciID);
				ps.setString(2, Ad);
				ps.setString(3, Soyad);
				ps.setString(4, Telefon);
				ps.setBinaryStream(5, Foto, Foto.available());
				ps.setString(6, KullaniciID);

				ps.executeUpdate();

			}

			return true;

		} catch (SQLException | IOException ex) {
			ex.printStackTrace();
			return false;
		}
	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------updateKullaniciSifre--------------------------------------------------------

	public static boolean updateKullaniciSifre(String KullaniciID, String Sifre) {

		try {
			ps = con.prepareStatement("UPDATE Kullanicilar SET Sifre = ?  WHERE KullaniciID = ? ");
			ps.setString(1, Sifre);
			ps.setString(2, KullaniciID);
			ps.executeUpdate();

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------validateAdmin---------------------------------------------------------------

	public static boolean validateAdmin(String KullaniciID, String Ad, String Soyad, LocalDate DogumTarihi) {

		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT * FROM Adminler WHERE KullaniciID = ?");
			ps.setString(1, KullaniciID);
			rs = ps.executeQuery();
			rs.next();

			java.util.Date dTarihi = new java.util.Date(rs.getDate("DogumTarihi").getTime());
			LocalDate LocalDogumTarihi = dTarihi.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if (!Ad.equals(rs.getString("Ad")) || !Soyad.equals(rs.getString("Soyad"))
					|| !LocalDogumTarihi.equals(DogumTarihi)) {
				return false;
			}

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}

	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------validateKullanici-----------------------------------------------------------

	public static boolean validateKullanici(String KullaniciID, String Ad, String Soyad, LocalDate DogumTarihi) {

		ResultSet rs;

		try {
			ps = con.prepareStatement("SELECT * FROM Kullanicilar WHERE KullaniciID = ?");
			ps.setString(1, KullaniciID);
			rs = ps.executeQuery();
			rs.next();

			java.util.Date dTarihi = new java.util.Date(rs.getDate("DogumTarihi").getTime());
			LocalDate LocalDogumTarihi = dTarihi.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if (!Ad.equals(rs.getString("Ad")) || !Soyad.equals(rs.getString("Soyad"))
					|| !LocalDogumTarihi.equals(DogumTarihi)) {
				return false;
			}

			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

//	----------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------Getters&Setters---------------------------------------------------------

	public static String getDbName() {
		return dbName;
	}

	public static void setDbName(String dbName) {
		SQLServer.dbName = dbName;
	}

	public static String getDbUser() {
		return dbUser;
	}

	public static void setDbUser(String dbUser) {
		SQLServer.dbUser = dbUser;
	}

	public static String getDbPassword() {
		return dbPassword;
	}

	public static void setDbPassword(String dbPassword) {
		SQLServer.dbPassword = dbPassword;
	}

//	************************************************************OVER*******************************************************************	
}
