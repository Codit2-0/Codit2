package GestioneCreazioneSchemaEr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ResetSession.
 */
@WebServlet("/ResetSession")
public class ServletResetSession extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * costruttore.
   * @see HttpServlet#HttpServlet()
   */
  public ServletResetSession() {
    super();
  }

  /**
   * override del metodi doGet di {@link HttpServlet}.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {

    HttpSession sessione = request.getSession();
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
    sessione.removeAttribute("operazioniSelezionate");
    sessione.removeAttribute("latoOperazioni");
    sessione.removeAttribute("colori");
    sessione.removeAttribute("f1");
    sessione.removeAttribute("f2");
   
    String url = request.getParameter("presente");
    File copy = (File) sessione.getAttribute(url);
    copy.delete();
    sessione.removeAttribute(url);

  }
  
  /**
   * override del metodo doPost di {@link HttpServlet}.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
