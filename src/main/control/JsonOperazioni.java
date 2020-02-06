package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/JSONOperazioni")
public class JsonOperazioni extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public JsonOperazioni() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {

    HttpSession sessione = request.getSession();
    
    // recupero dalla sessione la lista degli operatori selezionati
    // se non esite, la creo
    ArrayList operazioniSelezionate = (ArrayList) sessione.getAttribute("operazioniSelezionate");
    if (operazioniSelezionate == null) {
      operazioniSelezionate = new ArrayList();
    }

    // recupero dalla sessione la lista degli operatori selezionati con decisione sull'operando 
    // se non esite, la creo
    ArrayList latoOperazioni = (ArrayList) sessione.getAttribute("latoOperazioni");
    if (latoOperazioni == null) {
      latoOperazioni = new ArrayList();
    }
    
    // recupero dalla sessione la lista dei colori relativi agli operatori selezionati
    // se non esite, la creo
    ArrayList colori = (ArrayList) sessione.getAttribute("colori");
    if (colori == null) {
      colori = new ArrayList();
    }
    

    ArrayList f1 = (ArrayList) sessione.getAttribute("f1");

    if (f1 == null) {
      f1 = new ArrayList();
    }
    
    ArrayList f2 = (ArrayList) sessione.getAttribute("f2");
    if (f2 == null) {
      f2 = new ArrayList();
    }

    String p = (String) request.getParameter("op");
    
    if (p != null) {
      if (!p.equals("null")) {
        Random random = new Random();
        int val = random.nextInt(15728638) + 1048576;
        String hex = new String();
        hex = Integer.toHexString(val);

        operazioniSelezionate.add(p);
        latoOperazioni.add("null");
        colori.add(hex);
        f1.add("null");
        f1.add("null");
        f2.add("null");
        f2.add("null");

        sessione.setAttribute("operazioniSelezionate", operazioniSelezionate);
        sessione.setAttribute("latoOperazioni", latoOperazioni);
        sessione.setAttribute("colori", colori);
        sessione.setAttribute("f1", f1);
        sessione.setAttribute("f2", f2);
      }
    }

    response.setContentType("text/html");
    response.getWriter().append("[");
    for (int i = 0; i < operazioniSelezionate.size(); i++) {
      int x = i * 2;
      int j =  x + 1;
      if (i != operazioniSelezionate.size() - 1) {
        response.getWriter().append("{\"op\":\"" + operazioniSelezionate.get(i) 
                + "\", \"scelta\": \"" + latoOperazioni.get(i) + "\", \"colore\": \""
                + colori.get(i) + "\", \"f1N\": \"" + f1.get(x) + "\",  \"f1T\": \""
                + f1.get(j) + "\", \"f2N\": \"" + f2.get(x) + "\", \"f2T\": \""
                + f2.get(j) + "\"},");
      } else {
        response.getWriter().append("{\"op\":\"" + operazioniSelezionate.get(i) 
                + "\", \"scelta\": \"" + latoOperazioni.get(i) + "\", \"colore\": \""
                + colori.get(i) + "\", \"f1N\": \"" + f1.get(x) + "\",  \"f1T\": \""
                + f1.get(j) + "\", \"f2N\": \"" + f2.get(x) + "\", \"f2T\": \""
                + f2.get(j) + "\"}");
      }
    }
    response.getWriter().append("]");

    //sessione.invalidate();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    doGet(request, response);
  }

}
