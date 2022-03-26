package models;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Kullanici {

	private String ad;
	private String soyad;
	private String kullaniciAdi;
	private int cinsiyyet;
	private int postSayisi;
	private int takipSayisi;
	private int arkadasSayisi;
	private String telefonNo;
	private LocalDate dogumTarihi;
	private Image kisiselFoto;

	public Kullanici(String KullaniciAdi, String ad, String soyad, LocalDate dogumTarihi, String Telefon, Image Foto,
			int cin, int postS, int takipS, int arkadasS) {
		this.ad = ad;
		this.setSoyad(soyad);
		this.kullaniciAdi = KullaniciAdi;
		this.setDogumTarihi(dogumTarihi);
		this.setTelefonNo(Telefon);
		this.kisiselFoto = Foto;
		this.cinsiyyet = cin;
		this.setPostSayisi(postS);
		this.setArkadasSayisi(arkadasS);
		this.setTakipSayisi(takipS);
	}

	public String getKullaniciAdi() {
		return kullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		this.kullaniciAdi = kullaniciAdi;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public Image getFoto() {
		return this.kisiselFoto;

	}

	public void setFoto(Image foto) {
		this.kisiselFoto = foto;
	}

	public int getCinsiyyet() {
		return cinsiyyet;
	}

	public void setCinsiyyet(int cinsiyyet) {
		this.cinsiyyet = cinsiyyet;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public int getPostSayisi() {
		return postSayisi;
	}

	public void setPostSayisi(int postSayisi) {
		this.postSayisi = postSayisi;
	}

	public int getTakipSayisi() {
		return takipSayisi;
	}

	public void setTakipSayisi(int takipSayisi) {
		this.takipSayisi = takipSayisi;
	}

	public String getTelefonNo() {
		return telefonNo;
	}

	public void setTelefonNo(String telefonNo) {
		this.telefonNo = telefonNo;
	}

	public int getArkadasSayisi() {
		return arkadasSayisi;
	}

	public void setArkadasSayisi(int arkadasSayisi) {
		this.arkadasSayisi = arkadasSayisi;
	}

	public LocalDate getDogumTarihi() {
		return dogumTarihi;
	}

	public void setDogumTarihi(LocalDate dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}

}
