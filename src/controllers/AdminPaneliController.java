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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Dialogues;
import main.SQLServer;
import models.Kullanici;
import resources.Iconlar;

public class AdminPaneliController implements Initializable {

//	-----------------------------------------------------------------------------------------------------------------------------------
//	------------------------------------------------------------@FXML------------------------------------------------------------------
	@FXML
	private Button bilgiler;

	@FXML
	private Button geri;

	@FXML
	private Button sil;

	@FXML
	private TableView<Kullanici> tablo;

//		-----------------------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------Fields-----------------------------------------------------------------

//		-----------------------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------Initialize-------------------------------------------------------------

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		TableColumn<Kullanici, String> KullaniciAdi = new TableColumn<>("Kullanici Adi");
		TableColumn<Kullanici, String> Ad = new TableColumn<>("Ad");
		TableColumn<Kullanici, String> Soyad = new TableColumn<>("Soyad");
		TableColumn<Kullanici, String> Telefon = new TableColumn<>("Telefon");
		TableColumn<Kullanici, LocalDate> DogumTarihi = new TableColumn<>("Dogum Tarihi");
		TableColumn<Kullanici, String> Cinsiyet = new TableColumn<>("Cinsiyet");
		TableColumn<Kullanici, Integer> TakipSayisi = new TableColumn<>("Takip Sayisi");
		TableColumn<Kullanici, Integer> PostSayisi = new TableColumn<>("Post Sayisi");
		TableColumn<Kullanici, Integer> ArkadasSayisi = new TableColumn<>("Arkadas Sayisi");

		KullaniciAdi.setCellValueFactory(new PropertyValueFactory<>("kullaniciAdi"));
		Ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
		Soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
		Telefon.setCellValueFactory(new PropertyValueFactory<>("telefonNo"));
		DogumTarihi.setCellValueFactory(new PropertyValueFactory<>("dogumTarihi"));
		Cinsiyet.setCellValueFactory(new PropertyValueFactory<>("cinsiyyet"));
		TakipSayisi.setCellValueFactory(new PropertyValueFactory<>("takipSayisi"));
		PostSayisi.setCellValueFactory(new PropertyValueFactory<>("postSayisi"));
		ArkadasSayisi.setCellValueFactory(new PropertyValueFactory<>("arkadasSayisi"));

		tablo.setItems(SQLServer.getKullanicilar());
		tablo.getColumns().addAll(KullaniciAdi, Ad, Soyad, Telefon, DogumTarihi, Cinsiyet, PostSayisi, TakipSayisi,
				ArkadasSayisi);

	}

//		-----------------------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------Bilgiler---------------------------------------------------------------
	@FXML
	void bilgiler(ActionEvent event) {

		if (tablo.getSelectionModel().isEmpty()) {
			Dialogues.BosSecimDialogue.showAndWait();
			return;
		}

		Kullanici k = tablo.getSelectionModel().getSelectedItem();

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Bilgiler.fxml"));
			Parent root = loader.load();

			BilgilerController controller = loader.getController();
			controller.setData(k.getKullaniciAdi());

			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.BilgiIcon);
			st.setTitle("Bilgiler");
			st.initModality(Modality.APPLICATION_MODAL);
			st.setResizable(false);
			st.show();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

//		-----------------------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------Geri-------------------------------------------------------------------
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

			Stage es = (Stage) geri.getScene().getWindow();
			es.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//		-----------------------------------------------------------------------------------------------------------------------------------
//		------------------------------------------------------------Sil--------------------------------------------------------------------
	@FXML
	void sil(ActionEvent event) {

		if (tablo.getSelectionModel().isEmpty()) {
			Dialogues.BosSecimDialogue.showAndWait();
			return;
		}

		Kullanici k = tablo.getSelectionModel().getSelectedItem();

		SQLServer.KullaniciSil(k.getKullaniciAdi());

		Dialogues.BasariDialogue.showAndWait();

		tablo.getSelectionModel().getSelectedItems().forEach(tablo.getItems()::remove);

	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	************************************************************OVER*******************************************************************
}
