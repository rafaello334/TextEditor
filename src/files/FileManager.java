package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileManager
{
	private static String fileName;


	public void writeTextToFile(File file, TextArea textArea)
	{
		fileName = file.getAbsolutePath();
		ObservableList<CharSequence> paragraph = textArea.getParagraphs();
		Iterator<CharSequence> iter = paragraph.iterator();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName)))
		{
			while (iter.hasNext())
			{
				CharSequence seq = iter.next();
				bw.append(seq);
				bw.newLine();
			}
			bw.flush();
			System.out.println("Zapisano dane w pliku: " + fileName);
		} catch (FileNotFoundException e)
		{
			System.err.println("Nie odnaleziono pliku " + fileName);
		} catch (IOException e)
		{
			System.err.println("B³¹d podczas zapisu danych do pliku " + fileName);
		}
	}

	public TextArea readTextFromFile(File file, TextArea textArea)
			throws FileNotFoundException, IOException, ClassNotFoundException
	{
		fileName = file.getAbsolutePath();
		textArea.setText("");
		try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath())))
		{
			String str;
			while ((str = br.readLine()) != null)
			{

				textArea.appendText(str + "\n");
			}
			System.out.println("Wczytano dane z pliku: " + fileName);

		} catch (FileNotFoundException e)
		{
			System.err.println("Nie odnaleziono pliku " + fileName);
			throw e;
		} catch (IOException e)
		{
			System.err.println("B³¹d podczas odczytu danych z pliku " + fileName);
			throw e;
		}

		return textArea;
	}

	public File openOrSaveFile(boolean save)
	{

		FileChooser fileChooser;
		File file;
		fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Plik tekstowy .txt", "*.txt"));
		if (save)
		{
			file = fileChooser.showSaveDialog(new Stage());
		} else
		{
			file = fileChooser.showOpenDialog(new Stage());
		}

		System.out.println(file.getName());
		return file;
	}

}
