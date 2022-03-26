package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Dialogues;
import main.SQLServer;
import resources.Iconlar;

public class SifreYenilemekController {

	@FXML
	private TextField yeniSifre;

	@FXML
	private TextField yeniSifreTekrari;

	@FXML
	void sifreSifirla(ActionEvent event) {

		try {

			if (yeniSifre.getText().isBlank() || yeniSifreTekrari.getText().isBlank()) {
				Dialogues.BosAlanDialogue.showAndWait();
				return;
			}

			if (!yeniSifre.getText().equals(yeniSifreTekrari.getText())) {
				Dialogues.SifreDialogue.showAndWait();
				return;
			}

			if (main.Start.Admin) {
				SQLServer.updateAdminSifre(main.Start.KullaniciAdi, yeniSifre.getText());
			} else {
				SQLServer.updateKullaniciSifre(main.Start.KullaniciAdi, yeniSifre.getText());
			}

			Dialogues.SifreDegistirmeDialogue.showAndWait();

			Parent root = FXMLLoader.load(getClass().getResource("/views/Giris.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.GirisIcon);
			st.setTitle("Sosyal Media");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) yeniSifre.getScene().getWindow();
			es.close();

		} catch (IOException e) {
			e.printStackTrace();
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

			Stage es = (Stage) yeniSifre.getScene().getWindow();
			es.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
