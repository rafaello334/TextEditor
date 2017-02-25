package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import comunication.FormattedText;
import comunication.SendAndReceiveTextArea;
import controller.MainPaneController;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Client
{

	private final static String ADDRESS = "localhost";
	private final static int PORT = 1000;

	private Socket socket;
	private OutputConnection output;
	private InputConnection input;
	private MainPaneController mainPaneController;

	public Client(MainPaneController mainPaneController)
	{
		try
		{
			this.mainPaneController = mainPaneController;

			socket = new Socket(ADDRESS, PORT);
			input = new InputConnection();
			output = new OutputConnection();
			input.start();
			output.start();

		} catch (UnknownHostException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private class OutputConnection extends Thread
	{
		private TextArea textArea;
		private SendAndReceiveTextArea sendAndReceiveTextArea;
		private ObjectOutputStream writer;

		public void run()
		{
			try
			{
				writer = new ObjectOutputStream(socket.getOutputStream());
				sendAndReceiveTextArea = new SendAndReceiveTextArea(mainPaneController);
			} catch (IOException e1)
			{
				e1.printStackTrace();
			}

			mainPaneController.getContentPaneController().getMainTextArea()
					.setOnKeyReleased(new EventHandler<KeyEvent>()
					{

						@Override
						public void handle(KeyEvent keyEvent)
						{

							try
							{
								textArea = mainPaneController.getTextArea();
								sendAndReceiveTextArea.convertToFormattedText(textArea);
								writer.writeObject(sendAndReceiveTextArea.getFormattedText());
								writer.flush();

							} catch (IOException e)
							{
								e.printStackTrace();
							} catch (Exception e)
							{
								e.printStackTrace();
							}

						}
					});
		}
	}

	private class InputConnection extends Thread
	{
		private SendAndReceiveTextArea sendAndReceiveTextArea;
		private FormattedText formattedText;

		public void run()
		{
			try
			{
				ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
				sendAndReceiveTextArea = new SendAndReceiveTextArea(mainPaneController);
				while (true)
				{
					formattedText = (FormattedText) input.readObject();
					sendAndReceiveTextArea.convertToTextArea(formattedText);
				}

			} catch (IOException e)
			{
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
	}

}