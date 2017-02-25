package controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;

public class ContentPaneController implements Initializable, Serializable
{

	private static final long serialVersionUID = 234324892347815L;

	@FXML
	private TextArea mainTextArea;

	private MainPaneController mainPaneController;

	public TextArea getMainTextArea()
	{
		return mainTextArea;
	}

	public void setMainTextArea(TextArea mainTextArea)
	{
		this.mainTextArea = mainTextArea;
	}

	public void injectMainPaneController(MainPaneController mainPaneController)
	{
		this.mainPaneController = mainPaneController;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		configureTextArea();
	}

	private void configureTextArea()
	{
		// Domyœlny rodzaj czcionki
		getMainTextArea().setFont(Font.font("Arial"));
		// Domyœlna wielkoœæ czcionki
		getMainTextArea().setFont(Font.font(12));

	}

}
