package servlet;

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
public class ResetSession extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * costruttore.
   * @see HttpServlet#HttpServlet()
   */
  public ResetSession() {
    super();
  }

  /**
   * override del metodi doGet di {@link HttpServlet}.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
                      throws ServletException, IOException {

    HttpSession sessione = request.getSession();

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
