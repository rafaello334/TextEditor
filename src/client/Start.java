package client;

import controller.MainPaneController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Start extends Application
{

	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			primaryStage.setTitle("Edytor Tekstu v.0.3");
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainPane.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setOnCloseRequest((WindowEvent we) -> {
				Alert a = new Alert(Alert.AlertType.CONFIRMATION);
				a.setTitle("Ostrze¿enie");
				a.setHeaderText("Czy na pewno chcesz wy³¹czyæ? Wszelkie niezapisane zmiany zostan¹ utracone!");
				a.showAndWait().ifPresent(response -> {
					if (response == ButtonType.OK)
					{
						Platform.exit();
						System.exit(0);
					} else
					{
						we.consume();
					}
				});
			});

			MainPaneController mainPaneController = (MainPaneController) loader.getController();

			new Client(mainPaneController);
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