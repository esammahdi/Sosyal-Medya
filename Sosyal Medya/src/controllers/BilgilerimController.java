package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Dialogues;
import main.SQLServer;
import models.Kullanici;
import models.RippleButton;
import models.RippleLabel;

public class BilgilerimController implements Initializable {

	@FXML
	private TextField KullaniciAd;

	@FXML
	private TextField ad;

	@FXML
	private ToggleGroup Cinsiyet;

	@FXML
	private CheckBox aktiflestir;

	@FXML
	private RippleLabel arkadasSayisi;

	@FXML
	private RippleLabel uploadLabel;

	@FXML
	private DatePicker dTarihi;

	@FXML
	private ImageView foto;

	@FXML
	private RippleButton geri;

	@FXML
	private RippleButton guncelle;

	@FXML
	private RippleLabel postSayisi;

	@FXML
	private RadioButton erkek;

	@FXML
	private RadioButton kadin;

	@FXML
	private TextField soyad;

	@FXML
	private TextField telefon;

	@FXML
	private ImageView uploadFoto;

	String KullaniciID;
	String SQL;

	boolean fotoDegistirildi;
	Image hesapFotosu;

	File hesapFoto;
	FileChooser fChooser;
	FileInputStream fStream;
	FileInputStream fStream2;
	Alert dialogue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Circle clip = new Circle(foto.getFitWidth() / 2);
		clip.setCenterX(foto.getFitWidth() / 2);
		clip.setCenterY(foto.getFitHeight() / 2);
		foto.setPreserveRatio(false);
		foto.setClip(clip);

		fChooser = new FileChooser();
		fChooser.setTitle("Personel Foto Secimi");
		fChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG", "*.jpg"),
				new FileChooser.ExtensionFilter("PNG", "*.png"));

	}

	public void setData(String KullaniciAdi) {

		Kullanici aktif = SQLServer.getKullanici(KullaniciAdi);
		KullaniciID = aktif.getKullaniciAdi();

		ad.setText(aktif.getAd());
		soyad.setText(aktif.getSoyad());
		KullaniciAd.setText(aktif.getKullaniciAdi());
		telefon.setText(aktif.getTelefonNo());
		dTarihi.setValue(aktif.getDogumTarihi());
		postSayisi.setText(aktif.getPostSayisi() + "");
		arkadasSayisi.setText(aktif.getArkadasSayisi() + "");
		if (aktif.getCinsiyyet() == 1)
			erkek.setSelected(true);
		else
			kadin.setSelected(true);
		foto.setImage(aktif.getFoto());

	}

	@FXML
	void aktiflestir(ActionEvent event) {

		if (aktiflestir.isSelected()) {
			KullaniciAd.setDisable(false);
			ad.setDisable(false);
			soyad.setDisable(false);
			telefon.setDisable(false);
			guncelle.setDisable(false);
			uploadLabel.setDisable(false);
		} else {
			KullaniciAd.setDisable(true);
			ad.setDisable(true);
			soyad.setDisable(true);
			telefon.setDisable(true);
			guncelle.setDisable(true);
			uploadLabel.setDisable(true);

		}

	}

	@FXML
	void geri(ActionEvent event) {

		Stage es = (Stage) geri.getScene().getWindow();
		es.close();
	}

	@FXML
	void guncelle(ActionEvent event) {

		if (aktiflestir.isSelected()) {

			if (KullaniciAd.getText().isBlank() || ad.getText().isBlank() || soyad.getText().isBlank()
					|| telefon.getText().isBlank()) {

				Dialogues.BosAlanDialogue.showAndWait();
				return;
			}

			if (SQLServer.checkKullaniciID(KullaniciAd.getText()) && !KullaniciAd.getText().equals(KullaniciID)) {
				Dialogues.KullaniciAdiDialogue.showAndWait();
				return;
			}

			SQLServer.updateKullanici(KullaniciID, KullaniciAd.getText(), ad.getText(), soyad.getText(),
					telefon.getText(), fStream2);

			Dialogues.BasariDialogue.showAndWait();
			main.Start.AktifKullanici = SQLServer.getKullanici(KullaniciAd.getText());
			KullaniciID = KullaniciAd.getText();
			setData(KullaniciID);

		}

	}

	@FXML
	void upload(MouseEvent event) {

		hesapFoto = fChooser.showOpenDialog(geri.getScene().getWindow());

		if (hesapFoto != null) {
			try {
				fStream = new FileInputStream(hesapFoto);
				fStream2 = new FileInputStream(hesapFoto);
				foto.setImage(new Image(fStream));
				fotoDegistirildi = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
