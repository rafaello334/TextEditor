package comunication;

import java.io.Serializable;

public class FormattedText implements Serializable
{
	private static final long serialVersionUID = 14325345345543543L;

	private String text;
	private int caretPosition;
	
	
	public int getCaretPosition()
	{
		return caretPosition;
	}

	public void setCaretPosition(int caretPosition)
	{
		this.caretPosition = caretPosition;
	}



	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public FormattedText(String text, int caretPosition)
	{
		this.text = text;
		this.caretPosition = caretPosition;

	}

	@Override
	public String toString()
	{
		return "FormattedText [text=" + text + "]" + ", caretPosition = " + caretPosition + "]";
	}

}
