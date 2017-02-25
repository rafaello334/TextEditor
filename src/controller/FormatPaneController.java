package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;

public class FormatPaneController implements Initializable
{
	@FXML
	private SplitMenuButton fontNameButton;

	@FXML
	private SplitMenuButton fontSizeButton;

	@FXML
	private ToggleButton boldTextButton;

	@FXML
	private ToggleButton italicTextButton;

	@FXML
	private ToggleButton underlineTextButton;

	@FXML
	private Button leftAlignmentButton;

	@FXML
	private Button centerAlignmentButton;

	@FXML
	private Button rightAlignmentButton;

	@FXML
	private Button justifyButton;

	public SplitMenuButton getFontNameButton()
	{
		return fontNameButton;
	}

	public void setFontNameButton(SplitMenuButton fontNameButton)
	{
		this.fontNameButton = fontNameButton;
	}

	public SplitMenuButton getFontSizeButton()
	{
		return fontSizeButton;
	}

	public void setFontSizeButton(SplitMenuButton fontSizeButton)
	{
		this.fontSizeButton = fontSizeButton;
	}

	public ToggleButton getBoldTextButton()
	{
		return boldTextButton;
	}

	public void setBoldTextButton(ToggleButton boldTextButton)
	{
		this.boldTextButton = boldTextButton;
	}

	public ToggleButton getItalicTextButton()
	{
		return italicTextButton;
	}

	public void setItalicTextButton(ToggleButton italicTextButton)
	{
		this.italicTextButton = italicTextButton;
	}

	public ToggleButton getUnderlineTextButton()
	{
		return underlineTextButton;
	}

	public void setUnderlineTextButton(ToggleButton underlineTextButton)
	{
		this.underlineTextButton = underlineTextButton;
	}

	public Button getLeftAlignmentButton()
	{
		return leftAlignmentButton;
	}

	public void setLeftAlignmentButton(Button leftAlignmentButton)
	{
		this.leftAlignmentButton = leftAlignmentButton;
	}

	public Button getCenterAlignmentButton()
	{
		return centerAlignmentButton;
	}

	public void setCenterAlignmentButton(Button centerAlignmentButton)
	{
		this.centerAlignmentButton = centerAlignmentButton;
	}

	public Button getRightAlignmentButton()
	{
		return rightAlignmentButton;
	}

	public void setRightAlignmentButton(Button rightAlignmentButton)
	{
		this.rightAlignmentButton = rightAlignmentButton;
	}

	public Button getJustifyButton()
	{
		return justifyButton;
	}

	public void setJustifyButton(Button justifyButton)
	{
		this.justifyButton = justifyButton;
	}

	private MainPaneController mainPaneController;

	public void injectMainPaneController(MainPaneController mainPaneController)
	{
		this.mainPaneController = mainPaneController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources)
	{
		configureButtons();
	}

	// Buttons Configuration

	public void configureButtons()
	{
		getFontNameButton().setText("Arial");
		getFontSizeButton().setText("12");

		// Getting font names

		for (String fontName : Font.getFamilies())
			getFontNameButton().getItems().add(new MenuItem(fontName));

		for (final MenuItem item : getFontNameButton().getItems())
		{
			item.setOnAction((event) -> {
				mainPaneController.getTextArea().setFont(Font.font(item.getText()));
				getFontNameButton().setText(item.getText());
			});
		}

		for (int i = 0; i < 100; i++)
		{
			if ((i > 0 && i < 11) || i == 12 || i == 14 || i == 16 || i == 18 || i == 20 || i == 24 || i == 32
					|| i == 48 || i == 72)
				getFontSizeButton().getItems().add(new MenuItem(Integer.toString(i)));
		}

		for (final MenuItem item : getFontSizeButton().getItems())
		{
			item.setOnAction((event) -> {
				mainPaneController.getTextArea().setFont(Font.font(Integer.parseInt(item.getText())));
				getFontSizeButton().setText(item.getText());
			});
		}

		// Bold Text

		getBoldTextButton().setOnAction(new EventHandler<ActionEvent>()
		{
			@Override
			public void handle(ActionEvent event)
			{

				if (getBoldTextButton().isSelected())
				{
					mainPaneController.getTextArea().getStyleClass().add("bold-text-area");
				} else
				{
					mainPaneController.getTextArea().getStyleClass().remove("bold-text-area");
				}
			}
		});

		// Italic Text
		getItalicTextButton().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				if (getItalicTextButton().isSelected())
				{
					mainPaneController.getTextArea().getStyleClass().add("italic-text-area");
				} else
				{
					mainPaneController.getTextArea().getStyleClass().remove("italic-text-area");
				}

			}
		});

		// Underlined Text
		getUnderlineTextButton().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				if (getUnderlineTextButton().isSelected())
				{
					mainPaneController.getTextArea().getStyleClass().add("underlined-text-area");

				} else
				{
					mainPaneController.getTextArea().getStyleClass().remove("underlined-text-area");
				}

			}
		});

		getLeftAlignmentButton().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				mainPaneController.getTextArea().getStyleClass().remove("center-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("right-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("justify-text-area");
				mainPaneController.getTextArea().getStyleClass().add("left-text-area");
			}
		});

		getCenterAlignmentButton().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				mainPaneController.getTextArea().getStyleClass().remove("left-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("right-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("justify-text-area");
				mainPaneController.getTextArea().getStyleClass().add("center-text-area");
			}
		});

		getRightAlignmentButton().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				mainPaneController.getTextArea().getStyleClass().remove("left-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("center-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("justify-text-area");
				mainPaneController.getTextArea().getStyleClass().add("right-text-area");
			}
		});

		getJustifyButton().setOnAction(new EventHandler<ActionEvent>()
		{

			@Override
			public void handle(ActionEvent event)
			{
				mainPaneController.getTextArea().getStyleClass().remove("left-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("right-text-area");
				mainPaneController.getTextArea().getStyleClass().remove("center-text-area");
				mainPaneController.getTextArea().getStyleClass().add("justify-text-area");
			}
		});

	}

}
