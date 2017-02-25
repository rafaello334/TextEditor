package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class MainPaneController implements Initializable
{

	@FXML
	private MenuPaneController menuPaneController;
	@FXML
	private FormatPaneController formatPaneController;
	@FXML
	private ContentPaneController contentPaneController;
	@FXML
	private AboutPaneController aboutPaneController;

	public TextArea getTextArea()
	{
		return contentPaneController.getMainTextArea();
	}

	public MenuPaneController getMenuPaneController()
	{
		return menuPaneController;
	}

	public void setMenuPaneController(MenuPaneController menuPaneController)
	{
		this.menuPaneController = menuPaneController;
	}

	public FormatPaneController getFormatPaneController()
	{
		return formatPaneController;
	}

	public void setFormatPaneController(FormatPaneController formatPaneController)
	{
		this.formatPaneController = formatPaneController;
	}

	public ContentPaneController getContentPaneController()
	{
		return contentPaneController;
	}

	public void setContentPaneController(ContentPaneController contentPaneController)
	{
		this.contentPaneController = contentPaneController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		formatPaneController.injectMainPaneController(this);
		menuPaneController.injectMainPaneController(this);
		contentPaneController.injectMainPaneController(this);

		System.out.println(this);
		System.out.println(menuPaneController);
		System.out.println(formatPaneController);
		System.out.println(contentPaneController);

	}

}
