package controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import main.Dialogues;
import main.SQLServer;
import models.Arkadas;

public class ArkadaslarimController implements Initializable {

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------FXML---------------------------------------------------------------

	@FXML
	private TableView<Arkadas> AramaTablosu;

	@FXML
	private TableView<Arkadas> ArkadasTablosu;

	@FXML
	private TableView<Arkadas> TalepTablosu;

	@FXML
	private TextField KullaniciLabel;

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Fields-------------------------------------------------------------

	String aktif = main.Start.AktifKullanici.getKullaniciAdi();

	TableColumn<Arkadas, String> ArkadasKullaniciAdi;
	TableColumn<Arkadas, LocalDate> ArkadasTarih;
	TableColumn<Arkadas, ImageView> ArkadasFoto;

	TableColumn<Arkadas, String> TalepKullaniciAdi;
	TableColumn<Arkadas, String> TalepDurum;
	TableColumn<Arkadas, LocalDate> TalepTarih;
	TableColumn<Arkadas, ImageView> TalepFoto;

	TableColumn<Arkadas, String> AramaKullaniciAdi;
	TableColumn<Arkadas, ImageView> AramaFoto;

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Initialize---------------------------------------------------------

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ArkadasKullaniciAdi = new TableColumn<>("Kullanici Adi");
		ArkadasTarih = new TableColumn<>("Tarih");
		ArkadasFoto = new TableColumn<>("Foto");

		TalepKullaniciAdi = new TableColumn<>("Kullanici Adi");
		TalepDurum = new TableColumn<>("Durum");
		TalepTarih = new TableColumn<>("Tarih");
		TalepFoto = new TableColumn<>("Foto");

		AramaKullaniciAdi = new TableColumn<>("Kullanici Adi");
		AramaFoto = new TableColumn<>("Foto");

		ArkadasKullaniciAdi.setCellValueFactory(new PropertyValueFactory<>("KullaniciAdi"));
		ArkadasTarih.setCellValueFactory(new PropertyValueFactory<>("Tarih"));
		ArkadasFoto.setCellValueFactory(new PropertyValueFactory<>("Foto"));

		TalepKullaniciAdi.setCellValueFactory(new PropertyValueFactory<>("KullaniciAdi"));
		TalepDurum.setCellValueFactory(new PropertyValueFactory<>("Durum"));
		TalepTarih.setCellValueFactory(new PropertyValueFactory<>("Tarih"));
		TalepFoto.setCellValueFactory(new PropertyValueFactory<>("Foto"));

		AramaKullaniciAdi.setCellValueFactory(new PropertyValueFactory<>("KullaniciAdi"));
		AramaFoto.setCellValueFactory(new PropertyValueFactory<>("Foto"));

		ArkadasTablosu.getColumns().addAll(ArkadasFoto, ArkadasKullaniciAdi, ArkadasTarih);
		ArkadasTablosu.setItems(SQLServer.getArkadaslar(aktif));

		TalepTablosu.getColumns().addAll(TalepFoto, TalepKullaniciAdi, TalepTarih, TalepDurum);
		TalepTablosu.setItems(SQLServer.getTalepler(aktif));

		AramaTablosu.getColumns().addAll(AramaFoto, AramaKullaniciAdi);
		AramaTablosu.setItems(SQLServer.getArama(aktif));

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Ara----------------------------------------------------------------

	@FXML
	void Ara(ActionEvent event) {

		AramaTablosu.setItems(SQLServer.Ara(KullaniciLabel.getText(), aktif));

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------IstekGonder--------------------------------------------------------

	@FXML
	void IstekGonder(ActionEvent event) {

		if (AramaTablosu.getSelectionModel().isEmpty()) {
			Dialogues.BosSecimDialogue.showAndWait();
			return;
		}

		Arkadas a = AramaTablosu.getSelectionModel().getSelectedItem();

		SQLServer.arkadsEkle(a.getKullaniciAdi(), aktif);

		Dialogues.TalepEklemeDialogue.showAndWait();

		ArkadasTablosu.setItems(SQLServer.getArkadaslar(aktif));
		TalepTablosu.setItems(SQLServer.getTalepler(aktif));
		AramaTablosu.setItems(SQLServer.getArama(aktif));

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------KabulEt------------------------------------------------------------

	@FXML
	void KabulEt(ActionEvent event) {

		if (TalepTablosu.getSelectionModel().isEmpty()) {
			Dialogues.BosSecimDialogue.showAndWait();
			return;
		}

		Arkadas a = TalepTablosu.getSelectionModel().getSelectedItem();

		if (a.getArkadaslikID().substring(0, aktif.length()).equals(aktif)) {
			Dialogues.YanlisTalepKabulDialogue.showAndWait();
			return;
		}

		SQLServer.arkadsKabul(a.getKullaniciAdi(), aktif);
		Dialogues.TalepKabulDialogue.showAndWait();
		ArkadasTablosu.setItems(SQLServer.getArkadaslar(aktif));
		TalepTablosu.setItems(SQLServer.getTalepler(aktif));
		AramaTablosu.setItems(SQLServer.getArama(aktif));

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------ReddEt-------------------------------------------------------------

	@FXML
	void ReddEt(ActionEvent event) {

		if (TalepTablosu.getSelectionModel().isEmpty()) {
			Dialogues.BosSecimDialogue.showAndWait();
			return;
		}

		Arkadas a = TalepTablosu.getSelectionModel().getSelectedItem();

		if (a.getArkadaslikID().substring(0, aktif.length()).equals(aktif)) {
			Dialogues.YanlisTalepRedDialogue.showAndWait();
			return;
		}

		SQLServer.arkadsRed(a.getKullaniciAdi(), aktif);

		Dialogues.TalepRedDialogue.showAndWait();

		ArkadasTablosu.setItems(SQLServer.getArkadaslar(aktif));
		TalepTablosu.setItems(SQLServer.getTalepler(aktif));
		AramaTablosu.setItems(SQLServer.getArama(aktif));

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Sil----------------------------------------------------------------

	@FXML
	void Sil(ActionEvent event) {

		if (ArkadasTablosu.getSelectionModel().isEmpty()) {
			Dialogues.BosSecimDialogue.showAndWait();
			return;
		}

		Arkadas a = ArkadasTablosu.getSelectionModel().getSelectedItem();

		SQLServer.arkadsSil(a.getKullaniciAdi(), aktif);

		Dialogues.ArkadasSilmeDialogue.showAndWait();

		ArkadasTablosu.setItems(SQLServer.getArkadaslar(aktif));
		TalepTablosu.setItems(SQLServer.getTalepler(aktif));
		AramaTablosu.setItems(SQLServer.getArama(aktif));

	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	************************************************************OVER*******************************************************************

}
