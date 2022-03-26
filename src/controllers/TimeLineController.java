package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.SQLServer;
import models.Arkadas;
import models.Post;
import models.RippleLabel;
import resources.Iconlar;

public class TimeLineController implements Initializable {

	@FXML
	private RippleLabel kullaniciAdiLabel;

	@FXML
	private TextField PostYazTextField;

	@FXML
	private ImageView kullaniciFotosuOrta;

	@FXML
	private ImageView kullaniciFotosuSide;

	@FXML
	private VBox postContainer;

	@FXML
	private VBox arkadasContainer;

	private String aktif;
	private Pane p1;
	private Pane p2;
	private HBox root;
	private RippleLabel yorumLabel;
	private ImageView yorum;
	private Label kullaniciAdi;
	List<Post> posts;
	List<Arkadas> arkadaslar;

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------Initialize---------------------------------------------------------

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		aktif = main.Start.AktifKullanici.getKullaniciAdi();
		arkadaslar = SQLServer.getArkadaslar(aktif);
		posts = SQLServer.getPostlar(aktif);

		PostYazTextField.setPromptText(aktif + ",Bugun Ne Yapmayi Planliyorsun ?");
		Image f = main.Start.AktifKullanici.getFoto();

		kullaniciFotosuOrta.setPreserveRatio(false);
		kullaniciFotosuSide.setPreserveRatio(false);

		Circle clip = new Circle(kullaniciFotosuOrta.getFitWidth() / 2, kullaniciFotosuOrta.getFitHeight() / 2,
				kullaniciFotosuOrta.getFitWidth() / 2);
		Circle clip2 = new Circle(kullaniciFotosuSide.getFitWidth() / 2, kullaniciFotosuSide.getFitHeight() / 2,
				kullaniciFotosuSide.getFitWidth() / 2);

		kullaniciFotosuOrta.setClip(clip);
		kullaniciFotosuSide.setClip(clip2);

		kullaniciFotosuOrta.setImage(f);
		kullaniciFotosuSide.setImage(f);

//		Circle clip3 = new Circle(this.Foto.getFitWidth() / 2, this.Foto.getFitHeight() / 2,
//				this.Foto.getFitWidth() / 2);
//		this.Foto.setPreserveRatio(false);
//		this.Foto.setClip(clip);

		try {

			for (Post p : posts) {
				FXMLLoader fxml = new FXMLLoader();
				fxml.setLocation(getClass().getResource("/views/Post.fxml"));
				VBox Root = fxml.load();
				PostController pControll = fxml.getController();
				pControll.setData(p);
				postContainer.getChildren().add(Root);
			}

			for (Arkadas a : arkadaslar) {
				root = new OzelHBox();
				p1 = new OzelPane(9, 63);
				p2 = new OzelPane(13, 63);
				kullaniciAdi = new Label();
				kullaniciAdi.setText(a.getKullaniciAdi());
				yorumLabel = new RippleLabel();
				yorum = new ImageView(Iconlar.yorum);
				ImageView F = a.getFoto();

				Circle clip3 = new Circle(F.getFitWidth() / 2, F.getFitHeight() / 2, F.getFitWidth() / 2);
				F.setPreserveRatio(false);
				F.setClip(clip3);

				yorum.setFitHeight(25);
				yorum.setFitWidth(25);
				yorumLabel.setGraphic(yorum);
				yorumLabel.setOnMouseClicked(e -> MesajKutusuAc(e, a.getKullaniciAdi()));

				root.getChildren().addAll(F, p1, kullaniciAdi, p2, yorumLabel);
				arkadasContainer.getChildren().add(root);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-----------------------------------------------------------arkadaslarimMenusu---------------------------------------------------------

	@FXML
	void arkadaslarimMenusu(MouseEvent event) {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("/views/Arkadaslarim.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.setTitle("Arkadaslarim");
			st.getIcons().add(Iconlar.ArkadasIcon);
			st.setResizable(false);
			st.initModality(Modality.APPLICATION_MODAL);
			st.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-----------------------------------------------------------------Logout---------------------------------------------------------------

	@FXML
	void logout(MouseEvent event) {

		try {

			Parent root = FXMLLoader.load(getClass().getResource("/views/Giris.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.GirisIcon);
			st.setTitle("Sosyal Media");
			st.setResizable(false);
			st.show();

			Stage es = (Stage) postContainer.getScene().getWindow();
			es.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	----------------------------------------------------------------postEkle--------------------------------------------------------------
	@FXML
	void postEkle(ActionEvent event) {
		try {

			Parent root = FXMLLoader.load(getClass().getResource("/views/PostAt.fxml"));
			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.PostIcon);
			st.setTitle("Post At");
			st.initModality(Modality.APPLICATION_MODAL);
			st.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-----------------------------------------------------------------bilgilerim-----------------------------------------------------------

	@FXML
	void bilgilerim(MouseEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/Bilgilerim.fxml"));
			Parent root = loader.load();

			BilgilerimController controller = loader.getController();
			controller.setData(aktif);

			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.setTitle("Bilgiler");
			st.getIcons().add(Iconlar.BilgiIcon);
			st.initModality(Modality.APPLICATION_MODAL);
			st.setResizable(false);
			st.showAndWait();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

//	--------------------------------------------------------------------------------------------------------------------------------------
//	-------------------------------------------------------------------MesajKutusuAc------------------------------------------------------
	public void MesajKutusuAc(MouseEvent event, String ArkadasID) {

		try {

			System.out.println("(TimeLineController) aktif : " + aktif + " Arkadas : " + ArkadasID);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/views/MesajKutusu.fxml"));
			Parent root = loader.load();

			MesajController controller = loader.getController();
			controller.setArkadasID(ArkadasID);

			Stage st = new Stage();
			st.setScene(new Scene(root));
			st.setTitle("Mesajlasma");
			st.getIcons().add(Iconlar.yorum);
			st.setResizable(false);
			st.initModality(Modality.APPLICATION_MODAL);
			st.showAndWait();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

//	-----------------------------------------------------------------------------------------------------------------------------------
//	************************************************************OVER*******************************************************************

}

class OzelHBox extends HBox {

	public OzelHBox() {
		super.setSpacing(10);
		super.setPrefWidth(214);
		super.setPrefHeight(63);
		super.setAlignment(Pos.CENTER_LEFT);
		super.setStyle("-fx-background-color : #FFF;-fx-background-radius : 20");
	}

}

class OzelPane extends Pane {

	public OzelPane() {
		super.setStyle("-fx-background-color : #FFF");
	}

	public OzelPane(double x, double y) {
		super.setPrefWidth(x);
		super.setPrefHeight(y);
		super.setStyle("-fx-background-color : #FFF");
	}

}