package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import main.SQLServer;
import models.Kullanici;
import models.RippleButton;
import models.RippleLabel;

public class BilgilerController implements Initializable {

	@FXML
	private TextField KullaniciAd;

	@FXML
	private TextField ad;

	@FXML
	private ToggleGroup Cinsiyet;

	@FXML
	private RippleLabel arkadasSayisi;

	@FXML
	private DatePicker dTarihi;

	@FXML
	private ImageView foto;

	@FXML
	private RippleButton geri;

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

	String KullaniciID;

	Image hesapFotosu;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Circle clip = new Circle(foto.getFitWidth() / 2);
		clip.setCenterX(foto.getFitWidth() / 2);
		clip.setCenterY(foto.getFitHeight() / 2);
		foto.setPreserveRatio(false);
		foto.setClip(clip);

	}

	public void setData(String KullaniciAdi) {

		KullaniciID = KullaniciAdi;

		Kullanici k = SQLServer.getKullanici(KullaniciAdi);

		ad.setText(k.getAd());
		soyad.setText(k.getSoyad());
		KullaniciAd.setText(k.getKullaniciAdi());
		telefon.setText(k.getTelefonNo());
		dTarihi.setValue(k.getDogumTarihi());
		foto.setImage(k.getFoto());
		postSayisi.setText(k.getPostSayisi() + "");
		arkadasSayisi.setText(k.getArkadasSayisi() + "");
		if (k.getCinsiyyet() == 1)
			erkek.setSelected(true);
		else
			kadin.setSelected(true);
	}

	@FXML
	void geri(ActionEvent event) {

		Stage es = (Stage) geri.getScene().getWindow();
		es.close();
	}

}
