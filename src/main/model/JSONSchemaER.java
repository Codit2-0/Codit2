package model;

import control.JsonParser;
import control.Parser;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

@WebServlet("/JSONSchemaER")
public class JSONSchemaER extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public JSONSchemaER() {
    super();
  }


  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    HttpSession sessione = request.getSession();
    String url = (String) sessione.getAttribute("url"); 

    response.setContentType("text/html");
//  response.getWriter().append("[{");
    
    Parser p = new Parser();
    ERBean er = p.parser(url);

    JSONObject obj = JsonParser.parseToJson(er);
    response.getWriter().append(obj.toJSONString());
/*
    if (er.getEntity() != null) {
      for (int i = 0; i < er.getEntity().size(); i++) {
        response.getWriter().append("\"entita\": \"" + er.getEntity().get(i).getName() + "\",");
        if (i != er.getEntity().size() - 1) {
          for (int j = 0; j < er.getEntity().get(i).getAttribute().size(); j++) {
            response.getWriter().append("\"attributo\": \"" + er.getEntity().get(i).getAttribute().get(j) + "\",");
          }
        } else {
          for (int j = 0; j < er.getEntity().get(i).getAttribute().size(); j++) {
            if (i != er.getEntity().get(i).getAttribute().size() - 1) {
              response.getWriter().append("\"attributo\": \"" + er.getEntity().get(i).getAttribute().get(j) + "\",");
            } else {
              response.getWriter().append("\"attributo\": \"" + er.getEntity().get(i).getAttribute().get(j) + "\"}");
            }
          } 

        }
      }
    }
    response.getWriter().append("]");
*/
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }

}