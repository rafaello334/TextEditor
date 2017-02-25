package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import comunication.FormattedText;

public class Server
{
	public static final String HOST = "localhost";
	private static final int PORT = 1000;

	int clientNumber = 0;

	ArrayList<ClientConnection> clients = new ArrayList<>();

	public Server()
	{
		try
		{
			ServerSocket serverSocket = new ServerSocket(PORT);

			while (true)
			{
				Socket socket = serverSocket.accept();
				clients.add(new ClientConnection(socket));
			}
		} catch (IOException e)
		{
		}
	}

	synchronized void incomingMessage(FormattedText formattedText)
	{
		for (ClientConnection c : clients)
		{
			c.sendMessage(formattedText);
		}
	}

	public class ClientConnection
	{
		final Socket socket;
		private final InputConnection input;
		private final OutputConnection output;

		public ClientConnection(Socket socket)
		{
			this.socket = socket;

			System.out.println("Polaczono klienta nr " + clientNumber);
			this.input = new InputConnection();
			this.output = new OutputConnection();
			this.input.start();
			this.output.start();
			clientNumber++;
		}

		public void sendMessage(FormattedText formattedText)
		{
			this.output.addMessage(formattedText);
		}

		private class InputConnection extends Thread
		{
			FormattedText formattedText;

			public void run()
			{
				try
				{
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
					while (true)
					{
						formattedText = (FormattedText) input.readObject();
						incomingMessage(formattedText);
					}

				} catch (IOException e)
				{

					System.out.println("Klient numer: " + --clientNumber + " roz³¹czy³ siê");

				} catch (ClassNotFoundException e)
				{
					e.printStackTrace();
				}
			}
		}

		private class OutputConnection extends Thread
		{

			private ArrayList<FormattedText> inputList = new ArrayList<>();

			public void run()
			{
				try
				{
					ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

					while (!socket.isClosed())
					{

						if (!inputList.isEmpty())
						{
							output.writeObject(inputList.remove(0));
							output.flush();
						}
					}
				} catch (IOException e)
				{
					System.out.println();
				}
			}

			public synchronized void addMessage(FormattedText formattedText)
			{
				inputList.add(formattedText);
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		new Server();
	}

}
