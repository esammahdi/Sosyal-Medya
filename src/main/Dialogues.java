package main;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Dialogues {

	public static Alert BosAlanDialogue;
	public static Alert KullaniciAdiDialogue;
	public static Alert YanlisDegerDialogue;
	public static Alert SifreDialogue;
	public static Alert SifreDegistirmeDialogue;
	public static Alert BasariDialogue;
	public static Alert HesapSilmeBasariDialogue;
	public static Alert BosSecimDialogue;
	public static Alert TalepKabulDialogue;
	public static Alert TalepRedDialogue;
	public static Alert YanlisTalepKabulDialogue;
	public static Alert YanlisTalepRedDialogue;
	public static Alert ArkadasSilmeDialogue;
	public static Alert TalepEklemeDialogue;
	public static Alert PostEklemeBasariDialogue;

	public static void init() {

		BosAlanDialogue = new Alert(AlertType.ERROR);
		BosSecimDialogue = new Alert(AlertType.ERROR);
		YanlisDegerDialogue = new Alert(AlertType.ERROR);
		YanlisTalepKabulDialogue = new Alert(AlertType.ERROR);
		YanlisTalepRedDialogue = new Alert(AlertType.ERROR);
		KullaniciAdiDialogue = new Alert(AlertType.ERROR);
		SifreDialogue = new Alert(AlertType.ERROR);

		ArkadasSilmeDialogue = new Alert(AlertType.INFORMATION);
		BasariDialogue = new Alert(AlertType.INFORMATION);
		HesapSilmeBasariDialogue = new Alert(AlertType.INFORMATION);
		TalepKabulDialogue = new Alert(AlertType.INFORMATION);
		TalepRedDialogue = new Alert(AlertType.INFORMATION);
		TalepEklemeDialogue = new Alert(AlertType.INFORMATION);
		PostEklemeBasariDialogue = new Alert(AlertType.INFORMATION);
		SifreDegistirmeDialogue = new Alert(AlertType.INFORMATION);

		SifreDialogue.setTitle("Hata");
		SifreDialogue.setHeaderText("Sifre Tekrar Hatasi.");
		SifreDialogue.setContentText("Sifre ve Sifre Tekrari Ayni Olmalidir!");

		BosAlanDialogue.setTitle("Hata");
		BosAlanDialogue.setHeaderText("Bos Alan Hatasi.");
		BosAlanDialogue.setContentText("Alanlar Bos Olamaz!");

		BosSecimDialogue.setTitle("Hata");
		BosSecimDialogue.setHeaderText("Bos Secim Hatasi");
		BosSecimDialogue.setContentText("Lutfen Bir Satir Sec!");

		YanlisTalepKabulDialogue.setTitle("Hata");
		YanlisTalepKabulDialogue.setHeaderText("Yanlis Secim Hatasi");
		YanlisTalepKabulDialogue.setContentText("Kendi Talebini Kabul Edemezsin!");

		YanlisTalepRedDialogue.setTitle("Hata");
		YanlisTalepRedDialogue.setHeaderText("Yanlis Secim Hatasi");
		YanlisTalepRedDialogue.setContentText("Kendi Talebini Reddedemezsin!");

		YanlisDegerDialogue.setTitle("Hata");
		YanlisDegerDialogue.setHeaderText("Yanlis Deger Hatasi.");
		YanlisDegerDialogue.setContentText("Yanlis Kullanici Adi veya Sifre");

		KullaniciAdiDialogue.setTitle("Hata");
		KullaniciAdiDialogue.setHeaderText("Kullanici Adi Hatasi.");
		KullaniciAdiDialogue.setContentText("Kullanici Adi Zaten Var.Lutfen Bask Bir Ad Secin.");

		BasariDialogue.setTitle("Bildiri.");
		BasariDialogue.setHeaderText("Basari Bildirisi.");
		BasariDialogue.setContentText("Hesap Basariyla Olusturuldu!");

		HesapSilmeBasariDialogue.setTitle("Bildiri.");
		HesapSilmeBasariDialogue.setHeaderText("Basari Bildirisi.");
		HesapSilmeBasariDialogue.setContentText("Hesap Basariyla Silindi!");

		PostEklemeBasariDialogue.setTitle("Bildiri.");
		PostEklemeBasariDialogue.setHeaderText("Basari Bildirisi.");
		PostEklemeBasariDialogue.setContentText("Post Basariyla Yayinlandi!");

		TalepKabulDialogue.setTitle("Bildiri.");
		TalepKabulDialogue.setHeaderText("Basari Bildirisi.");
		TalepKabulDialogue.setContentText("Talep Basariyla Kabul Edildi.");

		TalepRedDialogue.setTitle("Bildiri.");
		TalepRedDialogue.setHeaderText("Basari Bildirisi.");
		TalepRedDialogue.setContentText("Talep Basariyla Reddedildi.");

		TalepEklemeDialogue.setTitle("Bildiri.");
		TalepEklemeDialogue.setHeaderText("Basari Bildirisi.");
		TalepEklemeDialogue.setContentText("Istek Basariyla Gonderildi.");

		ArkadasSilmeDialogue.setTitle("Bildiri.");
		ArkadasSilmeDialogue.setHeaderText("Basari Bildirisi.");
		ArkadasSilmeDialogue.setContentText("Arkadas Basariyla Silindi");

		SifreDegistirmeDialogue.setTitle("Bildiri");
		SifreDegistirmeDialogue.setHeaderText("Sifre Degistirme Bildirisi.");
		SifreDegistirmeDialogue.setContentText("Sifre Basariyla Degistirildi!");
	}

}
