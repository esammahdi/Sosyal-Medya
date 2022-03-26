package models;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Post {

	private String sahibi;
	private LocalDate tarih;
	private int begenmeSayisi;
	private int yorumSayisi;
	private String metin;
	private String ID;
	private Image Foto;
	private Image SahibFotosu;
	private boolean BegenildiMi;

	Image defaultHesapFotosu = new Image("resources/images/user.png");

	public Post() {

	}

	public Post(String PostID, String KullaniciAdi, LocalDate Tarih, String Metin, Image Foto, Image SahipFotosu,
			int BegeniSayisi, boolean begenildiMi) {

		this.ID = PostID;
		this.sahibi = KullaniciAdi;
		this.tarih = Tarih;
		this.metin = Metin;
		this.begenmeSayisi = BegeniSayisi;
		this.Foto = Foto;
		this.SahibFotosu = SahipFotosu;
		this.BegenildiMi = begenildiMi;

	}

	public boolean BegenildiMi() {
		return BegenildiMi;
	}

	public String getSahib() {
		return sahibi;
	}

	public void setSahib(String sahib) {
		this.sahibi = sahib;
	}

	public LocalDate getTarih() {
		return tarih;
	}

	public void setTarih(LocalDate tarih) {
		this.tarih = tarih;
	}

	public Image getFoto() {
		return Foto;
	}

	public void setFoto(Image Foto) {
		this.Foto = Foto;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public int getBegenmeSayisi() {
		return begenmeSayisi;
	}

	public void setBegenmeSayisi(int begenmeSayisi) {
		this.begenmeSayisi = begenmeSayisi;
	}

	public int getYorumSayisi() {
		return yorumSayisi;
	}

	public void setYorumSayisi(int yorumSayisi) {
		this.yorumSayisi = yorumSayisi;
	}

	public String getMetin() {
		return metin;
	}

	public void setMetin(String metin) {
		this.metin = metin;
	}

	public Image getSahibFotosu() {
		return SahibFotosu;
	}

	void setSahibFotosu(Image SahibFotosu) {
		this.SahibFotosu = SahibFotosu;
	}

}
