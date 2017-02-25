package controller;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class AboutPaneController implements Initializable
{
	@FXML
	private TextArea aboutTextArea;

	public TextArea getAboutTextField()
	{
		return aboutTextArea;
	}

	public void setAboutTextField(TextArea aboutTextField)
	{
		this.aboutTextArea = aboutTextField;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		aboutTextArea.setText("Edytor Tekstu v0.2 \n\n\n\n\nAutorzy:\nSkulimowski Maciej\nSokalski Rafa³");
	}

}
