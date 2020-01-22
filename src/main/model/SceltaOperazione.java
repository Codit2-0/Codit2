package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SceltaOperazione.
 */
@WebServlet("/SceltaOperazione")
public class SceltaOperazione extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * costruttore.
   * @see HttpServlet#HttpServlet()
   */
  public SceltaOperazione() {
    super();
  }

  /**
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    String p = (String) request.getParameter("op");
    String s = (String) request.getParameter("sc");

    HttpSession sessione = request.getSession();
    ArrayList latoOperazione  = (ArrayList) sessione.getAttribute("latoOperazioni");
    latoOperazione.set(Integer.parseInt(p),s);
    sessione.setAttribute("latoOperazione", latoOperazione);

    String f1N = (String) request.getParameter("f1Name");
    String f1T = (String) request.getParameter("f1Type");
    
    ArrayList f1  = (ArrayList) sessione.getAttribute("f1");
    f1.set(Integer.parseInt(p),f1N);
    f1.set(Integer.parseInt(p) + 1,f1T);
    sessione.setAttribute("f1", f1);

    String f2N = (String) request.getParameter("f2Name");
    String f2T = (String) request.getParameter("f2Type");
    
    ArrayList f2  = (ArrayList) sessione.getAttribute("f2");
    f2.set(Integer.parseInt(p), f2N);
    f2.set(Integer.parseInt(p) + 1, f2T);
    sessione.setAttribute("f2", f2);

  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
