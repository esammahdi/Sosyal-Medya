package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Dialogues;
import main.SQLServer;

public class HesapController implements Initializable {

	@FXML
	private ToggleGroup Cinsiyet;

	@FXML
	private RadioButton erkek;

	@FXML
	private RadioButton kadin;

	@FXML
	private TextField sifre;

	@FXML
	private TextField sifreTekrar;

	@FXML
	private TextField FotoPath;

	@FXML
	private TextField KullaniciAdi;

	@FXML
	private TextField ad;

	@FXML
	private DatePicker dogumTarihi;

	@FXML
	private Button hesapOlusturBtn;

	@FXML
	private Button geriBtn;

	@FXML
	private TextField soyad;

	@FXML
	private TextField telefonNumarasi;

	@FXML
	private Button yukleBtn;

	Image Girisicon = new Image("resources/images/card_security_64px.png");

	String KullaniciAd;
	String Ad;
	String Soyad;
	String Sifre;
	String SifreTekrar;
	String Telefon;
	String DogumTarihi;
	int cinsiyet;
	String Foto;
	File hesapFotosu;
	FileChooser fChooser;
	FileInputStream fStream;
	java.sql.Date dTarihi;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		fChooser = new FileChooser();
		fChooser.setTitle("Personel Foto Secimi");
		fChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));

	}

	@FXML
	void hesapOlustur(ActionEvent event) {

		KullaniciAd = KullaniciAdi.getText();
		Ad = ad.getText();
		Soyad = soyad.getText();
		Telefon = telefonNumarasi.getText();
		Sifre = sifre.getText();
		SifreTekrar = sifreTekrar.getText();

		if (validate() == 0) return;

		try {

			if (SQLServer.checkKullaniciID(KullaniciAd)) {
				Dialogues.KullaniciAdiDialogue.showAndWait();
				return;
			}

			dTarihi = java.sql.Date.valueOf(dogumTarihi.getValue());

			SQLServer.hesapEkle(KullaniciAd, Ad, Soyad, Sifre, dTarihi, Telefon, fStream, cinsiyet);

			Dialogues.BasariDialogue.showAndWait();

			Parent root = FXMLLoader.load(getClass().getResource("/views/Giris.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Girisicon);
			st.setTitle("Sosyal Media");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) geriBtn.getScene().getWindow();
			es.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int validate() {

		if (KullaniciAd.isBlank() || Ad.isBlank() || Soyad.isBlank() || Telefon.isBlank() || Sifre.isBlank()
				|| SifreTekrar.isBlank() || (!erkek.isSelected() && !kadin.isSelected())
				|| dogumTarihi.getValue() == null) {

			Dialogues.BosAlanDialogue.showAndWait();

			return 0;
		}

		if (!Sifre.equals(SifreTekrar)) {

			Dialogues.SifreDialogue.showAndWait();
			return 0;
		}

		return 1;
	}

	@FXML
	void fotoYukle(ActionEvent event) {
		hesapFotosu = fChooser.showOpenDialog(yukleBtn.getScene().getWindow());
		if (hesapFotosu != null) {
			FotoPath.setText(hesapFotosu.getAbsolutePath());
			try {
				fStream = new FileInputStream(hesapFotosu);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void geri(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Giris.fxml"));
			Parent root = loader.load();

			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Girisicon);
			st.setTitle("Sosyal Medya");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) geriBtn.getScene().getWindow();
			es.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
