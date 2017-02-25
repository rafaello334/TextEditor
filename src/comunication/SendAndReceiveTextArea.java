package comunication;

import controller.MainPaneController;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

public class SendAndReceiveTextArea
{
	FormattedText formattedText;
	MainPaneController mainPaneController;

	public SendAndReceiveTextArea(MainPaneController mainPaneController)
	{
		this.mainPaneController = mainPaneController;
	}

	public FormattedText getFormattedText()
	{
		return formattedText;
	}

	public void setFormattedText(FormattedText formattedText)
	{
		this.formattedText = formattedText;
	}

	public void convertToTextArea(FormattedText formattedText)
	{
		mainPaneController.getTextArea().setText(formattedText.getText());
		mainPaneController.getTextArea().positionCaret(formattedText.getCaretPosition());
	}

	public void convertToFormattedText(TextArea textArea)
	{
		this.formattedText = new FormattedText(textArea.getText(), textArea.getCaretPosition());
	}

}
