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
 * Servlet implementation class EliminaOperazione
 */
@WebServlet("/EliminaOperazione")
public class EliminaOperazione extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public EliminaOperazione() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession sessione = request.getSession();
    String p = (String) request.getParameter("op");

    System.out.print(p);

    ArrayList operazioniSelezionate = (ArrayList) sessione.getAttribute("operazioniSelezionate");
    operazioniSelezionate.remove(Integer.parseInt(p));

    ArrayList latoOperazioni = (ArrayList) sessione.getAttribute("latoOperazioni");
    latoOperazioni.remove(Integer.parseInt(p));


    ArrayList colori = (ArrayList) sessione.getAttribute("colori");
    colori.remove(Integer.parseInt(p));


    ArrayList f1 = (ArrayList) sessione.getAttribute("f1");
    f1.remove(Integer.parseInt(p));
    f1.remove(Integer.parseInt(p));



    ArrayList f2 = (ArrayList) sessione.getAttribute("f2");
    f2.remove(Integer.parseInt(p));
    f2.remove(Integer.parseInt(p));

    sessione.setAttribute("operazioniSelezionate", operazioniSelezionate);
    sessione.setAttribute("latoOperazioni", latoOperazioni);
    sessione.setAttribute("colori", colori);
    sessione.setAttribute("f1", f1);
    sessione.setAttribute("f2", f2);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}
