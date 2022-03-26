package controllers;

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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.Dialogues;
import main.SQLServer;
import resources.Iconlar;

public class GirisController implements Initializable {

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------@FXML------------------------------------------------------------------

	@FXML
	private Button admiinGiris;

	@FXML
	private TextField adminAdi;

	@FXML
	private PasswordField adminSifresi;

	@FXML
	private Label hesapOlustur;

	@FXML
	private TextField kullaniciAdi;

	@FXML
	private Button kullaniciGiris;

	@FXML
	private PasswordField kullaniciSifresi;

	@FXML
	private Label sifreUnuttum;

	@FXML
	private Label sifreUnuttumAdmin;

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------Fields-----------------------------------------------------------------

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------Initialize-------------------------------------------------------------

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		kullaniciSifresi.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				KullaniciGiris(new ActionEvent());
			}
		});

		adminSifresi.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				AdminGiris(new ActionEvent());
			}
		});

	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------AdminGiris-------------------------------------------------------------

	@FXML
	void AdminGiris(ActionEvent event) {

		String KullaniciAdi = adminAdi.getText();
		String Sifre = adminSifresi.getText();

		if (KullaniciAdi.isBlank() || Sifre.isBlank()) {
			Dialogues.BosAlanDialogue.showAndWait();
			return;
		}

		try {

			if (!SQLServer.checkAdmin(KullaniciAdi)) {
				Dialogues.YanlisDegerDialogue.showAndWait();
				return;
			}

			if (!SQLServer.checkAdminSifre(KullaniciAdi, Sifre)) {
				Dialogues.YanlisDegerDialogue.showAndWait();
				return;
			}

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AdminPaneli.fxml"));
			Parent root = loader.load();

			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.AdminIcon);
			st.setTitle("Admin Paneli");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) adminAdi.getScene().getWindow();
			es.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------sifreUnuttumAdmin------------------------------------------------------

	@FXML
	void sifreUnuttumAdmin(MouseEvent event) {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/SifremiUnuttum1.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.PasswordIcon);
			st.setTitle("Sifre Yenilemek");
			st.setResizable(false);
			st.show();
			Stage es = (Stage) adminAdi.getScene().getWindow();
			es.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------KullaniciGiris---------------------------------------------------------

	@FXML
	void KullaniciGiris(ActionEvent event) {

		String KullaniciAdi = kullaniciAdi.getText();
		String Sifre = kullaniciSifresi.getText();

		if (KullaniciAdi.isBlank() || Sifre.isBlank()) {
			Dialogues.BosAlanDialogue.showAndWait();
			return;
		}

		try {

			if (!SQLServer.checkKullaniciID(KullaniciAdi)) {
				Dialogues.YanlisDegerDialogue.showAndWait();
				return;
			}

			if (!SQLServer.checkKullaniciSifre(KullaniciAdi, Sifre)) {
				Dialogues.YanlisDegerDialogue.showAndWait();
				return;
			}

			main.Start.AktifKullanici = SQLServer.getKullanici(KullaniciAdi);

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/TimeLine.fxml"));
			Parent root = loader.load();

			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.MainIcon);
			st.setTitle("Merhaba!");
			st.show();

			Stage es = (Stage) adminAdi.getScene().getWindow();
			es.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------sifreUnuttum-----------------------------------------------------------

	@FXML
	void sifreUnuttum(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/SifremiUnuttum1.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.setTitle("Sifre Yenilemek");
			st.getIcons().add(Iconlar.PasswordIcon);
			st.setResizable(false);
			st.show();
			Stage es = (Stage) adminAdi.getScene().getWindow();
			es.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------hesapOlustur-----------------------------------------------------------

	@FXML
	void hesapOlustur(MouseEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/views/HesapOlustur.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.setTitle("Hesap Olustur");
			st.getIcons().add(Iconlar.HesapIcon);
			st.setResizable(false);
			st.show();
			Stage es = (Stage) adminAdi.getScene().getWindow();
			es.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	************************************************************OVER*******************************************************************

}
