package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Dialogues;
import main.SQLServer;
import resources.Iconlar;

public class PostEkleController implements Initializable {

	@FXML
	private ImageView EkleImg;

	@FXML
	private Label EkleSilLabel;

	@FXML
	private ImageView postAyarlari;

	@FXML
	private ImageView postFotosu;

	@FXML
	private Label postKullaniciAdi;

	@FXML
	private ImageView postKullaniciFotosu;

	@FXML
	private TextField postMetni;

	@FXML
	private Label postTarihi;

	@FXML
	private Button yayinlaBtn;

	@FXML
	private Button geriBtn;

	String KullaniciID;

	File hesapFotosu;
	FileChooser fChooser;
	FileInputStream fStream;
	FileInputStream fStream2;

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Initialize---------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		KullaniciID = main.Start.AktifKullanici.getKullaniciAdi();
		Image KullaniciFotosu = main.Start.AktifKullanici.getFoto();

		Circle clip = new Circle(postKullaniciFotosu.getFitWidth() / 2);
		clip.setCenterX(postKullaniciFotosu.getFitWidth() / 2);
		clip.setCenterY(postKullaniciFotosu.getFitHeight() / 2);

		fChooser = new FileChooser();
		fChooser.setTitle("Personel Foto Secimi");
		fChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));

		postFotosu.setVisible(false);
		postFotosu.setManaged(false);

		postTarihi.setText(LocalDate.now().toString());
		postKullaniciAdi.setText(KullaniciID);
		postKullaniciFotosu.setImage(KullaniciFotosu);
		postKullaniciFotosu.setPreserveRatio(false);
		postKullaniciFotosu.setClip(clip);

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------FotoEkle-----------------------------------------------------------

	@FXML
	void FotoEkle(MouseEvent event) {

		if (fStream == null) {

			hesapFotosu = fChooser.showOpenDialog(EkleImg.getScene().getWindow());
			if (hesapFotosu != null) {

				try {
					fStream = new FileInputStream(hesapFotosu);
					fStream2 = new FileInputStream(hesapFotosu);
				} catch (IOException e) {
					e.printStackTrace();
				}

				Image f = new Image(fStream);
				postFotosu.setImage(f);
				postFotosu.setVisible(true);
				postFotosu.setManaged(true);
				EkleSilLabel.setText("Fotoyu Sil");
				EkleImg.setImage(Iconlar.kaldir);
			}
		} else {

			fStream = null;
			postFotosu.setImage(null);
			postFotosu.setVisible(false);
			postFotosu.setManaged(false);
			EkleSilLabel.setText("Foto Ekle");
			EkleImg.setImage(Iconlar.ekle);
		}
	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Geri---------------------------------------------------------------
	@FXML
	void geri(ActionEvent event) {
		Stage es = (Stage) geriBtn.getScene().getWindow();
		es.close();
	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Yayinla------------------------------------------------------------

	@FXML
	void yayinla(ActionEvent event) {

		if (postMetni.getText().isBlank() && postFotosu.getImage() == null) {
			Dialogues.BosAlanDialogue.showAndWait();
			return;
		}

		SQLServer.postEkle(KullaniciID, KullaniciID + LocalDateTime.now().toString(), postMetni.getText(), fStream2);

		Dialogues.PostEklemeBasariDialogue.showAndWait();

		Stage es = (Stage) geriBtn.getScene().getWindow();
		es.close();

	}

//	--------------------------------------------------------------------------------------------------------------------------------------

}
