package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import main.SQLServer;
import models.Post;
import resources.Iconlar;

public class PostController implements Initializable {

	@FXML
	private ImageView begenFotosu;

	@FXML
	private HBox begenmeContainer;

	@FXML
	private Label begenmeSayisi;

	@FXML
	private ImageView postAyarlari;

	@FXML
	private ImageView postFotosu;

	@FXML
	private Label postKullaniciAdi;

	@FXML
	private ImageView postKullaniciFotosu;

	@FXML
	private Label postTarihi;

	@FXML
	private Label postYazisi;

	@FXML
	private ImageView tepkiFotosu;

	@FXML
	private ImageView yorumFotosu;

	@FXML
	private Label yorumSayisi;

	boolean BegenildiMi;

	String PostID;
	String KullaniciID;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		postKullaniciFotosu.setPreserveRatio(false);
		postKullaniciFotosu.setFitHeight(50);
		postKullaniciFotosu.setFitWidth(50);

		Circle clip = new Circle(25);
		clip.setCenterX(25);
		clip.setCenterY(25);
		postKullaniciFotosu.setClip(clip);

	}

	@FXML
	void BegeniAt(MouseEvent event) {

		if (BegenildiMi) {

			SQLServer.deleteBegeni(main.Start.AktifKullanici.getKullaniciAdi(), PostID);
			begenmeSayisi.setText(Integer.parseInt(begenmeSayisi.getText().substring(0, 1)) - 1 + " Begenme");
			begenFotosu.setImage(Iconlar.begenmeOutline);
			BegenildiMi = false;

		} else {

			SQLServer.addBegeni(main.Start.AktifKullanici.getKullaniciAdi(), PostID);
			begenmeSayisi.setText(Integer.parseInt(begenmeSayisi.getText().substring(0, 1)) + 1 + " Begenme");
			begenFotosu.setImage(Iconlar.begenme);
			BegenildiMi = true;

		}

	}

	@FXML
	void YorumAt(MouseEvent event) {

	}

	public void setData(Post p) {
		PostID = p.getID();
		KullaniciID = p.getSahib();
		BegenildiMi = p.BegenildiMi();

		postYazisi.setText(p.getMetin());
		postTarihi.setText(p.getTarih().toString());

		if (p.getFoto() != null) {
			postFotosu.setImage(p.getFoto());
		} else {
			postFotosu.setVisible(false);
			postFotosu.setManaged(false);
		}

		yorumSayisi.setText(p.getYorumSayisi() + " Yorum");
		begenmeSayisi.setText(p.getBegenmeSayisi() + " Begeni");
		postKullaniciAdi.setText(p.getSahib());

		postKullaniciFotosu.setImage(p.getSahibFotosu());

		if (BegenildiMi) {
			begenFotosu.setImage(Iconlar.begenme);
		} else {
			begenFotosu.setImage(Iconlar.begenmeOutline);
		}

		postTarihi.setText(p.getTarih().toString());

	}

}
