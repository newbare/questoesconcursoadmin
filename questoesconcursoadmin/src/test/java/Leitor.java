import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Leitor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File arquivo = new File("C:\\Users\\Said\\Desktop\\processos.txt");
		try {
			FileReader fileReader = new FileReader(arquivo);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String linha = "";
			while ((linha = bufferedReader.readLine()) != null) {
				System.out.println(linha);
			}
			fileReader.close();
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
