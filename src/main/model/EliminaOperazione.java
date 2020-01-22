package model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


//!!!!!MODIFICATA!!!!!!!!


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
		 
		 
		    
		    ArrayList operazioniSelezionate = (ArrayList) sessione.getAttribute("operazioniSelezionate");
		    ArrayList latoOperazioni = (ArrayList) sessione.getAttribute("latoOperazioni");
		    ArrayList colori = (ArrayList) sessione.getAttribute("colori");
		    ArrayList f1 = (ArrayList) sessione.getAttribute("f1");
		    ArrayList f2 = (ArrayList) sessione.getAttribute("f2");

		    response.setContentType("text/html");
		    response.getWriter().append("[");
		    response.getWriter().append("{\"op\":\"" + operazioniSelezionate.get(Integer.parseInt(p)) + "\", \"scelta\": \""+latoOperazioni.get(Integer.parseInt(p))+"\", \"colore\": \""+colori.get(Integer.parseInt(p))+"\", \"f1N\": \""+f1.get(Integer.parseInt(p))+"\",  \"f1T\": \""+f1.get(Integer.parseInt(p)+1)+"\", \"f2N\": \""+f2.get(Integer.parseInt(p))+"\", \"f2T\": \""+f2.get(Integer.parseInt(p)+1)+"\"}");  
		    response.getWriter().append("]");

		    
		    operazioniSelezionate.remove(Integer.parseInt(p));
		    latoOperazioni.remove(Integer.parseInt(p));
		    colori.remove(Integer.parseInt(p));
		    f1.remove(Integer.parseInt(p));
		    f1.remove(Integer.parseInt(p));
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
