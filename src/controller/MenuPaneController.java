package controller;

import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import files.FileManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.IndexRange;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class MenuPaneController implements Initializable
{

	MainPaneController mainPaneController;
	private String copyCutText = "";

	@FXML
	private Menu fileMenuButton;

	@FXML
	private Menu editMenuButton;

	@FXML
	private Menu helpMenuButton;

	@FXML
	private MenuItem cutMenuItem;

	@FXML
	private MenuItem copyMenuItem;

	@FXML
	private MenuItem pasteMenuItem;

	@FXML
	private MenuItem selectMenuItem;

	@FXML
	private MenuItem newMenuItem;

	@FXML
	private MenuItem saveMenuItem;

	@FXML
	private MenuItem loadMenuItem;

	@FXML
	private MenuItem closeMenuItem;

	@FXML
	private MenuItem aboutMenuItem;

	public Menu getFileMenuButton()
	{
		return fileMenuButton;
	}

	public void setFileMenuButton(Menu fileMenuButton)
	{
		this.fileMenuButton = fileMenuButton;
	}

	public Menu getEditMenuButton()
	{
		return editMenuButton;
	}

	public void setEditMenuButton(Menu editMenuButton)
	{
		this.editMenuButton = editMenuButton;
	}

	public Menu getHelpMenuButton()
	{
		return helpMenuButton;
	}

	public void setHelpMenuButton(Menu helpMenuButton)
	{
		this.helpMenuButton = helpMenuButton;
	}

	public MenuItem getCutMenuItem()
	{
		return cutMenuItem;
	}

	public void setCutMenuItem(MenuItem cutMenuItem)
	{
		this.cutMenuItem = cutMenuItem;
	}

	public MenuItem getCopyMenuItem()
	{
		return copyMenuItem;
	}

	public void setCopyMenuItem(MenuItem copyMenuItem)
	{
		this.copyMenuItem = copyMenuItem;
	}

	public MenuItem getPasteMenuItem()
	{
		return pasteMenuItem;
	}

	public void setPasteMenuItem(MenuItem pasteMenuItem)
	{
		this.pasteMenuItem = pasteMenuItem;
	}

	public MenuItem getSelectMenuItem()
	{
		return selectMenuItem;
	}

	public void setSelectMenuItem(MenuItem selectMenuItem)
	{
		this.selectMenuItem = selectMenuItem;
	}

	public MenuItem getNewMenuItem()
	{
		return newMenuItem;
	}

	public void setNewMenuItem(MenuItem newMenuItem)
	{
		this.newMenuItem = newMenuItem;
	}

	public MenuItem getSaveMenuItem()
	{
		return saveMenuItem;
	}

	public void setSaveMenuItem(MenuItem saveMenuItem)
	{
		this.saveMenuItem = saveMenuItem;
	}

	public MenuItem getLoadMenuItem()
	{
		return loadMenuItem;
	}

	public void setLoadMenuItem(MenuItem loadMenuItem)
	{
		this.loadMenuItem = loadMenuItem;
	}

	public MenuItem getCloseMenuItem()
	{
		return closeMenuItem;
	}

	public void setCloseMenuItem(MenuItem closeMenuItem)
	{
		this.closeMenuItem = closeMenuItem;
	}

	public MenuItem getAboutMenuItem()
	{
		return aboutMenuItem;
	}

	public void setAboutMenuItem(MenuItem aboutMenuItem)
	{
		this.aboutMenuItem = aboutMenuItem;
	}

	public void injectMainPaneController(MainPaneController mainPaneController)
	{
		this.mainPaneController = mainPaneController;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		configureFileMenu();
		configureEditMenu();
		configureHelpMenu();
	}

	public void saveFile()
	{
		FileManager fileManager = new FileManager();
		try
		{
			File file = fileManager.openOrSaveFile(true);
			fileManager.writeTextToFile(file, mainPaneController.getTextArea());
		} catch (NullPointerException e)
		{
			System.out.println("Anulowano zapisywanie pliku");
		}
	}

	private void configureFileMenu()
	{
		getLoadMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{
				FileManager fileManager = new FileManager();
				try
				{
					File file = fileManager.openOrSaveFile(false);
					fileManager.readTextFromFile(file, mainPaneController.getTextArea());
				} catch (NullPointerException e)
				{
					System.out.println("Anulowano otwieranie pliku");
				} catch (FileNotFoundException e)
				{
					e.printStackTrace();

				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		});

		getSaveMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				saveFile();
			}
		});

		getCloseMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{

				Object[] options = { "Tak", "Nie", "Anuluj" };
				int n = JOptionPane.showOptionDialog(new Frame(), "Czy chcesz zapisaæ?", "Pytanie",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				switch (n)
				{
				case 0:
					saveFile();
					break;
				case 1:
					Platform.exit();
					break;
				}

			}
		});

		getNewMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				Object[] options = { "Tak", "Nie", "Anuluj" };
				int n = JOptionPane.showOptionDialog(new Frame(), "Czy chcesz zapisaæ?", "Pytanie",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
				switch (n)
				{
				case 0:
					saveFile();
					break;
				case 1:
					mainPaneController.getTextArea().setText("");
					break;
				}

			}
		});

	}

	private void configureEditMenu()
	{
		getCopyMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{

				copyCutText = mainPaneController.getTextArea().getSelectedText();

			}
		});

		getCutMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				copyCutText = mainPaneController.getTextArea().getSelectedText();
				IndexRange range = mainPaneController.getTextArea().getSelection();
				mainPaneController.getTextArea().deleteText(range);

			}
		});

		getSelectMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				mainPaneController.getTextArea().selectAll();

			}
		});

		getPasteMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				int position = mainPaneController.getTextArea().getCaretPosition();
				mainPaneController.getTextArea().insertText(position, copyCutText);

			}
		});

	}

	private void configureHelpMenu()
	{
		getAboutMenuItem().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				String aboutName = "O programie";
				Stage aboutStage = new Stage();
				try
				{
					Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/other/view/AboutPane.fxml"));
					Scene scene = new Scene(parent);
					aboutStage.setScene(scene);
					aboutStage.setResizable(false);
					aboutStage.setTitle(aboutName);
					aboutStage.show();

				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		});
	}
}
