package model;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SaveDialog {

  public SaveDialog() {

  }

  /**
   * 
   * @param path
   * @param nome
   * @param contenuto
   * @throws IOException
   */
  public void saveDialog(String path, String nome, String contenuto) throws IOException {
    //	     String path = "";
    //	     String nome ="";
    //	     JFrame frame = new JFrame();  
    //        JPanel panel = new JPanel();  
    //        panel.setLayout(new FlowLayout());    
    //        frame.add(panel);  
    //        frame.setSize(100, 100);  
    //        frame.setLocationRelativeTo(null);  
    //        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    //        frame.setVisible(false); 
    //        
    //        
    //    	int result = -99;
    //		JFileChooser fileChooser = new JFileChooser();
    //		
    //		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    //		result = fileChooser.showSaveDialog(null);
    //		if(result == JFileChooser.APPROVE_OPTION) {
    //			path = fileChooser.getCurrentDirectory().getAbsolutePath();	
    //			nome = fileChooser.getSelectedFile().getName();
    //		}
    //		
    //		System.out.println(nome);
    //		System.out.println(result);

    try {
      File fil = new File(path+nome);
      System.out.println(path+nome);
      if(!fil.exists()) {
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
