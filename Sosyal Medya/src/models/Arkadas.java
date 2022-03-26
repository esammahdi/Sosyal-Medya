package models;

import java.time.LocalDate;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Arkadas {

	private String KullaniciAdi;
	private String TelefonNo;
	private String Durum;
	private String ArkadaslikID;
	private LocalDate Tarih;
	private ImageView Foto = new ImageView();

	public Arkadas(Image Foto, String KullaniciAdi) {
		this.setKullaniciAdi(KullaniciAdi);
		this.setFoto(Foto);
		this.Foto.setFitWidth(48);
		this.Foto.setFitHeight(48);
	}

	public Arkadas(Image Foto, String KullaniciAdi, LocalDate Tarih) {
		this.setKullaniciAdi(KullaniciAdi);
		this.setTarih(Tarih);
		this.setFoto(Foto);
		this.Foto.setFitWidth(48);
		this.Foto.setFitHeight(48);

	}

	public Arkadas(Image Foto, String KullaniciAdi, LocalDate Tarih, String Durum) {
		this.setKullaniciAdi(KullaniciAdi);
		this.setTarih(Tarih);
		this.setFoto(Foto);
		this.setDurum(Durum);
		this.Foto.setFitWidth(48);
		this.Foto.setFitHeight(48);
	}

	public String getKullaniciAdi() {
		return KullaniciAdi;
	}

	public void setKullaniciAdi(String kullaniciAdi) {
		KullaniciAdi = kullaniciAdi;
	}

	public ImageView getFoto() {
		return Foto;
	}

	public void setFoto(Image foto) {
		this.Foto.setImage(foto);
	}

	public LocalDate getTarih() {
		return Tarih;
	}

	public void setTarih(LocalDate tarih) {
		Tarih = tarih;
	}

	public String getDurum() {
		return Durum;
	}

	public void setDurum(String durum) {
		Durum = durum;
	}

	public String getTelefonNo() {
		return TelefonNo;
	}

	public void setTelefonNo(String telefonNo) {
		TelefonNo = telefonNo;
	}

	public String getArkadaslikID() {
		return ArkadaslikID;
	}

	public void setArkadaslikID(String arkadaslikID) {
		ArkadaslikID = arkadaslikID;
	}
}
