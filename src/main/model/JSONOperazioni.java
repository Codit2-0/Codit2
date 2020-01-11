package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet("/JSONOperazioni")
public class JSONOperazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public JSONOperazioni() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String p = (String) request.getParameter("op");
		
	    HttpSession sessione = request.getSession();
		ArrayList operazioniSelezionate = (ArrayList) sessione.getAttribute("operazioniSelezionate");
		ArrayList latoOperazioni = (ArrayList) sessione.getAttribute("latoOperazioni");
		if(operazioniSelezionate == null) operazioniSelezionate = new ArrayList();
		if(latoOperazioni == null) latoOperazioni = new ArrayList();
		
		if(p != "null" && p != null) {
			operazioniSelezionate.add(p);
			latoOperazioni.add("null");
			sessione.setAttribute("operazioniSelezionate", operazioniSelezionate);
			sessione.setAttribute("latoOperazioni", latoOperazioni);
		}
		
		response.setContentType("text/html");
		response.getWriter().append("[");
		for(int i = 0;i<operazioniSelezionate.size();i++) {
			if(i != operazioniSelezionate.size() - 1) { response.getWriter().append("{\"op\":\"" + operazioniSelezionate.get(i) + "\", \"scelta\": \""+latoOperazioni.get(i)+"\"},");}
			else {response.getWriter().append("{\"op\": \"" + operazioniSelezionate.get(i) + "\", \"scelta\": \""+latoOperazioni.get(i)+"\"}");}
			//System.out.println(operazioniSelezionate.get(i));
		}
	    response.getWriter().append("]");
		
	    //sessione.invalidate();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
