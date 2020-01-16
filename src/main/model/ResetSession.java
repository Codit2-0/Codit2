package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
	  ArrayList operazioniSelezionate = (ArrayList) sessione.getAttribute("operazioniSelezionate");
	  ArrayList latoOperazioni = (ArrayList) sessione.getAttribute("latoOperazioni");
	  ArrayList colori = (ArrayList) sessione.getAttribute("colori");
	  ArrayList f1 = (ArrayList) sessione.getAttribute("f1");
	  ArrayList f2 = (ArrayList) sessione.getAttribute("f2");
	  
	  response.setContentType("text/html");
	  response.getWriter().append("[");
	  if(operazioniSelezionate != null) {
	   for(int i = 0;i<operazioniSelezionate.size();i++) {
	    int x = i*2;
	    int j =  x + 1;
	    if(i != operazioniSelezionate.size() - 1) { response.getWriter().append("{\"op\":\"" + operazioniSelezionate.get(i) + "\", \"scelta\": \""+latoOperazioni.get(i)+"\", \"colore\": \""+colori.get(i)+"\", \"f1N\": \""+f1.get(x)+"\",  \"f1T\": \""+f1.get(j)+"\", \"f2N\": \""+f2.get(x)+"\", \"f2T\": \""+f2.get(j)+"\"},");}   
	    else { response.getWriter().append("{\"op\":\"" + operazioniSelezionate.get(i) + "\", \"scelta\": \""+latoOperazioni.get(i)+"\", \"colore\": \""+colori.get(i)+"\", \"f1N\": \""+f1.get(x)+"\",  \"f1T\": \""+f1.get(j)+"\", \"f2N\": \""+f2.get(x)+"\", \"f2T\": \""+f2.get(j)+"\"}");} 
	   }
	   
	     
	   sessione.removeAttribute("operazioniSelezionate");
	   sessione.removeAttribute("latoOperazioni");
	   sessione.removeAttribute("colori");
	   sessione.removeAttribute("f1");
	   sessione.removeAttribute("f2");
	  }
	  
	  response.getWriter().append("]");
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
