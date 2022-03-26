package models;

import java.time.LocalDateTime;

import javafx.scene.image.Image;

public class Mesaj {

	private String ID;
	private String GonderenID;
	private String AlanID;
	private String Metin;
	private LocalDateTime Zaman;
	private Image Foto;
	private Image SahibFoto;

	public Mesaj(String GonderenID, String AlanID, String Metin, Image SahibFoto) {
		this.setGonderenID(GonderenID);
		this.setAlanID(AlanID);
		this.setMetin(Metin);
		this.setSahibFoto(SahibFoto);
	}

	public Mesaj(String ID, String GonderenID, String AlanID, String Metin, Image SahibFoto) {
		this.setID(ID);
		this.setGonderenID(GonderenID);
		this.setAlanID(AlanID);
		this.setMetin(Metin);
		this.setSahibFoto(SahibFoto);
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getGonderenID() {
		return GonderenID;
	}

	public void setGonderenID(String gonderenID) {
		GonderenID = gonderenID;
	}

	public String getAlanID() {
		return AlanID;
	}

	public void setAlanID(String alanID) {
		AlanID = alanID;
	}

	public String getMetin() {
		return Metin;
	}

	public void setMetin(String metin) {
		Metin = metin;
	}

	public LocalDateTime getZaman() {
		return Zaman;
	}

	public void setZaman(LocalDateTime zaman) {
		Zaman = zaman;
	}

	public Image getFoto() {
		return Foto;
	}

	public void setFoto(Image foto) {
		Foto = foto;
	}

	public Image getSahibFoto() {
		return SahibFoto;
	}

	public void setSahibFoto(Image sahibFoto) {
		SahibFoto = sahibFoto;
	}
}
