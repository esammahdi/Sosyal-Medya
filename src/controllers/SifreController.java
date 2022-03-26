package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Dialogues;
import main.SQLServer;
import resources.Iconlar;

public class SifreController implements Initializable {

	@FXML
	private TextField Ad;

	@FXML
	private DatePicker DogumTarihi;

	@FXML
	private TextField KullaniciID;

	@FXML
	private TextField Soyad;

	@FXML
	private TextField adminAd;

	@FXML
	private DatePicker adminDogumTarihi;

	@FXML
	private TextField adminID;

	@FXML
	private Button adminSifreDegistirBtn;

	@FXML
	private TextField adminSoyad;

	@FXML
	private Button sifreDegistirBtn;

	String KullaniciAdi;
	String ad;
	String soyad;
	LocalDate dogumTarihi;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	void sifreDegistirAdmin(ActionEvent event) {

		KullaniciAdi = adminID.getText();
		ad = adminAd.getText();
		soyad = adminSoyad.getText();
		dogumTarihi = adminDogumTarihi.getValue();

		if (KullaniciAdi.isBlank() || ad.isBlank() || soyad.isBlank() || dogumTarihi == null) {
			Dialogues.BosAlanDialogue.showAndWait();
			return;
		}

		try {

			if (!SQLServer.checkAdmin(KullaniciAdi)) {
				Dialogues.YanlisDegerDialogue.showAndWait();
				return;
			}

			if (!SQLServer.validateAdmin(KullaniciAdi, ad, soyad, dogumTarihi)) {
				Dialogues.YanlisDegerDialogue.showAndWait();
				return;
			}

			main.Start.Admin = true;
			main.Start.KullaniciAdi = KullaniciAdi;

			Parent root = FXMLLoader.load(getClass().getResource("/views/SifremiUnuttum2.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.PasswordIcon);
			st.setTitle("Sifre Yenile");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) Ad.getScene().getWindow();
			es.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	@FXML
	void sifreDegistir(ActionEvent event) {

		KullaniciAdi = KullaniciID.getText();
		ad = Ad.getText();
		soyad = Soyad.getText();
		dogumTarihi = DogumTarihi.getValue();

		if (KullaniciAdi.isBlank() || ad.isBlank() || soyad.isBlank() || dogumTarihi == null) {
			Dialogues.BosAlanDialogue.showAndWait();
			return;
		}

		if (!SQLServer.checkKullaniciID(KullaniciAdi)) {
			Dialogues.YanlisDegerDialogue.showAndWait();
			return;
		}

		try {

			if (!SQLServer.validateKullanici(KullaniciAdi, ad, soyad, dogumTarihi)) {
				Dialogues.YanlisDegerDialogue.showAndWait();
				return;
			}

			main.Start.Admin = false;
			main.Start.KullaniciAdi = KullaniciAdi;

			Parent root = FXMLLoader.load(getClass().getResource("/views/SifremiUnuttum2.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.PasswordIcon);
			st.setTitle("Sifre Yenile");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) Ad.getScene().getWindow();
			es.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	void geri(ActionEvent event) {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("/views/Giris.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.GirisIcon);
			st.setTitle("Sosyal Media");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) Ad.getScene().getWindow();
			es.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
