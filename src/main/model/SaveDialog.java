package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveDialog {

  public SaveDialog() {

  }

  /**
   * Crea un pop-up che conferma l'avvenut osalvataggio delle posizioni.
   * @param path Il persorco del file
   * @param nome Il nome del file
   * @param contenuto Il contenuto del pop-up
   * @throws IOException In caso di errori di apertura del file lancia questa eccezione
   */
  public void saveDialog(String path, String nome, String contenuto) throws IOException {


    try {
      File fil = new File(path + nome);
      //System.out.println(path + nome);
      if (!fil.exists()) {
        fil.createNewFile();
      }

      FileWriter myWriter = new FileWriter(fil);
      myWriter.write(contenuto);
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
      // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

}
