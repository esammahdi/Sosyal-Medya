package controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import main.SQLServer;
import models.Mesaj;

public class MesajController implements Initializable {

	@FXML
	private VBox mesajContainer;

	@FXML
	TextField Mesaj;

	String aktif;

	OzelImageView foto;
	Circle clip;

	OHBox giden;
	OHBox gelen;

	OzelText gidenMetin;
	OzelText gelenMetin;

	OzelTextFlow gidenText;
	OzelTextFlow gelenText;

	String ArkadasID = "Esam";
	List<Mesaj> Mesajlar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void setArkadasID(String ArkadasID) {
		aktif = main.Start.AktifKullanici.getKullaniciAdi();
		this.ArkadasID = ArkadasID;
		Mesajlar = SQLServer.getMesajlar(aktif, ArkadasID);
		giden = new OHBox();
		gelen = new OHBox();

		gidenMetin = new OzelText();
		gelenMetin = new OzelText();

		gidenText = new OzelTextFlow();
		gelenText = new OzelTextFlow();

		foto = new OzelImageView();

		Mesaj.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)) {
				MesajGonder(new ActionEvent());
			}
		});

		for (Mesaj m : Mesajlar) {

			if (m.getGonderenID().equals(aktif)) {
				giden = new OHBox();
				gidenMetin = new OzelText();
				gidenText = new OzelTextFlow();
				foto = new OzelImageView();

				foto.setImage(m.getSahibFoto());
				gidenMetin.setText(m.getMetin());
				gidenText.getChildren().add(gidenMetin);

				giden.getChildren().addAll(gidenText, foto);

				mesajContainer.getChildren().add(giden);

			} else {
				gelen = new OHBox();
				gelen.setAlignment(Pos.CENTER_LEFT);
				gelenMetin = new OzelText();
				gelenText = new OzelTextFlow();
				foto = new OzelImageView();

				foto.setImage(m.getSahibFoto());
				gelenMetin.setText(m.getMetin());
				gelenText.getChildren().add(gelenMetin);

				gelen.getChildren().addAll(foto, gelenText);

				mesajContainer.getChildren().add(gelen);

			}
		}
	}

	@FXML
	public void MesajGonder(ActionEvent e) {

		System.out.println("(MesajControler) aktif : " + aktif + " Arkadas : " + ArkadasID);
		giden = new OHBox();
		gidenMetin = new OzelText();
		gidenText = new OzelTextFlow();
		foto = new OzelImageView();

		Mesaj m = new Mesaj(aktif, ArkadasID, Mesaj.getText(), main.Start.AktifKullanici.getFoto());

		foto.setImage(m.getSahibFoto());
		gidenMetin.setText(m.getMetin());
		gidenText.getChildren().add(gidenMetin);

		giden.getChildren().addAll(gidenText, foto);

		mesajContainer.getChildren().add(giden);
		Mesaj.setText("");
		SQLServer.mesajEkle(m);

	}

}

//********************************************************************************************************************************************
//*************************************************************OzelClasses********************************************************************
//********************************************************************************************************************************************

class OHBox extends HBox {

	public OHBox() {
		super.setAlignment(Pos.CENTER_RIGHT);
		super.setPadding(new Insets(5, 5, 5, 10));
		super.setSpacing(10);
		super.setStyle("-fx-background-color : #FFF;-fx-background-radius : 20");
	}

}

class OzelText extends Text {

	public OzelText() {
		super.setFill(Color.ALICEBLUE);
		super.setFill(Color.color(0.943, 0.945, 0.996));
	}

	public OzelText(int i) {
		super.setFill(Color.ALICEBLUE);
		super.setFill(Color.color(0.943, 0.945, 0.996));
	}

}

class OzelTextFlow extends TextFlow {

	public OzelTextFlow() {
		super.setStyle("-fx-color : #eff2ff; -fx-background-color : #0f7df2;-fx-background-radius : 20px");
		super.setPadding(new Insets(5, 10, 5, 10));
	}

	public OzelTextFlow(int i) {
		super.setStyle("-fx-color : #eff2ff; -fx-background-color : #0f7df2;-fx-background-radius : 20px");
		super.setPadding(new Insets(5, 10, 5, 10));
	}

}

class OzelImageView extends ImageView {

	public OzelImageView() {
		super.setPreserveRatio(false);
		super.setFitHeight(25);
		super.setFitWidth(25);
		Circle clip = new Circle(super.getFitWidth() / 2, super.getFitHeight() / 2, 14);
		super.setClip(clip);
	}

}