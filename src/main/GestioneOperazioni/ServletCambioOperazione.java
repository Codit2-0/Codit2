package GestioneOperazioni;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CambioOperazione.
 */
@WebServlet("/CambioOperazione")
public class ServletCambioOperazione extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * costruttore della classe.
   * @see HttpServlet#HttpServlet()
   */
  public ServletCambioOperazione() {
    super();
  }

  /**
   * Override del metodo doGet di {@link HttpServlet}.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    // variabile contenete il nome dell'operatore selezionato
    String p = (String) request.getParameter("op");
    // variabile contenente la scelta rispetto all'operatore
    String s = (String) request.getParameter("sc");

    HttpSession sessione = request.getSession();
    
    // array degli operatori selezionati
    ArrayList latoOperazione  = (ArrayList) sessione.getAttribute("latoOperazioni");
    latoOperazione.set(Integer.parseInt(p),s);
    sessione.setAttribute("latoOperazione", latoOperazione);
  }

  /**
   *  Override del metodo doPost di {@link HttpServlet}.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
