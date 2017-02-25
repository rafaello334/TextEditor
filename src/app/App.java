package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application
{
	public static final int PORT = 1001;
	public static final String HOST = "localhost";

	@Override
	public void start(Stage primaryStage)
	{
		final String appName = "Text Editor v0.1";
		try
		{
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/view/MainPane.fxml"));
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.setTitle(appName);
			primaryStage.show();
			primaryStage.setOnCloseRequest((WindowEvent we) -> {
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Ostrze¿enie");
				a.setHeaderText("Czy na pewno chcesz wy³¹czyæ? Wszelkie niezapisane zmiany zostan¹ utracone!");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK)
					{
						Platform.exit();
					} else
					{
						we.consume();
					}
				});
			});

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		launch(args);
	}
}
