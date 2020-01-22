package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SavePosition.
 */
@WebServlet("/SavePosition")
public class SavePosition extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * costruttore.
   * @see HttpServlet#HttpServlet()
   */
  public SavePosition() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * override del metodo.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String nomi = (String) request.getParameter("nomi");
    String xstr = (String) request.getParameter("x");
    String ystr = (String) request.getParameter("y");

    //System.out.println(nomi);
    //System.out.println(xstr);
    //System.out.println(ystr);

    int q = 0;

    ArrayList<String> arrayNomi = new ArrayList<String>();
    ArrayList<String> arrayX = new ArrayList<String>();
    ArrayList<String> arrayY = new ArrayList<String>();


    while (nomi.indexOf("__") != -1) {
      arrayNomi.add(nomi.substring(0, nomi.indexOf("__")));
      nomi = nomi.substring(nomi.indexOf("__") + 2);
      System.out.println(arrayNomi.get(q) + " " + q);
      q++;
    }

    while (xstr.indexOf("_") != -1) {
      arrayX.add(xstr.substring(0, xstr.indexOf("_")));
      xstr = xstr.substring(xstr.indexOf("_") + 1);
    }

    while (ystr.indexOf("_") != -1) {
      arrayY.add(ystr.substring(0, ystr.indexOf("_")));
      ystr = ystr.substring(ystr.indexOf("_") + 1);
    }

    String prima;
    String dopo;
    String fileModificato = "";
    String nomeFile;
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());


    File fileRead;

    String url = (String) request.getParameter("url");
    HttpSession sessione = request.getSession();
    File fileSession; 
    if (url.equals("Nframe2")) {
      fileSession = (File) sessione.getAttribute("Nframe2");
      fileRead = new File("copyFileDatabase2.xmi"); 
      nomeFile = ("riposizionato_" + (String) sessione.getAttribute("NomeFile2"));
    } else {
      fileSession  = (File) sessione.getAttribute("Nframe1");
      fileRead = new File("copyFileDatabase1.xmi"); 
      nomeFile = (String) sessione.getAttribute("NomeFile1");
      nomeFile = nomeFile.substring(0,nomeFile.indexOf(".xmi"));
      nomeFile = nomeFile + "_riposizionato" + timeStamp + ".xmi";

    }

    int sl = 0;
    int car = 0;
    String nuovoPath = fileSession.getAbsolutePath();
    while (sl != 3) {
      sl++;
      car += nuovoPath.indexOf("/");
      nuovoPath = nuovoPath.substring(nuovoPath.indexOf("/") + 1);
      System.out.println("nuovoPath= " + nuovoPath);
    }

    nuovoPath = fileSession.getAbsolutePath().substring(0, 
        fileSession.getAbsolutePath().indexOf(nuovoPath));
    System.out.println("nuovoPath= " + nuovoPath);
    File cartella = new File(nuovoPath + "Codit");
    if (!cartella.exists()) {
      cartella.mkdirs();
    }
    //File save = new File(nuovoPath+"Codit/"+fileSession.getName());

    //String n = fileSession.getAbsolutePath().substring(0,)
    //System.out.println(fileSession.getAbsolutePath());



    BufferedReader br = new BufferedReader(new FileReader(fileRead)); 

    String st; 
    int k = 0;
    String perPrendereIlNome = "";
    int riga = 0;
    while ((st = br.readLine()) != null) {
      riga++;
      if (riga == 2) {
        fileModificato += "<version v=\"" + timeStamp + "\"/>\n";
      }
      //Caso in cui è un'entità o un attributo
      if (st.indexOf("name=") != -1 && st.indexOf("name=") <= 200) {

        //Caso in cui x e y non sono settate
        if (st.indexOf("x=") == -1 || st.indexOf("y=") == -1) {
          //  perPrendereIlNome = st.substring(st.indexOf("name=")+6);

          //Caso in cui il nome si trova con il corrispondente nell'array dei nomi
          // if(perPrendereIlNome.substring(0, perPrendereIlNome.indexOf("\""))
          //        .equals(arrayNomi.get(indiciNomi))) {
          prima = st.substring(0, st.indexOf("name="));
          dopo = st.substring(st.indexOf("name="));

          fileModificato += prima + "x=\"" + arrayX.get(k) + "\" y=\""
                            + arrayY.get(k) + "\" " + dopo + "\n";
          if (k != arrayNomi.size() - 1) {
            k++;
          }
          //}
          //else {
          //fileModificato += st + "\n";
          //}


          // System.out.println(fileModificato);

          //Caso in cui x e y sono settate (Quindi sicuramente un'entità)  
        } else {
          prima = st.substring(0, st.indexOf("x="));
          dopo = st.substring(st.indexOf("name="));

          fileModificato += prima + "x=\"" + arrayX.get(k) + "\" y=\""
                            + arrayY.get(k) + "\" " + dopo + "\n";

          if (k != arrayNomi.size() - 1) {
            k++;
          }
          //System.out.println(fileModificato);

        }



      } else { //Se la riga che si sta leggendo non è ne un'entità ne un'attributo

        if (st.indexOf("type=\"uml:") != -1) {
          //Se è un'associazione
          if (st.substring(st.indexOf("type=\"uml:") + 10, 
                           st.indexOf("type=\"uml:") + 14).equals("Asso")) {
            //Se x e y non sono settate
            if (st.indexOf("x=") == -1 || st.indexOf("y=") == -1) {
              prima = st.substring(0, st.indexOf("_id\"") + 4);
              dopo = st.substring(st.indexOf("_id\"") + 4);
              fileModificato += prima + " x=\"" + arrayX.get(k) + "\" y=\""
                                + arrayY.get(k) + "\" " + dopo + "\n";
              if (k != arrayNomi.size() - 1) {
                k++;
              }
            } else { //Se x e y sono settate
             
              prima = st.substring(0, st.indexOf("x=\""));

              if (st.indexOf("/>") != -1) {
                dopo = st.substring(st.indexOf("/>"));
              } else {
                dopo = st.substring(st.indexOf(">"));
              }


              fileModificato += prima + " x=\"" + arrayX.get(k) + "\" y=\""
                                + arrayY.get(k) + "\" " + dopo + "\n";

              if (k != arrayNomi.size() - 1) {
                k++;
              }
            }
          } else { //Se non è un'associazione
            fileModificato += st + "\n";
          }
          
          
        } else {
          fileModificato += st + "\n";
          // System.out.println(fileModificato);
        }
      }
    }
    SaveDialog saveDialogClass = new SaveDialog();
    saveDialogClass.saveDialog(nuovoPath + "Codit/", nomeFile, fileModificato);
    fileModificato = "";

    //System.out.println(fileModificato);

  }

  /**
   * override del metodo.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) 
                  throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
