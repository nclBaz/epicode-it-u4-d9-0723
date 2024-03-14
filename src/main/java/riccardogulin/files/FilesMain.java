package riccardogulin.files;

import org.apache.commons.io.FileUtils;
import riccardogulin.entities.User;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FilesMain {
	public static void main(String[] args)  {
		File file = new File("src/output.txt"); // Creo un riferimento al file e al suo percorso nelle cartelle del progetto

		try {
			User user = new User("Aldo", "Baglio", 20, "Roma");
			FileUtils.writeStringToFile(file, user.toString() + System.lineSeparator(), StandardCharsets.UTF_8, true);
			System.out.println("Scritto!");

			String contenuto = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			System.out.println("CONTENUTO FILE");
			System.out.println(contenuto);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
