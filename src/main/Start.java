package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Kullanici;
import resources.Iconlar;

public class Start extends Application {

	public static Kullanici AktifKullanici;
	public static boolean Admin;
	public static String KullaniciAdi;
	private static String dbName = "SosyalMedya";
	private static String dbUser = "esam";
	private static String dbPassword = "1234";

	public static void main(String[] args) {

		Platform.runLater(() -> Dialogues.init());
//		Dialogues.init();
		Platform.runLater(() -> {
			SQLServer.setDbName(dbName);
			SQLServer.setDbUser(dbUser);
			SQLServer.setDbPassword(dbPassword);
			SQLServer.dbConnect();
		});

		Application.launch(args);
	}

	@Override
	public void start(Stage st) {

		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource("/views/Giris.fxml"));
			st.setScene(new Scene(root));
			st.getIcons().add(Iconlar.GirisIcon);
			st.setResizable(false);
			st.setTitle("Sosyal Medya");
			st.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
